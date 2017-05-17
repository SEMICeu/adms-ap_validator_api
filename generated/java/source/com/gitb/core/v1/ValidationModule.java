
package com.gitb.core.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValidationModule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidationModule">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gitb.com/core/v1/}TestModule">
 *       &lt;sequence>
 *         &lt;element name="configs" type="{http://www.gitb.com/core/v1/}ConfigurationParameters" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="operation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidationModule", propOrder = {
    "configs"
})
public class ValidationModule
    extends TestModule
{

    protected ConfigurationParameters configs;
    @XmlAttribute(name = "operation")
    protected String operation;

    /**
     * Gets the value of the configs property.
     * 
     * @return
     *     possible object is
     *     {@link ConfigurationParameters }
     *     
     */
    public ConfigurationParameters getConfigs() {
        return configs;
    }

    /**
     * Sets the value of the configs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigurationParameters }
     *     
     */
    public void setConfigs(ConfigurationParameters value) {
        this.configs = value;
    }

    /**
     * Gets the value of the operation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Sets the value of the operation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperation(String value) {
        this.operation = value;
    }

}
