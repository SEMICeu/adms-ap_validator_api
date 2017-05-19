
package com.gitb.core.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for outputFormat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="outputFormat">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="value" type="{http://www.gitb.com/core/v1/}outputFormatEnumeration"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "outputFormat", propOrder = {
    "value"
})
public class OutputFormat {

    @XmlElement(required = true, defaultValue = "XML")
    @XmlSchemaType(name = "string")
    protected OutputFormatEnumeration value;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link OutputFormatEnumeration }
     *     
     */
    public OutputFormatEnumeration getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link OutputFormatEnumeration }
     *     
     */
    public void setValue(OutputFormatEnumeration value) {
        this.value = value;
    }

}
