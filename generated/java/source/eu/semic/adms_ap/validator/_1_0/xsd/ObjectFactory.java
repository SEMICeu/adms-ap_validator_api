
package eu.semic.adms_ap.validator._1_0.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the eu.semic.adms_ap.validator._1_0.xsd package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DefinitionRequestType_QNAME = new QName("http://adms-ap.semic.eu/validator/1.0/xsd", "DefinitionRequestType");
    private final static QName _ValidateResponseType_QNAME = new QName("http://adms-ap.semic.eu/validator/1.0/xsd", "ValidateResponseType");
    private final static QName _DefinitionResponseType_QNAME = new QName("http://adms-ap.semic.eu/validator/1.0/xsd", "DefinitionResponseType");
    private final static QName _MissingParameterException_QNAME = new QName("http://adms-ap.semic.eu/validator/1.0/xsd", "MissingParameterException");
    private final static QName _ValidateRequestType_QNAME = new QName("http://adms-ap.semic.eu/validator/1.0/xsd", "ValidateRequestType");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: eu.semic.adms_ap.validator._1_0.xsd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Void }
     * 
     */
    public Void createVoid() {
        return new Void();
    }

    /**
     * Create an instance of {@link ValidateResponse }
     * 
     */
    public ValidateResponse createValidateResponse() {
        return new ValidateResponse();
    }

    /**
     * Create an instance of {@link GetModuleDefinitionResponse }
     * 
     */
    public GetModuleDefinitionResponse createGetModuleDefinitionResponse() {
        return new GetModuleDefinitionResponse();
    }

    /**
     * Create an instance of {@link MissingParameterException }
     * 
     */
    public MissingParameterException createMissingParameterException() {
        return new MissingParameterException();
    }

    /**
     * Create an instance of {@link ValidateRequest }
     * 
     */
    public ValidateRequest createValidateRequest() {
        return new ValidateRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Void }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://adms-ap.semic.eu/validator/1.0/xsd", name = "DefinitionRequestType")
    public JAXBElement<Void> createDefinitionRequestType(Void value) {
        return new JAXBElement<Void>(_DefinitionRequestType_QNAME, Void.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://adms-ap.semic.eu/validator/1.0/xsd", name = "ValidateResponseType")
    public JAXBElement<ValidateResponse> createValidateResponseType(ValidateResponse value) {
        return new JAXBElement<ValidateResponse>(_ValidateResponseType_QNAME, ValidateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetModuleDefinitionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://adms-ap.semic.eu/validator/1.0/xsd", name = "DefinitionResponseType")
    public JAXBElement<GetModuleDefinitionResponse> createDefinitionResponseType(GetModuleDefinitionResponse value) {
        return new JAXBElement<GetModuleDefinitionResponse>(_DefinitionResponseType_QNAME, GetModuleDefinitionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MissingParameterException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://adms-ap.semic.eu/validator/1.0/xsd", name = "MissingParameterException")
    public JAXBElement<MissingParameterException> createMissingParameterException(MissingParameterException value) {
        return new JAXBElement<MissingParameterException>(_MissingParameterException_QNAME, MissingParameterException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://adms-ap.semic.eu/validator/1.0/xsd", name = "ValidateRequestType")
    public JAXBElement<ValidateRequest> createValidateRequestType(ValidateRequest value) {
        return new JAXBElement<ValidateRequest>(_ValidateRequestType_QNAME, ValidateRequest.class, null, value);
    }

}
