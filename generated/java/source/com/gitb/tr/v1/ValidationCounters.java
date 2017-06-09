
package com.gitb.tr.v1;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValidationCounters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidationCounters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nrOfAssertions" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="nrOfErrors" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="nrOfWarnings" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidationCounters", propOrder = {
    "nrOfAssertions",
    "nrOfErrors",
    "nrOfWarnings"
})
public class ValidationCounters {

    protected BigInteger nrOfAssertions;
    protected BigInteger nrOfErrors;
    protected BigInteger nrOfWarnings;

    /**
     * Gets the value of the nrOfAssertions property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNrOfAssertions() {
        return nrOfAssertions;
    }

    /**
     * Sets the value of the nrOfAssertions property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNrOfAssertions(BigInteger value) {
        this.nrOfAssertions = value;
    }

    /**
     * Gets the value of the nrOfErrors property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNrOfErrors() {
        return nrOfErrors;
    }

    /**
     * Sets the value of the nrOfErrors property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNrOfErrors(BigInteger value) {
        this.nrOfErrors = value;
    }

    /**
     * Gets the value of the nrOfWarnings property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNrOfWarnings() {
        return nrOfWarnings;
    }

    /**
     * Sets the value of the nrOfWarnings property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNrOfWarnings(BigInteger value) {
        this.nrOfWarnings = value;
    }

}
