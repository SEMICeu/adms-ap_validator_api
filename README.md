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

<h2>User guide - getModuleDefinition</h2>

Launch the getModuleDefinition operation to get the definition of the validate operation.

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
      <ns4:GetModuleDefinitionResponse xmlns:ns2="http://www.gitb.com/core/v1/" xmlns:ns3="http://www.gitb.com/tr/v1/" xmlns:ns4="http://www.gitb.com/vs/v1/">
         <module operation="V" id="ValidationService">
            <ns2:metadata>
               <ns2:name>ValidationService</ns2:name>
               <ns2:version>1.0.0</ns2:version>
            </ns2:metadata>
            <ns2:inputs>
               <ns2:param type="URI" name="rulesURI" use="R" kind="SIMPLE" desc="The url to the rules to be used to validate."/>
               <ns2:param type="URI" name="databaseURI" use="R" kind="SIMPLE" desc="The url to the database which to query."/>
               <ns2:param type="URI" name="dataURI" use="R" kind="SIMPLE" desc="The url to the data to upload and validate. This parameter is mandatory."/>
               <ns2:param type="long" name="SessionId" use="O" kind="SIMPLE" desc="The session ID."/>
            </ns2:inputs>
         </module>
      </ns4:GetModuleDefinitionResponse>
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
    <li>the session Id, this should be the current time in millisecond as a integer. If not filled in, the session Id will be automatically generated. </li>
    </ol>

Example:

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:v1="http://www.gitb.com/vs/v1/" xmlns:v11="http://www.gitb.com/core/v1/">
   <soapenv:Header/>
   <soapenv:Body>
      <v1:ValidateRequest>
         <sessionId>?</sessionId>
         <input name="rulesURI" embeddingMethod="URI" type="?" encoding="?">
            <v11:value>http://cpsv-ap.semic.eu/adms-ap_validator/adms-ap.txt</v11:value>
         </input>
         <input name="databaseURI" embeddingMethod="URI" type="?" encoding="?">
            <v11:value>http://cpsv-ap.semic.eu/sparql</v11:value>
         </input>
         <input name="dataURI" embeddingMethod="URI" type="?" encoding="?">
            <v11:value>http://cpsv-ap.semic.eu/adms-ap_validator/samples/sample-turtle2.ttl</v11:value>
         </input>
      </v1:ValidateRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

<h3>Output</h3>

The output of the validate operation exists of:
   <ol>
   <li>the date of validation,</li>
   <li>the overall result of the validation (success or failure),</li>
   <li>the number of errors,</li>
   <li>the number of warnings,</li>
   <li>the context of the validation (i.e. the file which has been validated), and</li>
   <li>the report, including the structured errors, warnings and info messages.</li>
   </ol>

Example:

```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
   <soap:Body>
      <ns4:ValidationResponse xmlns:ns2="http://www.gitb.com/core/v1/" xmlns:ns3="http://www.gitb.com/tr/v1/" xmlns:ns4="http://www.gitb.com/vs/v1/">
         <report>
            <ns3:date>2017-06-13T11:08:32.105+02:00</ns3:date>
            <ns3:result>FAILURE</ns3:result>
            <ns3:counters>
               <ns3:nrOfErrors>1</ns3:nrOfErrors>
               <ns3:nrOfWarnings>0</ns3:nrOfWarnings>
            </ns3:counters>
            <ns3:context>
               <ns2:value><![CDATA[@prefix dcat: <http://www.w3.org/ns/dcat#> .
@prefix dc: <http://purl.org/dc/terms/> .
@prefix ns0: <c> .
@prefix foaf: <http://xmlns.com/foaf/0.1/> .
@prefix v: <http://rdf.data-vocabulary.org/#> .
@prefix vcard: <http://www.w3.org/2006/vcard/ns#> .

<http://joinup.ec.europa.eu/unique_identifier>
  a dcat:Dataset ;
  dc:title "EIRA_v0.9.0_beta"@en ;
  dc:description """This Archi file provides the ArchiMate model of the European Interoperability Reference Architecture (EIRA). The EIRA has been developed in the context of Action 2.1 of the Interoperability Solutions for European Public Administrations (ISA) Programme. The EIRA is a reference architecture focused on the interoperability of digital public services. It is composed of the most important architecture building blocks needed to promote cross-border and cross-sector interactions between public administrations. It is based on the Service Oriented Architecture style and uses ArchiMate as a modelling notation. The EIRA implements the European Interoperability Framework (EIF) . 

The latest release of the EIRA is available on Joinup: https://joinup.ec.europa.eu/asset/eia/description"""@en ;
  ns0:omposedOf "" .

<http://joinup.ec.europa.eu/ABB165>
  a dcat:Dataset ;
  dc:type <http://data.europa.eu/eira/LegislationCatalogue> ;
  dc:title "Legislation Catalogue Solution BB"@en ;
  dc:description "A Legislation Catalogue is a set of legal documents."@en ;
  dcat:keyword "legislation" ;
  dcat:landingPage <https://joinup.ec.europa.eu/example/LegislationCatalogue> ;
  dc:modified "2015-12-15" ;
  dcat:distribution <https://joinup.ec.europa.eu/distribution>, <https://joinup.ec.europa.eu/distribution2> .

<http://data.europa.eu/eli/dir/2006/123/oj>
  a dcat:Dataset ;
  dc:type <http://data.europa.eu/eira/PublicPolicy>, <http://data.europa.eu/eira/BindingInstrument> ;
  dc:title "Services Directory"@en ;
  dc:description "The objective of the Services Directive is to realise the full potential of services markets in Europe by removing legal and administrative barriers to trade. The simplification measures introduced by the Directive have increased transparency and made it easier for businesses and consumers to provide or use services in the Single Market. The Directive was adopted in 2006 and implemented by all EU countries in 2009. The European Commission is now working with EU countries to further improve the Single Market for services."@en ;
  dcat:contactPoint <http://data.europa.eu/contact/mrx> ;
  dc:publisher <http://data.europa.eu/publisher/EP> ;
  dcat:keyword "public services", "european union" ;
  dcat:landingPage <http://ec.europa.eu/growth/single-market/services/services-directive/index_en.htm> ;
  dc:relation <http://data.europa.eu/URI/CPSVAP> .

<http://data.europa.eu/publisher/EP>
  a foaf:Agent ;
  foaf:name "European Parliament" .

<http://data.europa.eu/contact/mrx>
  a v:Kind ;
  vcard:fn "Mr X" ;
  vcard:hasEmail "mrx@host.com" .

<http://data.europa.eu/URI/CPSVAP>
  a dcat:Dataset ;
  dc:type <http://data.europa.eu/eira/CoreDataModel> ;
  dc:title "CPSV-AP"@en ;
  dc:description "The CPSV-AP has been defined as an Application Profile of the ISA² Core Public Service Vocabulary (CPSV). An Application Profile is a specification that re-uses terms from one or more base standards, adding more specificity by identifying mandatory, recommended and optional elements to be used for a particular application, as well as recommendations for controlled vocabularies to be used."@en ;
  dcat:contactPoint <http://data.europa.eu/contact/mrx> ;
  dc:publisher <http://data.europa.eu/publisher/ISA> ;
  dcat:keyword "services directory", "businesses" ;
  dcat:landingPage <https://joinup.ec.europa.eu/asset/cpsv-ap/asset_release/core-public-service-vocabulary-application-profile-v100> ;
  dcat:distribution <https://joinup.ec.europa.eu/system/files/project/d02.02_-_definition_and_development_of_a_data_model_for_description_of_the_services_related_to_kbe-v1.05.docx> .

<http://data.europa.eu/publisher/ISA>
  a foaf:Agent ;
  foaf:name "ISA" .]]></ns2:value>
            </ns3:context>
            <ns3:reports>
               <ns3:error xsi:type="ns3:BAR" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                  <ns3:assertionID>1</ns3:assertionID>
                  <ns3:description>The dcat:Dataset http://joinup.ec.europa.eu/unique_identifier does not have a dcat:contactPoint property.</ns3:description>
                  <ns3:location>http://joinup.ec.europa.eu/unique_identifier</ns3:location>
                  <ns3:test>dcat:contactPoint is a required property for dcat:Dataset.</ns3:test>
                  <ns3:type>http://www.w3.org/ns/dcat#contactPoint</ns3:type>
                  <ns3:value>NA</ns3:value>
               </ns3:error>
            </ns3:reports>
         </report>
      </ns4:ValidationResponse>
   </soap:Body>
</soap:Envelope>
```

<h2>Licence</h2>
This software is released with EUPL licence: https://joinup.ec.europa.eu/community/eupl/home
