
package com.gitb.vs.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.gitb.tr.v1.TAR;


/**
 * <p>Java class for ValidateResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidateResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="report" type="{http://www.gitb.com/tr/v1/}TAR"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidateResponse", propOrder = {
    "report"
})
public class ValidateResponse {

    @XmlElement(required = true)
    protected TAR report;

    /**
     * Gets the value of the report property.
     * 
     * @return
     *     possible object is
     *     {@link TAR }
     *     
     */
    public TAR getReport() {
        return report;
    }

    /**
     * Sets the value of the report property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAR }
     *     
     */
    public void setReport(TAR value) {
        this.report = value;
    }

}
