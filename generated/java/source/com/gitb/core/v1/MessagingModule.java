
package com.gitb.core.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MessagingModule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MessagingModule">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gitb.com/core/v1/}TestModule">
 *       &lt;sequence>
 *         &lt;element name="actorConfigs" type="{http://www.gitb.com/core/v1/}ConfigurationParameters"/>
 *         &lt;element name="transactionConfigs" type="{http://www.gitb.com/core/v1/}ConfigurationParameters" minOccurs="0"/>
 *         &lt;element name="listenConfigs" type="{http://www.gitb.com/core/v1/}ConfigurationParameters" minOccurs="0"/>
 *         &lt;element name="receiveConfigs" type="{http://www.gitb.com/core/v1/}ConfigurationParameters" minOccurs="0"/>
 *         &lt;element name="sendConfigs" type="{http://www.gitb.com/core/v1/}ConfigurationParameters" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="isProxy" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessagingModule", propOrder = {
    "actorConfigs",
    "transactionConfigs",
    "listenConfigs",
    "receiveConfigs",
    "sendConfigs"
})
public class MessagingModule
    extends TestModule
{

    @XmlElement(required = true)
    protected ConfigurationParameters actorConfigs;
    protected ConfigurationParameters transactionConfigs;
    protected ConfigurationParameters listenConfigs;
    protected ConfigurationParameters receiveConfigs;
    protected ConfigurationParameters sendConfigs;
    @XmlAttribute(name = "isProxy")
    protected Boolean isProxy;

    /**
     * Gets the value of the actorConfigs property.
     * 
     * @return
     *     possible object is
     *     {@link ConfigurationParameters }
     *     
     */
    public ConfigurationParameters getActorConfigs() {
        return actorConfigs;
    }

    /**
     * Sets the value of the actorConfigs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigurationParameters }
     *     
     */
    public void setActorConfigs(ConfigurationParameters value) {
        this.actorConfigs = value;
    }

    /**
     * Gets the value of the transactionConfigs property.
     * 
     * @return
     *     possible object is
     *     {@link ConfigurationParameters }
     *     
     */
    public ConfigurationParameters getTransactionConfigs() {
        return transactionConfigs;
    }

    /**
     * Sets the value of the transactionConfigs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigurationParameters }
     *     
     */
    public void setTransactionConfigs(ConfigurationParameters value) {
        this.transactionConfigs = value;
    }

    /**
     * Gets the value of the listenConfigs property.
     * 
     * @return
     *     possible object is
     *     {@link ConfigurationParameters }
     *     
     */
    public ConfigurationParameters getListenConfigs() {
        return listenConfigs;
    }

    /**
     * Sets the value of the listenConfigs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigurationParameters }
     *     
     */
    public void setListenConfigs(ConfigurationParameters value) {
        this.listenConfigs = value;
    }

    /**
     * Gets the value of the receiveConfigs property.
     * 
     * @return
     *     possible object is
     *     {@link ConfigurationParameters }
     *     
     */
    public ConfigurationParameters getReceiveConfigs() {
        return receiveConfigs;
    }

    /**
     * Sets the value of the receiveConfigs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigurationParameters }
     *     
     */
    public void setReceiveConfigs(ConfigurationParameters value) {
        this.receiveConfigs = value;
    }

    /**
     * Gets the value of the sendConfigs property.
     * 
     * @return
     *     possible object is
     *     {@link ConfigurationParameters }
     *     
     */
    public ConfigurationParameters getSendConfigs() {
        return sendConfigs;
    }

    /**
     * Sets the value of the sendConfigs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigurationParameters }
     *     
     */
    public void setSendConfigs(ConfigurationParameters value) {
        this.sendConfigs = value;
    }

    /**
     * Gets the value of the isProxy property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsProxy() {
        if (isProxy == null) {
            return true;
        } else {
            return isProxy;
        }
    }

    /**
     * Sets the value of the isProxy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsProxy(Boolean value) {
        this.isProxy = value;
    }

}
