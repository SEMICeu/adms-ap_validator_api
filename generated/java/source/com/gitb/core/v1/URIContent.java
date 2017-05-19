
package com.gitb.core.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for URIContent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="URIContent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *       &lt;/sequence>
 *       &lt;attribute name="embeddingMethod" type="{http://www.gitb.com/core/v1/}ValueEmbeddingEnumeration" default="URI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "URIContent", propOrder = {
    "value"
})
public class URIContent {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String value;
    @XmlAttribute(name = "embeddingMethod")
    protected ValueEmbeddingEnumeration embeddingMethod;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the embeddingMethod property.
     * 
     * @return
     *     possible object is
     *     {@link ValueEmbeddingEnumeration }
     *     
     */
    public ValueEmbeddingEnumeration getEmbeddingMethod() {
        if (embeddingMethod == null) {
            return ValueEmbeddingEnumeration.URI;
        } else {
            return embeddingMethod;
        }
    }

    /**
     * Sets the value of the embeddingMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueEmbeddingEnumeration }
     *     
     */
    public void setEmbeddingMethod(ValueEmbeddingEnumeration value) {
        this.embeddingMethod = value;
    }

}
