
package com.gitb.tr.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DR">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gitb.com/tr/v1/}TestStepReportType">
 *       &lt;sequence>
 *         &lt;element name="decision" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DR", propOrder = {
    "decision"
})
public class DR
    extends TestStepReportType
{

    protected boolean decision;

    /**
     * Gets the value of the decision property.
     * 
     */
    public boolean isDecision() {
        return decision;
    }

    /**
     * Sets the value of the decision property.
     * 
     */
    public void setDecision(boolean value) {
        this.decision = value;
    }

}
