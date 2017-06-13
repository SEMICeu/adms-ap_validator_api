package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

import com.gitb.core.AnyContent;
import com.gitb.core.ConfigurationType;
import com.gitb.core.Metadata;
import com.gitb.core.TypedParameter;
import com.gitb.core.TypedParameters;
import com.gitb.core.UsageEnumeration;
import com.gitb.core.ValidationModule;
import com.gitb.tr.BAR;
import com.gitb.tr.ObjectFactory;
import com.gitb.tr.TAR;
import com.gitb.tr.TestAssertionGroupReportsType;
import com.gitb.tr.ValidationCounters;
import com.gitb.tr.TestResultType;
import com.gitb.vs.GetModuleDefinitionResponse;
import com.gitb.vs.ValidateRequest;
import com.gitb.vs.ValidationResponse;
import com.gitb.vs.ValidationService;
import com.gitb.vs.Void;

@WebService(serviceName="ValidationService",
			endpointInterface="com.gitb.vs.ValidationService",
			targetNamespace="http://www.gitb.com/vs/v1/",
			portName="ValidatorServicePort",
			name="ValidationServiceImpl")
public class ValidationServiceImpl implements ValidationService {
	String username;
	String password;
	protected ObjectFactory objectFactory = new ObjectFactory();
	
    /**
     * Get the module definition.
     */
	@Override
	public GetModuleDefinitionResponse getModuleDefinition(@WebParam(name = "GetModuleDefinitionRequest",
	targetNamespace = "http://www.gitb.com/vs/v1/", partName = "parameters") Void aVoid) {
		
		// Setup ValidationModule
        GetModuleDefinitionResponse response = new GetModuleDefinitionResponse();
        response.setModule(new ValidationModule());
        response.getModule().setId("ValidationService");
        response.getModule().setOperation("V");
        response.getModule().setMetadata(new Metadata());
        response.getModule().getMetadata().setName("ValidationService");
        response.getModule().getMetadata().setVersion("1.0.0");
        //Set inputs
        response.getModule().setInputs(new TypedParameters());
        response.getModule().getInputs().getParam().add(setModuleDefinitionResponse(
        		"URL rules", "URI", UsageEnumeration.R, ConfigurationType.SIMPLE, "The url to the rules to be used to validate."));
        response.getModule().getInputs().getParam().add(setModuleDefinitionResponse(
        		"URL database", "URI", UsageEnumeration.R, ConfigurationType.SIMPLE, "The url to the database which to query."));
        response.getModule().getInputs().getParam().add(setModuleDefinitionResponse(
        		"URL data", "URI", UsageEnumeration.R, ConfigurationType.SIMPLE, "The url to the data to upload and validate. This parameter is mandatory."));
        response.getModule().getInputs().getParam().add(setModuleDefinitionResponse(
        		"SessionID", "long", UsageEnumeration.O, ConfigurationType.SIMPLE, "The session ID."));
        return response;
        
	}
	
	/**
     * Set parameter to be reported as an input in the ModuleDefinitionResponse
     * @param name Name of the parameter
     * @param type Type of the parameter (URI, long, ...)
     * @param usage The usage of the parameter (optional, mandatory, ...)
     * @param config The configuration of the parameter (simple, binary)
     * @param description The description of the parameter
     */
	private TypedParameter setModuleDefinitionResponse(String name, String type, 
			UsageEnumeration usage, ConfigurationType config, String description) {
		
		TypedParameter parameter =  new TypedParameter();
		parameter.setName(name);
		parameter.setType(type);
		parameter.setUse(usage);
		parameter.setKind(config);
		parameter.setDesc(description);
		return parameter;
		
	}

    /**
     * Download and validate a file against a SPARQL query (rules).
     * @param parameters Parameters for the validation.
     * @param parameters.getSessionID() The session ID.
     * @param parameters.getDataURI() The URI of the data to be downloaded and validated.
     * @param parameters.getRulesURI() The URI of the rules to be used for the validation.
     * @param parameters.getDatabaseURI() The URI of the database which to query.
     * @param parameters.getOutputFormat() The format in which the output should be provided.
     */
	@Override
	public ValidationResponse validate(@WebParam(name = "ValidateRequest", targetNamespace = "http://www.gitb.com/vs/v1/",
	partName = "parameters") ValidateRequest parameters) {
		getConfigurationValues();
		
		ValidationResponse response = new ValidationResponse();
		
		String sessionID = null, rulesURI = null, databaseURI = null, dataURI = null;
		
		for (AnyContent anInput: parameters.getInput()) {
            if (anInput.getName().equals("rulesURI")) {
            	rulesURI = anInput.getValue();
            } else if (anInput.getName().equals("databaseURI")) {
            	databaseURI = anInput.getValue();
            } else if (anInput.getName().equals("dataURI")) {
            	dataURI = anInput.getValue();
            } 
		}
		sessionID = parameters.getSessionId();
		
		// The data, database and rules URI are mandatory parameters.
		// Check if it is filled in. If not (IF), skip all steps and warn the user; ELSE: do steps.
		if (dataURI == null || dataURI.equalsIgnoreCase("?") 
				|| dataURI.toString().equalsIgnoreCase("")
			|| databaseURI == null || databaseURI.toString().equalsIgnoreCase("?") 
					|| databaseURI.toString().equalsIgnoreCase("")
			|| rulesURI == null || rulesURI.equalsIgnoreCase("?") 
					|| rulesURI.equalsIgnoreCase("")) 
		{
			throw new RuntimeException("Missing Parameter");
			
		} else {
			
			// The session ID is an optional parameter. If not provided, use the current time in ms as session ID.
			if ( sessionID == null || sessionID.equalsIgnoreCase("?") || sessionID.equalsIgnoreCase("")) {
				sessionID = String.valueOf( new Timestamp( (new Date()).getTime() ).getTime() );
			}
			
			// Get file as String.
			String file = getText(dataURI);
			// Get SPARQL query as String.
			String rules = getText(rulesURI);
			// Fill in the graph URI in the WHERE statement of the SPARQL query.
			rules = fillInSessionID(sessionID, rules);
			// Upload the file to the database using a HTTP POST request.
			httpPOST(file, databaseURI, sessionID, getUsername(), getPassword());
			// Perform the SPARQL query against the file and return the result as a String.
			TAR tar = validateFile(databaseURI, rules, file);
			
			// Fill in the tar in the response.
			response.setReport(tar);
		}
		
		return response;
		
	}

	/**
     * Downloads the content of a file to a string from a URL.
     * @param fileURL HTTP URL of the file to download.
     */
    private static String getText(String fileURL) {
    	// Initialise variables
    	String ls = System.getProperty("line.separator");
        URL website = null;
        URLConnection connection = null;
        BufferedReader in = null;
        StringBuilder response = new StringBuilder();
        String inputLine;
        
		try {
			// Set up connection
			website = new URL(fileURL);
			connection = website.openConnection();
			// Read file and append per line
			in = new BufferedReader(
			                        new InputStreamReader(
			                            connection.getInputStream()));
			while ((inputLine = in.readLine()) != null) {
			    response.append(inputLine);
				response.append(ls);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			// Throw Exception using SOAP Fault Message 
			throw new RuntimeException("Download of the file did not succeed");
		}
        
        return response.toString();
        
    }
    
	/**
     * Fill in the Graph URI in the rules file.
     * @param SessionID The session ID to be filled in.
     * @param Rules The SPARQL query in which the session ID should be filled in.
     */
    private String fillInSessionID(String SessionID, String rules) {
 
		rules = rules.replaceAll("<@@@TOKEN-GRAPH@@@>", "<http://" + SessionID + ">");
		return rules;
		
	}
	
    /**
     * Upload the file to the server via a HTTP POST request
     * @param file The file as a string.
     * @param database The server to which the file should be uploaded.
     * @param SessionID The session ID. This will also determine the graph URI.
     * @param username The user name of the server.
     * @param password The password of the server.
     */
	private static void httpPOST(String file, String database, String SessionID, String username, String password) {
		String url = null;
		try {
			// Set credentials for server
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(
                  new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, "SPARQL"),
                  new UsernamePasswordCredentials(username, password));
			
			// Create HTTP client
			CloseableHttpClient client = HttpClients.custom()
	                  .setDefaultCredentialsProvider(credsProvider)
	                  .build();
			
//			// Proxy settings for debugging purposes. Does not need to be enabled for final version.
//			URI proxyURI = new URI("http://localhost:8888");
//			URI targetURI = new URI("http://localhost:8890");
			
//			HttpHost proxy = new HttpHost(proxyURI.getHost(), proxyURI.getPort(), proxyURI.getScheme());
//			HttpHost target = new HttpHost(targetURI.getHost(), targetURI.getPort(), targetURI.getScheme());
//			
//			RequestConfig config = RequestConfig.custom()
//                    .setProxy(proxy)
//                    .build();
			
			// configure the url to upload to and create POST request
			if ( database.substring(database.length() - 1).equalsIgnoreCase("/")) {
				database = database.substring(0, database.length() - 1);
			}
			url = database + "-graph-crud?graph-uri=http://" + SessionID;
			HttpPost request = new HttpPost(url);
//			request.setConfig(config);
			
			// Set the content and headers of the POST
			HttpEntity entity = new ByteArrayEntity(file.getBytes("UTF-8"));   // Encoding
			request.setEntity(entity);
			request.setHeader(HttpHeaders.ACCEPT, "*/*");
			request.setHeader(HttpHeaders.EXPECT, "100-continue");
	        
			// Execute the POST and print the response if not successful.
			CloseableHttpResponse response = client.execute(request);   // IOException
			if (! (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 201 )) {
				// Throw Exception using SOAP Fault Message 
					throw new RuntimeException("Upload of the file did not succeed");
			}
			client.close();    // IO Exception
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			// Throw Exception using SOAP Fault Message 
			
			throw new RuntimeException("Upload of the file did not succeed");
		}  catch (IOException e) {
			e.printStackTrace();
			// Throw Exception using SOAP Fault Message 
			
			throw new RuntimeException("Upload of the file did not succeed");
		}
	}
	
	/**
     * Perform SPARQL query on the file to validate it. Create the report.
     * @param databaseURI The server against which to perform the query.
     * @param rules The SPARQL query.
     * @param file The file to be validated. 
     */
    private TAR validateFile(String databaseURI, String rules, String file) {
    	// Execute SPARQL query
    	String result = executeSPARQLquery(databaseURI, rules);
    	// Parse the CSV result to an Arraylist of Arraylists
    	List<List<String>> items = parseCSVResult(result);
    	// Create TestAssertionReport, set the Date and context
    	// Create elements in test report and add them to the report
    	TAR tar = createTAR(file, items);
    	return tar;  
    	
	}
    
	/**
     * Perform SPARQL query on the file to validate it.
     * @param databaseURI The server against which to perform the query.
     * @param rules The SPARQL query.
     */
	private String executeSPARQLquery(String databaseURI, String rules) {
		// Execute SPARQL query
        QueryExecution qe = QueryExecutionFactory.sparqlService(databaseURI, rules);
    	ResultSet results = qe.execSelect();
    	
        String result = new String();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			// Get output as CSV in a String
       		ResultSetFormatter.outputAsCSV(stream, results);
        	result = stream.toString("UTF-8");  
		} catch (Exception e) {
        	e.printStackTrace();
			// Throw Exception using SOAP Fault Message 
			
			throw new RuntimeException("SPARQL query did not succeed");
        } finally {
            qe.close();
        }
		return result; 	
		
	}
	
	/**
     * Parse the CSV string to an Arraylist of Arraylists
     * @param result The CSV string to parse
     */
	private List<List<String>> parseCSVResult(String result) {
		// Split the string on newline character, add to ArrayList and remove empty lines
    	List<String> linesList = Arrays.asList(result.split("[\\n\\r]"));
    	ArrayList<String> lines = new ArrayList<String>();
    	lines.addAll(linesList);
    	lines.removeAll(Arrays.asList(""));
    	
    	// Replace empty values with NA. 
    	// If last value is empty, this cannot be changed. It is handled later
    	List<List<String>> items = new ArrayList<>();
    	for (int i = 0; i < lines.size(); i++) {
    		while (lines.get(i).contains(",,")) {
    			lines.set(i, lines.get(i).replaceAll(",,", ",NA,"));
    			System.out.println(i + ": " + lines);
    		}
    	}
    	
    	// Split each line on "," and if the last value was empty, complete with NA
    	List<String> firstLineSplit = new ArrayList<>(Arrays.asList((lines.get(0).split("\\s*,\\s*"))));
    	int size = firstLineSplit.size();
    	for (int i = 0; i < lines.size(); i++) {
    		List<String> splittedLine = new ArrayList<>(Arrays.asList((lines.get(i).split("\\s*,\\s*"))));
    		if (splittedLine.size() != size ) {
    			splittedLine.add("NA");
    		}
    		items.add(splittedLine);
    	}
    	return items;
    	
	}
	
	/**
     * Get the current date and time.
     */
	private XMLGregorianCalendar getXMLGregorianCalendarDateTime() throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        
    }
	
	/**
     * Create the report elements.
     * @param TAR The TestAssertionReport to complete.
     * @param items The results to put into the report. 
     */
	private TAR createTAR(String file, List<List<String>> items) {    	
    	int severityIndex = 0, IDIndex = 0, descriptionIndex = 0, messageIndex = 0, subjectIndex = 0, predicateIndex = 0, objectIndex = 0;
    	// Define the index per line of each element, based on the headers
    	for (int j = 0; j < items.get(0).size(); j++) {
    		if ( items.get(0).get(j).contains("Rule_Severity") ) {
    			severityIndex = j;
    		} else if (items.get(0).get(j).contains("Rule_ID") ) {
    			IDIndex = j;
    		} else if (items.get(0).get(j).contains("Rule_Description") ) {
    			descriptionIndex = j;
    		} else if (items.get(0).get(j).contains("Message") ) {
    			messageIndex = j;
    		} else if (items.get(0).get(j).contains("Subject") ) {
    			subjectIndex = j;
    		} else if (items.get(0).get(j).contains("Predicate") ) {
    			predicateIndex = j;
    		} else if (items.get(0).get(j).contains("Object") ) {
    			objectIndex = j;
    		}
    	}
    	
    	TAR tar = new TAR();
        tar.setResult(TestResultType.FAILURE);
		tar.setReports(new TestAssertionGroupReportsType());
		AnyContent fileContext = new AnyContent();
		fileContext.setValue(file);
		tar.setContext(fileContext);
		try {
            tar.setDate(getXMLGregorianCalendarDateTime());
        } catch (DatatypeConfigurationException e) {
        	e.printStackTrace();
			// Throw Exception using SOAP Fault Message 
			
			throw new RuntimeException("Creation of TestAssertionReport did not succeed");
        } finally {
	    	// Create the error, warning or info object
	    	// Start from line 1 as line 0 are the headers
	    	int errorCount = 0, warningCount = 0;
	    	for (int i = 1; i < items.size(); i++) {
	    		BAR error = new BAR();
				error.setAssertionID(items.get(i).get(IDIndex));
				error.setDescription(items.get(i).get(messageIndex));
				error.setLocation(items.get(i).get(subjectIndex));
				error.setTest(items.get(i).get(descriptionIndex));
				error.setType(items.get(i).get(predicateIndex));
				error.setValue(items.get(i).get(objectIndex));
				if (items.get(i).get(severityIndex).contains("error")) {
					JAXBElement element = this.objectFactory.createTestAssertionGroupReportsTypeError(error);
					tar.getReports().getInfoOrWarningOrError().add(element);
					errorCount++;
				} else if (items.get(i).get(severityIndex).contains("warning")) {
					JAXBElement element = this.objectFactory.createTestAssertionGroupReportsTypeWarning(error);
					tar.getReports().getInfoOrWarningOrError().add(element);
					warningCount++;
				} else if (items.get(i).get(severityIndex).contains("info")) {
					JAXBElement element = this.objectFactory.createTestAssertionGroupReportsTypeInfo(error);
					tar.getReports().getInfoOrWarningOrError().add(element);
				}
	    	}
	    	
	    	// Fill in the count of errors and warnings
	    	ValidationCounters counters = new ValidationCounters();
	    	counters.setNrOfErrors(BigInteger.valueOf(errorCount));
	    	counters.setNrOfWarnings(BigInteger.valueOf(warningCount));
	    	tar.setCounters(counters);
        }
    	return tar;
    	
	}

	/**
     * Load the configuration settings from the config.properties file.
     */
    private void getConfigurationValues() {
	    InputStream inputStream = null;
		
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propFileName);
			prop.load(inputStream);
 
			setUsername("username");
			setPassword("password");
 
		}	catch (Exception e) {
			e.printStackTrace();
			// Throw Exception using SOAP Fault Message 
			
			throw new RuntimeException("Configuration not loaded");
		} 
		finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				// Throw Exception using SOAP Fault Message 
				
				throw new RuntimeException("Configuration not loaded");
			}
		}
		return;
		
	}
    
	private void setUsername(String username) {
		this.username = username;
	}
	
	private String getUsername() {
		return this.username;
	}
	
	private void setPassword(String password) {
		this.password = password;
	}
	
	private String getPassword() {
		return this.password;
	}
 
}