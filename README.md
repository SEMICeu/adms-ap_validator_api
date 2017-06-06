# adms-ap_validator_api

This API allows to perform the validation of <a href="https://joinup.ec.europa.eu/asset/dcat_application_profile/description">ADMS-AP</a> RDF files.
Validation is performed via an API operation which loads a file (Turtle, RDF/XML, N-triples, JSON-LD) as graph in a triple store and it queries the triple store with a SPARQL query.
The output of the validation can be in XML (HTML with XSLT transformation), JSON, CSV or TSV.

<h2>System requirements</h2>

* JDK 8 (minimum)
* Tomcat 7 (minimum)
* Virtuoso 07.10.3207 (minimum)

<h2>The Source Structure</h2>

* /src/test/resources - SOAP UI Project to test the web service.
* /src/main/resources/config.properties - File containing the authentication to virtuoso.
* /src/main/resources/services/ValidatorService.wsdl - The wsdl file containing the definition of the API.
* /src/main/resources/entities - XML Schemas used by the wsdl file.
* /src/main/java/service/ValidatorServiceImpl.java - The webservice business logic.
* /target/apidocs - HTML documentation of the source code.
* /target/ADMS-AP_Validator_Service.war - War file to be deployed on an application server.

<h2>Rules</h2>

The SPARQL query contains several rules which are based on those available here:
<a href="https://joinup.ec.europa.eu/asset/dcat_application_profile/asset_release/dcat-application-profile-data-portals-europe-final">DCAT-AP Final 1.1</a>

<h2>User guide - get definition</h2>

Launch the getDefinition operation to get the definition of the validate operation.

<h3>Input</h3>

No input parameters need to be provided.

<h3>Output</h3>

This operation provides as an output a structured definition of the validate operation.
This structured definition includes:
 <ol>
  <li>the name of the service,</li>
  <li>the version of the service, and</li>
  <li>the name, type, usage, kind and description of the parameters to be provided to the validate operation.</li>
 </ol>
 
 Example:
 
 ```xml
 <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
   <soap:Body>
      <DefinitionResponseType xmlns="http://adms-ap.semic.eu/validator/1.0/xsd" xmlns:ns2="http://www.gitb.com/core/v1/">
         <module operation="V" id="ValidationService">
            <ns2:metadata>
               <ns2:name>ValidationService</ns2:name>
               <ns2:version>0.0.2</ns2:version>
            </ns2:metadata>
            <ns2:inputs>
               <ns2:param type="URI" name="URL rules" use="R" kind="SIMPLE" desc="The url to the rules to be used to validate."/>
               <ns2:param type="URI" name="URL database" use="R" kind="SIMPLE" desc="The url to the database which to query."/>
               <ns2:param type="URI" name="URL data" use="R" kind="SIMPLE" desc="The url to the data to upload and validate. This parameter is mandatory."/>
               <ns2:param type="long" name="SessionID" use="O" kind="SIMPLE" desc="The session ID."/>
               <ns2:param type="String" name="outputFormat" use="O" kind="SIMPLE" desc="The format in which you want the output to be provided. Possible values are: XML, JSON, TSV and CSV. If not provided, the ouput will be in XML format."/>
            </ns2:inputs>
         </module>
      </DefinitionResponseType>
   </soap:Body>
</soap:Envelope>
 ```
  
<h2>User guide - validate</h2>

Launch the validate operation to validate a file using the SPARQL query (see [Rules](.#Rules)).

<h3>Input</h3>

As parameters, please provide:
    <ol>
    <li>the URL of the file to be validated,</li>
    <li>the URL of the database to query,</li>
    <li>the URL of the rules to be used to validate,</li>
    </ol>
    
Optionally, the following parameters may be provided:
    <ol>
    <li>the session ID, and </li>
    <li>the format in which you want the output to be provided. Possible values are: XML, JSON, TSV and CSV. If not provided, the output will be in XML format.</li>
    </ol>

Example:

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://adms-ap.semic.eu/validator/1.0/xsd" xmlns:ns="http://adms-ap.semic.eu/validator/1.0/xsd/2">
   <soapenv:Header/>
   <soapenv:Body>
      <xsd:ValidateRequestType>
         <xsd:rulesURI embeddingMethod="URI">
            <ns:value>http://localhost:8890/sparql.txt</ns:value>
         </xsd:rulesURI>
         <xsd:databaseURI embeddingMethod="URI">
            <ns:value>http://localhost:8890/sparql</ns:value>
         </xsd:databaseURI>
         <xsd:dataURI embeddingMethod="URI">
            <ns:value>http://localhost:8890/data.ttl</ns:value>
         </xsd:dataURI>
         <!--Optional:-->
         <xsd:sessionID>?</xsd:sessionID>
         <!--Optional:-->
         <xsd:outputFormat>
            <ns:value>XML</ns:value>
         </xsd:outputFormat>
      </xsd:ValidateRequestType>
   </soapenv:Body>
</soapenv:Envelope>
```

<h3>Output</h3>

The output of the validate operation exists of:
   <ol>
   <li>a string containing the XML, JSON, CSV or TSV validation result. The output format depends on the provided setting of the "outputFormat" paramater when invoking the operation. If no specification is made, the validation result will be provided as XML.</li>
   <li>your sessionID, and</li>
   <li>the amount of results.</li>
   </ol>

Example:

```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
   <soap:Body>
      <ns2:ValidateResponseType xmlns="http://adms-ap.semic.eu/validator/1.0/xsd/2" xmlns:ns2="http://adms-ap.semic.eu/validator/1.0/xsd" xmlns:ns3="http://www.gitb.com/core/v1/">
         <ns2:sessionID>1496402407312</ns2:sessionID>
         <ns2:ResultsCount>1</ns2:ResultsCount>
         <ns2:report><![CDATA[<?xml version="1.0"?>
<sparql xmlns="http://www.w3.org/2005/sparql-results#">
  <head>
    <variable name="Class_Name"/>
    <variable name="Rule_ID"/>
    <variable name="Rule_Severity"/>
    <variable name="Rule_Description"/>
    <variable name="Message"/>
    <variable name="Subject"/>
    <variable name="Predicate"/>
    <variable name="Object"/>
  </head>
  <results>
    <result>
      <binding name="Class_Name">
        <literal>foaf:Agent</literal>
      </binding>
      <binding name="Rule_ID">
        <literal datatype="http://www.w3.org/2001/XMLSchema#integer">95</literal>
      </binding>
      <binding name="Rule_Severity">
        <literal>error</literal>
      </binding>
      <binding name="Rule_Description">
        <literal>foaf:name is a required property for foaf:Agent.</literal>
      </binding>
      <binding name="Message">
        <literal>The foaf:Agent http://www.oepm.es/cs/OEPMSite/contenidos/Folletos/08-la-patente-europea.html does not have a foaf:name property.</literal>
      </binding>
      <binding name="Subject">
        <uri>http://www.oepm.es/cs/OEPMSite/contenidos/Folletos/08-la-patente-europea.html</uri>
      </binding>
      <binding name="Predicate">
        <uri>http://xmlns.com/foaf/0.1/name</uri>
      </binding>
    </result>
  </results>
</sparql>]]></ns2:report>
      </ns2:ValidateResponseType>
   </soap:Body>
</soap:Envelope>
```

<h2>Licence</h2>
This software is released with EUPL licence: https://joinup.ec.europa.eu/community/eupl/home
