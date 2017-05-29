# adms-ap_validator_api

This API allows to performs the validation of <a href="https://joinup.ec.europa.eu/asset/dcat_application_profile/description">ADMS-AP</a> rdf files.
Validation is performed via a API operation which loads a file (Turtle, RDF/XML, N-triples, JSON-LD) as graph in a triplestore and it queries the triple store with a SPARQL query.
The output of the validation can be in XML (HTML with xslt transformation), JSON, CSV or TSV.

<h2>Rules</h2>

The SPARQL query contains several rules which are based on those available here:
<a href="https://joinup.ec.europa.eu/asset/dcat_application_profile/asset_release/dcat-application-profile-data-portals-europe-final">DCAT-AP Final 1.1</a>

Each rule is a SPARQL query which has been documented with <a href="https://github.com/ldodds/sparql-doc">sparql-doc</a> annotations and validated with the <a href="http://www.sparql.org/query-validator.html">online sparql validator</a>.

When a rule is validated it is added to the dcat-ap.rq file (locates inside the pages folder) which is then loaded into the the web form via the dcat-ap_validator.js file.

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
  <li>the type, name, usage, kind and description of the parameters to be provided to the validate function.</li>
 </ol>
  
<h2>User guide - validate</h2>

<h3>Input</h3>

As parameters, please provide:
    <ol>
    <li>the URL of the file to be validated,</li>
    <li>the URL of the database to query,</li>
    <li>the URL of the rules to be used to validate,</li>
    <li>optionally the session ID, and </li>
    <li>optionally the format in which you want the output to be provided. Possible values are: XML, JSON, TSV and CSV. If not provided, the ouput will be in XML format.</li>
    </ol>

<h3>Output</h3>

The output of the validate operation is a string containing the XML (HTML with xslt transformation), JSON, CSV or TSV validation result. The output format depends on the provided setting of the "outputFormat" paramater when invoking the operation. If no specification is made, the validation result will be provided as XML.


<h2>Licence</h2>
This software is released with EUPL licence: https://joinup.ec.europa.eu/community/eupl/home
