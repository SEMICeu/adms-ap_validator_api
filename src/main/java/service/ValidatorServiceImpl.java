package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.Date;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
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

import entities.validator.GetModuleDefinitionResponse;
import entities.validator.ValidateRequest;
import entities.validator.ValidateResponse;
import entities.validator.Void;
import services.validatorservice.IValidatorService;

@WebService(serviceName="ValidatorService", endpointInterface="services.validatorservice.IValidatorService",
targetNamespace="http://services/ValidatorService/", portName="ValidatorServicePort", name="ValidatorServiceImpl")
public class ValidatorServiceImpl implements IValidatorService {

    /**
     * Get the module definition.
     */
	@Override
	public GetModuleDefinitionResponse getDefinition(@WebParam(name = "GetModuleDefinitionRequest",
	targetNamespace = "http://www.gitb.com/vs/v1/", partName = "parameters") Void aVoid) {
		
        GetModuleDefinitionResponse response = new GetModuleDefinitionResponse();
        response.setModule(new ValidationModule());
        response.getModule().setId("ValidationService");
        response.getModule().setOperation("V");
        response.getModule().setMetadata(new Metadata());
        response.getModule().getMetadata().setName("ValidationService");
        response.getModule().getMetadata().setVersion("0.0.1");
        response.getModule().setInputs(new TypedParameters());
        response.getModule().getInputs().getParam().add(setModuleDefinitionResponse(
        		"url", "URI", UsageEnumeration.R, ConfigurationType.SIMPLE, "The url to the rules to be used to validate."));
        response.getModule().getInputs().getParam().add(setModuleDefinitionResponse(
        		"url", "URI", UsageEnumeration.R, ConfigurationType.SIMPLE, "The url to the data to upload and validate."));
        response.getModule().getInputs().getParam().add(setModuleDefinitionResponse(
        		"url", "URI", UsageEnumeration.R, ConfigurationType.SIMPLE, "The url to the database which to query."));
        response.getModule().getInputs().getParam().add(setModuleDefinitionResponse(
        		"sessionID", "long", UsageEnumeration.O, ConfigurationType.SIMPLE, "The session ID. This parameter is optional."));
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
     * Download and validate a file against a sparql query (rules).
     * @param parameters Parameters for the validation.
     * @param parameters.getSessionID() The session ID.
     * @param parameters.getDataURI() The URI of the data to be downloaded and validated.
     * @param parameters.getRulesURI() The URI of the rules to be used for the validation.
     * @param parameters.getDatabaseURI() The URI of the database which to query.
     */
	@Override
	public ValidateResponse validate(@WebParam(name = "ValidateRequest", targetNamespace = "http://www.gitb.com/vs/v1/",
	partName = "parameters") ValidateRequest parameters) {
		
		if ( parameters.getSessionId() == null || parameters.getSessionId().toString().equalsIgnoreCase("?") 
				|| parameters.getSessionId().toString().equalsIgnoreCase("")) {
			parameters.setSessionId( String.valueOf( new Timestamp( (new Date()).getTime() ).getTime() ) );
		}
		
		String result = new String();
		try {
			String file = getText(parameters.getDataURI().getValue());
			String rules = getText(parameters.getRulesURI().getValue());
			rules = fillInSessionID(parameters.getSessionId(), rules);
			httpPut(file, parameters.getSessionId());
			result = validateFile(parameters.getDatabaseURI().getValue(), rules);		
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		ValidateResponse response = new ValidateResponse();
		response.setReport(result);		
		return response;
		
	}
	
	private static void httpPut(String xml,  String sessionID) {
		try {
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(
                  new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, "SPARQL"),
                  new UsernamePasswordCredentials("dba", "dba"));
			
			CloseableHttpClient client = HttpClients.custom()
	                  .setDefaultCredentialsProvider(credsProvider)
	                  .build();
			
//			URI proxyURI = new URI("http://localhost:8888");
//			URI targetURI = new URI("http://localhost:8890");
			
//			HttpHost proxy = new HttpHost(proxyURI.getHost(), proxyURI.getPort(), proxyURI.getScheme());
//			HttpHost target = new HttpHost(targetURI.getHost(), targetURI.getPort(), targetURI.getScheme());
//			
//			RequestConfig config = RequestConfig.custom()
//                    .setProxy(proxy)
//                    .build();
			
			System.out.println("sessionID" + sessionID);
			String url = "http://localhost:8890/sparql-graph-crud-auth?graph-uri=http://localhost:8890/" + sessionID;
			System.out.println(url);
			HttpPost request = new HttpPost(url);
//			request.setConfig(config);
			
			HttpEntity entity = new ByteArrayEntity(xml.getBytes("UTF-8"));
			request.setEntity(entity);
			request.setHeader(HttpHeaders.ACCEPT, "*/*");
			request.setHeader(HttpHeaders.EXPECT, "100-continue");
	        
			client.execute(request);
			client.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Perform SPARQL query on the file to validate it.
     */
    private String validateFile(String databaseURI, String rules) {
    	
    	System.out.println(rules);
    	
        QueryExecution qe = QueryExecutionFactory.sparqlService(databaseURI, rules);
        String result = new String();
        try {
           ResultSet results = qe.execSelect();
           result = ResultSetFormatter.asXMLString(results);
        } catch (Exception e) {
            System.out.println("Query error:"+e);
        } finally {
            qe.close();
        }
        return result;
        
	}

	/**
     * Fill in the session ID in the rules file.
     * @param sessionID The session ID to be filled in.
     * @throws IOException 
     */
    private String fillInSessionID(String sessionID, String rules) throws IOException {
    	
		rules = rules.replaceAll("<@@@TOKEN-GRAPH@@@>", "<http://localhost:8890/" + sessionID + ">");
		return rules;
		
	}
    
	/**
     * Downloads the content of a file to a string from a url.
     * @param fileURL HTTP URL of the file to be downloaded.
     * @throws IOException 
     */
    private static String getText(String fileURL) throws Exception {
    	
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
    
}