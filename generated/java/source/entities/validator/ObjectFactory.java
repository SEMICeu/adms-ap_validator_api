
package entities.validator;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the entities.validator package. 
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

    private final static QName _ValidateRequestType_QNAME = new QName("http://entities/Validator", "ValidateRequestType");
    private final static QName _DefinitionRequestType_QNAME = new QName("http://entities/Validator", "DefinitionRequestType");
    private final static QName _DefinitionResponseType_QNAME = new QName("http://entities/Validator", "DefinitionResponseType");
    private final static QName _ValidateResponseType_QNAME = new QName("http://entities/Validator", "ValidateResponseType");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: entities.validator
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
     * Create an instance of {@link ValidateRequest }
     * 
     */
    public ValidateRequest createValidateRequest() {
        return new ValidateRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entities/Validator", name = "ValidateRequestType")
    public JAXBElement<ValidateRequest> createValidateRequestType(ValidateRequest value) {
        return new JAXBElement<ValidateRequest>(_ValidateRequestType_QNAME, ValidateRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Void }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entities/Validator", name = "DefinitionRequestType")
    public JAXBElement<Void> createDefinitionRequestType(Void value) {
        return new JAXBElement<Void>(_DefinitionRequestType_QNAME, Void.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetModuleDefinitionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entities/Validator", name = "DefinitionResponseType")
    public JAXBElement<GetModuleDefinitionResponse> createDefinitionResponseType(GetModuleDefinitionResponse value) {
        return new JAXBElement<GetModuleDefinitionResponse>(_DefinitionResponseType_QNAME, GetModuleDefinitionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entities/Validator", name = "ValidateResponseType")
    public JAXBElement<ValidateResponse> createValidateResponseType(ValidateResponse value) {
        return new JAXBElement<ValidateResponse>(_ValidateResponseType_QNAME, ValidateResponse.class, null, value);
    }

}
