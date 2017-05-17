
package entities.validator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.gitb.core.v1.AnyContent;


/**
 * <p>Java class for ValidateRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidateRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sessionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataURI" type="{http://www.gitb.com/core/v1/}AnyContent"/>
 *         &lt;element name="rulesURI" type="{http://www.gitb.com/core/v1/}AnyContent"/>
 *         &lt;element name="databaseURI" type="{http://www.gitb.com/core/v1/}AnyContent"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidateRequest", propOrder = {
    "sessionId",
    "dataURI",
    "rulesURI",
    "databaseURI"
})
public class ValidateRequest {

    protected String sessionId;
    @XmlElement(required = true)
    protected AnyContent dataURI;
    @XmlElement(required = true)
    protected AnyContent rulesURI;
    @XmlElement(required = true)
    protected AnyContent databaseURI;

    /**
     * Gets the value of the sessionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets the value of the sessionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionId(String value) {
        this.sessionId = value;
    }

    /**
     * Gets the value of the dataURI property.
     * 
     * @return
     *     possible object is
     *     {@link AnyContent }
     *     
     */
    public AnyContent getDataURI() {
        return dataURI;
    }

    /**
     * Sets the value of the dataURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyContent }
     *     
     */
    public void setDataURI(AnyContent value) {
        this.dataURI = value;
    }

    /**
     * Gets the value of the rulesURI property.
     * 
     * @return
     *     possible object is
     *     {@link AnyContent }
     *     
     */
    public AnyContent getRulesURI() {
        return rulesURI;
    }

    /**
     * Sets the value of the rulesURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyContent }
     *     
     */
    public void setRulesURI(AnyContent value) {
        this.rulesURI = value;
    }

    /**
     * Gets the value of the databaseURI property.
     * 
     * @return
     *     possible object is
     *     {@link AnyContent }
     *     
     */
    public AnyContent getDatabaseURI() {
        return databaseURI;
    }

    /**
     * Sets the value of the databaseURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyContent }
     *     
     */
    public void setDatabaseURI(AnyContent value) {
        this.databaseURI = value;
    }

}
