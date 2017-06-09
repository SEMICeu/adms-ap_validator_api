
package com.gitb.vs.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.gitb.vs.v1 package. 
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

    private final static QName _ValidationResponse_QNAME = new QName("http://www.gitb.com/vs/v1/", "ValidationResponse");
    private final static QName _GetModuleDefinitionResponse_QNAME = new QName("http://www.gitb.com/vs/v1/", "GetModuleDefinitionResponse");
    private final static QName _ValidateRequest_QNAME = new QName("http://www.gitb.com/vs/v1/", "ValidateRequest");
    private final static QName _GetModuleDefinitionRequest_QNAME = new QName("http://www.gitb.com/vs/v1/", "GetModuleDefinitionRequest");
    private final static QName _ADMSException_QNAME = new QName("http://www.gitb.com/vs/v1/", "ADMSException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gitb.vs.v1
     * 
     */
    public ObjectFactory() {
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
     * Create an instance of {@link ValidateResponse }
     * 
     */
    public ValidateResponse createValidateResponse() {
        return new ValidateResponse();
    }

    /**
     * Create an instance of {@link ADMSExceptionError }
     * 
     */
    public ADMSExceptionError createADMSExceptionError() {
        return new ADMSExceptionError();
    }

    /**
     * Create an instance of {@link Void }
     * 
     */
    public Void createVoid() {
        return new Void();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gitb.com/vs/v1/", name = "ValidationResponse")
    public JAXBElement<ValidateResponse> createValidationResponse(ValidateResponse value) {
        return new JAXBElement<ValidateResponse>(_ValidationResponse_QNAME, ValidateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetModuleDefinitionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gitb.com/vs/v1/", name = "GetModuleDefinitionResponse")
    public JAXBElement<GetModuleDefinitionResponse> createGetModuleDefinitionResponse(GetModuleDefinitionResponse value) {
        return new JAXBElement<GetModuleDefinitionResponse>(_GetModuleDefinitionResponse_QNAME, GetModuleDefinitionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gitb.com/vs/v1/", name = "ValidateRequest")
    public JAXBElement<ValidateRequest> createValidateRequest(ValidateRequest value) {
        return new JAXBElement<ValidateRequest>(_ValidateRequest_QNAME, ValidateRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Void }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gitb.com/vs/v1/", name = "GetModuleDefinitionRequest")
    public JAXBElement<Void> createGetModuleDefinitionRequest(Void value) {
        return new JAXBElement<Void>(_GetModuleDefinitionRequest_QNAME, Void.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ADMSExceptionError }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gitb.com/vs/v1/", name = "ADMSException")
    public JAXBElement<ADMSExceptionError> createADMSException(ADMSExceptionError value) {
        return new JAXBElement<ADMSExceptionError>(_ADMSException_QNAME, ADMSExceptionError.class, null, value);
    }

}
