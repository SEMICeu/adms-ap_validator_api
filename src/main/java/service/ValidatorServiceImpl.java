package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.commons.io.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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

import entities.validator.GetModuleDefinitionResponse;
import entities.validator.ValidateRequest;
import entities.validator.ValidateResponse;
import entities.validator.Void;
import services.validatorservice.IValidatorService;

@WebService(serviceName="ValidatorService", endpointInterface="services.validatorservice.IValidatorService",
targetNamespace="http://services/ValidatorService/", portName="ValidatorServicePort", name="ValidatorServiceImpl")
public class ValidatorServiceImpl implements IValidatorService {
	
    private static final int BUFFER_SIZE = 4096;

    /**
     * Get the module definition.
     */
	@Override
	public GetModuleDefinitionResponse getDefinition(@WebParam(name = "GetModuleDefinitionRequest", targetNamespace = "http://www.gitb.com/vs/v1/", partName = "parameters") Void aVoid) {
        GetModuleDefinitionResponse response = new GetModuleDefinitionResponse();
        response.setModule(new ValidationModule());
        response.getModule().setId("ValidationService");
        response.getModule().setOperation("V");
        response.getModule().setMetadata(new Metadata());
        response.getModule().getMetadata().setName("ValidationService");
        response.getModule().getMetadata().setVersion("0.0.1");
        response.getModule().setInputs(new TypedParameters());
        TypedParameter rulesInput =  new TypedParameter();
        rulesInput.setName("url");
        rulesInput.setType("URI");
        rulesInput.setUse(UsageEnumeration.R);
        rulesInput.setKind(ConfigurationType.SIMPLE);
        rulesInput.setDesc("The url to the rules to be used to validate.");
        response.getModule().getInputs().getParam().add(rulesInput);
        TypedParameter fileInput =  new TypedParameter();
        fileInput.setName("url");
        fileInput.setType("URI");
        fileInput.setUse(UsageEnumeration.R);
        fileInput.setKind(ConfigurationType.SIMPLE);
        fileInput.setDesc("The url to the data to upload and validate.");
        response.getModule().getInputs().getParam().add(fileInput);
        TypedParameter databaseInput =  new TypedParameter();
        databaseInput.setName("url");
        databaseInput.setType("URI");
        databaseInput.setUse(UsageEnumeration.R);
        databaseInput.setKind(ConfigurationType.SIMPLE);
        databaseInput.setDesc("The url to the database which to query.");
        response.getModule().getInputs().getParam().add(databaseInput);
        TypedParameter sessionIDInput =  new TypedParameter();
        sessionIDInput.setName("sessionID");
        sessionIDInput.setType("long");
        sessionIDInput.setUse(UsageEnumeration.O);
        sessionIDInput.setKind(ConfigurationType.SIMPLE);
        sessionIDInput.setDesc("The session ID. This parameter is optional.");
        response.getModule().getInputs().getParam().add(sessionIDInput);
        return response;
	}

    /**
     * Download and validate a file against a sparql query (rules).
     * @param parameters Parameters for the validation.
     * @param parameters.getSessionID() The session ID.
     * @param parameters.getDataURI() The URI of the data to be downloaded and validated.
     * @param parameters.getRulesURI() The URI of the rules to be used for the validation.
     * @param parameters.getDatabaseURI() The URI of the database which to query.
     */
	@Override
	public ValidateResponse validate(@WebParam(name = "ValidateRequest", targetNamespace = "http://www.gitb.com/vs/v1/", partName = "parameters") ValidateRequest parameters) {		
		if ( parameters.getSessionId() == null || parameters.getSessionId().toString().equalsIgnoreCase("?") ) {
			parameters.setSessionId( String.valueOf( new Timestamp( (new Date()).getTime() ).getTime() ) );
		}
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
//		System.out.println("Current relative path is: " + s);
		

		try {
			String fileName = downloadFile(parameters.getDataURI().getValue(), "C:\\Users\\vandeloc");
			String rules = getText(parameters.getRulesURI().getValue());
			rules = fillInSessionID(parameters.getSessionId(), rules);
//			httpPut("C:\\Users\\vandeloc\\" + fileName);
			validateFile(parameters.getDatabaseURI().getValue(), rules);		
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		ValidateResponse response = new ValidateResponse();
		response.setReport("The report will be provided here.");		
		return response;
	}
	
	/**
     * Perform SPARQL query on the file to validate it.
     */
    private void validateFile(String databaseURI, String rules) {
    	System.out.println(rules);
    	String service = "http://localhost:8890/sparql";
        String query = "SELECT * FROM <http://localhost:8890/try> WHERE {?s ?p ?o}";
        QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
        try {
           ResultSet results = qe.execSelect();
           ResultSetFormatter.outputAsXML(System.out, results);
        } catch (Exception e) {
            System.out.println("Query error:"+e);
        } finally {
            qe.close();
        }
	}

	/**
     * Fill in the session ID in the rules file.
     * @param sessionID The session ID to be filled in.
     * @throws IOException 
     */
    private String fillInSessionID(String sessionID, String rules) throws IOException {
		rules = rules.replaceAll("<@@@TOKEN-GRAPH@@@>", "<" + sessionID + ">");
		return rules;
	}
    
	/**
     * Downloads the content of a file to a string from a url.
     * @param fileURL HTTP URL of the file to be downloaded.
     * @throws IOException 
     */
    public static String getText(String fileURL) throws Exception {
    	String ls = System.getProperty("line.separator");
        URL website = new URL(fileURL);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                    connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        	response.append(ls);
        }

        in.close();

        return response.toString();
    }
    

	/**
     * Downloads a file from a URL.
     * @param fileURL HTTP URL of the file to be downloaded
     * @param saveDir path of the directory to save the file
     * @throws IOException
     */
    public static String downloadFile(String fileURL, String saveDir)
            throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();
        String fileName = "";
        
        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String disposition = httpConn.getHeaderField("Content-Disposition");
 
            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                        fileURL.length());
            }
 
            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = saveDir + File.separator + fileName;
             
            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
 
            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
 
            outputStream.close();
            inputStream.close();
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
        
        return fileName;
    }
    
    public static void httpPut(String fileName) {
        try {   	
          	CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(
                    new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, "SPARQL"),
                    new UsernamePasswordCredentials("dba", "dba"));
            
            CloseableHttpClient client = HttpClients.custom()
                    .setDefaultCredentialsProvider(credsProvider)
                    .build();
        	
            HttpPost httpPost = new HttpPost("http://localhost:8890/sparql-graph-crud-auth");
        	
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			System.out.println("location of file: " + fileName);
			File file = new File(fileName);
			builder.addPart("file", new FileBody(file));
//		    builder.addBinaryBody("file", file, ContentType.create("application/rdf+xml"), file.getName());
//		    builder.addTextBody(file.getName(), Files.toString(file, Charsets.UTF_8), ContentType.create("application/rdf+xml"));
		    builder.addPart("named-graph-uri", new StringBody("http://localhost:8890/try", Charsets.UTF_8 ));
//		    builder.addTextBody("default-graph-uri", "");
		    HttpEntity multipart = builder.build();
		 
		    httpPost.setEntity(multipart);
		    System.out.println(EntityUtils.toString(multipart));
			CloseableHttpResponse response = client.execute(httpPost);
			client.close();
			System.out.println(response.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}