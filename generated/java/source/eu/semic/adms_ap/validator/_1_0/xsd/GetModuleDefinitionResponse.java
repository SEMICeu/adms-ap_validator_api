
package eu.semic.adms_ap.validator._1_0.xsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.gitb.core.v1.ValidationModule;


/**
 * <p>Java class for GetModuleDefinitionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetModuleDefinitionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="module" type="{http://www.gitb.com/core/v1/}ValidationModule"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetModuleDefinitionResponse", propOrder = {
    "module"
})
public class GetModuleDefinitionResponse {

    @XmlElement(required = true)
    protected ValidationModule module;

    /**
     * Gets the value of the module property.
     * 
     * @return
     *     possible object is
     *     {@link ValidationModule }
     *     
     */
    public ValidationModule getModule() {
        return module;
    }

    /**
     * Sets the value of the module property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidationModule }
     *     
     */
    public void setModule(ValidationModule value) {
        this.module = value;
    }

}
