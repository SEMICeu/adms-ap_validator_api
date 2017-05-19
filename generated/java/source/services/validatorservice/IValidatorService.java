package services.validatorservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.2
 * 2017-05-19T14:27:10.778+02:00
 * Generated source version: 3.0.2
 * 
 */
@WebService(targetNamespace = "http://services/ValidatorService/", name = "IValidatorService")
@XmlSeeAlso({entities.validator.ObjectFactory.class, com.gitb.core.v1.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface IValidatorService {

    @WebMethod
    @WebResult(name = "ValidateResponseType", targetNamespace = "http://entities/Validator", partName = "parameters")
    public entities.validator.ValidateResponse validate(
        @WebParam(partName = "parameters", name = "ValidateRequestType", targetNamespace = "http://entities/Validator")
        entities.validator.ValidateRequest parameters
    );

    @WebMethod
    @WebResult(name = "DefinitionResponseType", targetNamespace = "http://entities/Validator", partName = "parameters")
    public entities.validator.GetModuleDefinitionResponse getDefinition(
        @WebParam(partName = "parameters", name = "DefinitionRequestType", targetNamespace = "http://entities/Validator")
        entities.validator.Void parameters
    );
}
