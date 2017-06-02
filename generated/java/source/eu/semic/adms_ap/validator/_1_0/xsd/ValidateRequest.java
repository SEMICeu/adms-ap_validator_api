
package eu.semic.adms_ap.validator._1_0.xsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="rulesURI" type="{http://adms-ap.semic.eu/validator/1.0/xsd}URIContent"/>
 *         &lt;element name="databaseURI" type="{http://adms-ap.semic.eu/validator/1.0/xsd}URIContent"/>
 *         &lt;element name="dataURI" type="{http://adms-ap.semic.eu/validator/1.0/xsd}URIContent"/>
 *         &lt;element name="sessionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outputFormat" type="{http://adms-ap.semic.eu/validator/1.0/xsd}outputFormat" minOccurs="0"/>
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
    "rulesURI",
    "databaseURI",
    "dataURI",
    "sessionID",
    "outputFormat"
})
public class ValidateRequest {

    @XmlElement(required = true)
    protected URIContent rulesURI;
    @XmlElement(required = true)
    protected URIContent databaseURI;
    @XmlElement(required = true)
    protected URIContent dataURI;
    protected String sessionID;
    protected OutputFormat outputFormat;

    /**
     * Gets the value of the rulesURI property.
     * 
     * @return
     *     possible object is
     *     {@link URIContent }
     *     
     */
    public URIContent getRulesURI() {
        return rulesURI;
    }

    /**
     * Sets the value of the rulesURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link URIContent }
     *     
     */
    public void setRulesURI(URIContent value) {
        this.rulesURI = value;
    }

    /**
     * Gets the value of the databaseURI property.
     * 
     * @return
     *     possible object is
     *     {@link URIContent }
     *     
     */
    public URIContent getDatabaseURI() {
        return databaseURI;
    }

    /**
     * Sets the value of the databaseURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link URIContent }
     *     
     */
    public void setDatabaseURI(URIContent value) {
        this.databaseURI = value;
    }

    /**
     * Gets the value of the dataURI property.
     * 
     * @return
     *     possible object is
     *     {@link URIContent }
     *     
     */
    public URIContent getDataURI() {
        return dataURI;
    }

    /**
     * Sets the value of the dataURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link URIContent }
     *     
     */
    public void setDataURI(URIContent value) {
        this.dataURI = value;
    }

    /**
     * Gets the value of the sessionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionID() {
        return sessionID;
    }

    /**
     * Sets the value of the sessionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionID(String value) {
        this.sessionID = value;
    }

    /**
     * Gets the value of the outputFormat property.
     * 
     * @return
     *     possible object is
     *     {@link OutputFormat }
     *     
     */
    public OutputFormat getOutputFormat() {
        return outputFormat;
    }

    /**
     * Sets the value of the outputFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link OutputFormat }
     *     
     */
    public void setOutputFormat(OutputFormat value) {
        this.outputFormat = value;
    }

}
