package services.validatorservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.2
 * 2017-05-17T12:09:22.746+02:00
 * Generated source version: 3.0.2
 * 
 */
@WebServiceClient(name = "ValidatorService", 
                  wsdlLocation = "file:/C:/Users/vandeloc/Documents/Documents/SEMIC/Eclipse%20workspace/APIval/src/main/resources/services/ValidatorService.wsdl",
                  targetNamespace = "http://services/ValidatorService/") 
public class ValidatorService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://services/ValidatorService/", "ValidatorService");
    public final static QName ValidatorServicePort = new QName("http://services/ValidatorService/", "ValidatorServicePort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/vandeloc/Documents/Documents/SEMIC/Eclipse%20workspace/APIval/src/main/resources/services/ValidatorService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ValidatorService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/vandeloc/Documents/Documents/SEMIC/Eclipse%20workspace/APIval/src/main/resources/services/ValidatorService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ValidatorService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ValidatorService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ValidatorService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ValidatorService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ValidatorService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ValidatorService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    

    /**
     *
     * @return
     *     returns IValidatorService
     */
    @WebEndpoint(name = "ValidatorServicePort")
    public IValidatorService getValidatorServicePort() {
        return super.getPort(ValidatorServicePort, IValidatorService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IValidatorService
     */
    @WebEndpoint(name = "ValidatorServicePort")
    public IValidatorService getValidatorServicePort(WebServiceFeature... features) {
        return super.getPort(ValidatorServicePort, IValidatorService.class, features);
    }

}