
package com.gitb.core.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AnyContent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AnyContent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="item" type="{http://www.gitb.com/core/v1/}AnyContent" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="value" type="{http://www.gitb.com/core/v1/}StringUriOrBase64Type" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="embeddingMethod" type="{http://www.gitb.com/core/v1/}ValueEmbeddingEnumeration" default="BASE64" />
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="encoding" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnyContent", propOrder = {
    "item",
    "value"
})
public class AnyContent {

    protected List<AnyContent> item;
    @XmlSchemaType(name = "anySimpleType")
    protected String value;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "embeddingMethod")
    protected ValueEmbeddingEnumeration embeddingMethod;
    @XmlAttribute(name = "type")
    protected String type;
    @XmlAttribute(name = "encoding")
    protected String encoding;

    /**
     * Gets the value of the item property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnyContent }
     * 
     * 
     */
    public List<AnyContent> getItem() {
        if (item == null) {
            item = new ArrayList<AnyContent>();
        }
        return this.item;
    }

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
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
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
            return ValueEmbeddingEnumeration.BASE_64;
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

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the encoding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * Sets the value of the encoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncoding(String value) {
        this.encoding = value;
    }

}
