package eu.semic.adms_ap.validatorservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.2
 * 2017-05-29T15:49:30.246+02:00
 * Generated source version: 3.0.2
 * 
 */
@WebService(targetNamespace = "http://adms-ap.semic.eu/ValidatorService/", name = "IValidatorService")
@XmlSeeAlso({eu.semic.adms_ap.validator._1_0.xsd.ObjectFactory.class, com.gitb.core.v1.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface IValidatorService {

    @WebMethod
    @WebResult(name = "ValidateResponseType", targetNamespace = "http://adms-ap.semic.eu/validator/1.0/xsd", partName = "parameters")
    public eu.semic.adms_ap.validator._1_0.xsd.ValidateResponse validate(
        @WebParam(partName = "parameters", name = "ValidateRequestType", targetNamespace = "http://adms-ap.semic.eu/validator/1.0/xsd")
        eu.semic.adms_ap.validator._1_0.xsd.ValidateRequest parameters
    );

    @WebMethod
    @WebResult(name = "DefinitionResponseType", targetNamespace = "http://adms-ap.semic.eu/validator/1.0/xsd", partName = "parameters")
    public eu.semic.adms_ap.validator._1_0.xsd.GetModuleDefinitionResponse getDefinition(
        @WebParam(partName = "parameters", name = "DefinitionRequestType", targetNamespace = "http://adms-ap.semic.eu/validator/1.0/xsd")
        eu.semic.adms_ap.validator._1_0.xsd.Void parameters
    );
}
