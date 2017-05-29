package service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.ParseException;
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

import com.gitb.core.v1.ConfigurationType;
import com.gitb.core.v1.Metadata;
import com.gitb.core.v1.TypedParameter;
import com.gitb.core.v1.TypedParameters;
import com.gitb.core.v1.UsageEnumeration;
import com.gitb.core.v1.ValidationModule;

import eu.semic.adms_ap.validator._1_0.xsd.GetModuleDefinitionResponse;
import eu.semic.adms_ap.validator._1_0.xsd.ValidateRequest;
import eu.semic.adms_ap.validator._1_0.xsd.ValidateResponse;
import eu.semic.adms_ap.validator._1_0.xsd.Void;
import services.validatorservice.IValidatorService;

@WebService(serviceName="ValidatorService", endpointInterface="services.validatorservice.IValidatorService",
targetNamespace="http://services/ValidatorService/", portName="ValidatorServicePort", name="ValidatorServiceImpl")
public class ValidatorServiceImpl implements IValidatorService {
	String username;
	String password;
	
    /**
     * Get the module definition.
     */
	@Override
	public GetModuleDefinitionResponse getDefinition(@WebParam(name = "GetModuleDefinitionRequest",
	targetNamespace = "http://www.gitb.com/vs/v1/", partName = "parameters") Void aVoid) {
		
		// Setup ValidationModule
        GetModuleDefinitionResponse response = new GetModuleDefinitionResponse();
        response.setModule(new ValidationModule());
        response.getModule().setId("ValidationService");
        response.getModule().setOperation("V");
        response.getModule().setMetadata(new Metadata());
        response.getModule().getMetadata().setName("ValidationService");
        response.getModule().getMetadata().setVersion("0.0.1");
        //Set inputs
        response.getModule().setInputs(new TypedParameters());
        response.getModule().getInputs().getParam().add(setModuleDefinitionResponse(
        		"URL rules", "URI", UsageEnumeration.R, ConfigurationType.SIMPLE, "The url to the rules to be used to validate."));
        response.getModule().getInputs().getParam().add(setModuleDefinitionResponse(
        		"URL database", "URI", UsageEnumeration.R, ConfigurationType.SIMPLE, "The url to the database which to query."));
        response.getModule().getInputs().getParam().add(setModuleDefinitionResponse(
        		"URL data", "URI", UsageEnumeration.R, ConfigurationType.SIMPLE, "The url to the data to upload and validate. This parameter is mandatory."));
        response.getModule().getInputs().getParam().add(setModuleDefinitionResponse(
        		"sessionID", "long", UsageEnumeration.O, ConfigurationType.SIMPLE, "The session ID."));
        response.getModule().getInputs().getParam().add(setModuleDefinitionResponse(
        		"outputFormat", "String", UsageEnumeration.O, ConfigurationType.SIMPLE, "The format in which you want the output to be provided. Possible values are: "
        				+ "XML, JSON, TSV and CSV. If not provided, the ouput will be in XML format."));
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
     */
	@Override
	public ValidateResponse validate(@WebParam(name = "ValidateRequest", targetNamespace = "http://www.gitb.com/vs/v1/",
	partName = "parameters") ValidateRequest parameters) {
		getConfigurationValues();
		
		ValidateResponse response = new ValidateResponse();
		
		// The data, database and rules URI are mandatory parameters.
		// Check if it is filled in. If not (IF), skip all steps and warn the user; ELSE: do steps.
		if (parameters.getDataURI() == null || parameters.getDataURI().getValue().toString().equalsIgnoreCase("?") 
				|| parameters.getDataURI().getValue().toString().equalsIgnoreCase("")
			|| parameters.getDatabaseURI()== null || parameters.getDatabaseURI().getValue().toString().equalsIgnoreCase("?") 
					|| parameters.getDatabaseURI().getValue().toString().equalsIgnoreCase("")
			|| parameters.getRulesURI() == null || parameters.getRulesURI().getValue().toString().equalsIgnoreCase("?") 
					|| parameters.getRulesURI().getValue().toString().equalsIgnoreCase("")) 
		{
			// Fill in a warning for the user to provide all necessary parameters.
			response.setReport("The URL data, database and and rules are mandatory parameters. Please provide all.");	
			
		} else {
			
			// The session ID is an optional parameter. If not provided, use the current time in ms as session ID.
			if ( parameters.getSessionId() == null || parameters.getSessionId().toString().equalsIgnoreCase("?") 
					|| parameters.getSessionId().toString().equalsIgnoreCase("")) {
				parameters.setSessionId( String.valueOf( new Timestamp( (new Date()).getTime() ).getTime() ) );
			}
			
			String result = new String();
			// Get file as String.
			String file = getText(parameters.getDataURI().getValue());
			// Get SPARQL query as String.
			String rules = getText(parameters.getRulesURI().getValue());
			// Fill in the graph URI in the WHERE statement of the SPARQL query.
			rules = fillInSessionID(parameters.getSessionId(), rules);
			// Upload the file to the database using a HTTP POST request.
			httpPOST(file, parameters.getDatabaseURI().getValue(), parameters.getSessionId(), getUsername(), getPassword());
			// Perform the SPARQL query against the file and return the result as a String.
			result = validateFile(parameters.getDatabaseURI().getValue(), rules, parameters.getOutputFormat().getValue().toString());		
			
			// Fill in the result in the response.
			response.setReport(result);	
			
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
		}
        
        return response.toString();
        
    }
    
	/**
     * Fill in the Graph URI in the rules file.
     * @param sessionID The session ID to be filled in.
     * @throws IOException 
     */
    private String fillInSessionID(String sessionID, String rules) {
 
		rules = rules.replaceAll("<@@@TOKEN-GRAPH@@@>", "<http://" + sessionID + ">");
		return rules;
		
	}
	
    /**
     * Upload the file to the server via a HTTP POST request
     * @param file The file as a string.
     * @param sessionID The session ID. This will also determine the graph URI.
     */
	private static void httpPOST(String file, String database, String sessionID, String username, String password) {
		String url = null;
		try {
			// Set credentials for server
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(
                  new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, "SPARQL"),
                  new UsernamePasswordCredentials("dba", "dba"));
			
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
			url = database + "-graph-crud-auth?graph-uri=http://" + sessionID;
			HttpPost request = new HttpPost(url);
//			request.setConfig(config);
			
			// Set the content and headers of the POST
			HttpEntity entity = new ByteArrayEntity(file.getBytes("UTF-8"));
			request.setEntity(entity);
			request.setHeader(HttpHeaders.ACCEPT, "*/*");
			request.setHeader(HttpHeaders.EXPECT, "100-continue");
	        
			// Execute the POST and print the response if not successful.
			CloseableHttpResponse response = client.execute(request);
			if (! (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 201 )) {
				System.out.println("Response: " + response.toString());
				System.out.println("Tried to upload to: " + url);
			}
			client.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Tried to upload to: " + url);
		}
	}
	
	/**
     * Perform SPARQL query on the file to validate it.
     */
    private String validateFile(String databaseURI, String rules, String outputFormat) {
    	
    	// Execute SPARQL query
        QueryExecution qe = QueryExecutionFactory.sparqlService(databaseURI, rules);
    	ResultSet results = qe.execSelect();
        String result = new String();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
        	if ( outputFormat == "XML" ) {
        		// Output the results as XML
        		ResultSetFormatter.outputAsXML(stream, results);
        	}  else if ( outputFormat == "JSON" ) {
        		// Output the results as JSON
        		ResultSetFormatter.outputAsJSON(stream, results);
         	} else if ( outputFormat == "TSV" ) {
         	// Output the results as TSV
        		ResultSetFormatter.outputAsTSV(stream, results);
        	} else if ( outputFormat == "CSV" ) {
        		// Output the results as CSV
        		ResultSetFormatter.outputAsCSV(stream, results);
        	}
        	result = stream.toString("UTF-8");
        	
        } catch (Exception e) {
            System.out.println("Query error:"+e);
        } finally {
            qe.close();
        }
        return result;
        
	}

    private void getConfigurationValues() {
	    InputStream inputStream = null;
		
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			setUsername("username");
			setPassword("password");
 
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
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