
package com.gitb.tr.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import com.gitb.core.v1.AnyContent;


/**
 * <p>Java class for TAR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TAR">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gitb.com/tr/v1/}TestStepReportType">
 *       &lt;sequence>
 *         &lt;element name="overview" type="{http://www.gitb.com/tr/v1/}ValidationOverview" minOccurs="0"/>
 *         &lt;element name="counters" type="{http://www.gitb.com/tr/v1/}ValidationCounters" minOccurs="0"/>
 *         &lt;element name="context" type="{http://www.gitb.com/core/v1/}AnyContent" minOccurs="0"/>
 *         &lt;element name="reports" type="{http://www.gitb.com/tr/v1/}TestAssertionGroupReportsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TAR", propOrder = {
    "overview",
    "counters",
    "context",
    "reports"
})
public class TAR
    extends TestStepReportType
{

    protected ValidationOverview overview;
    protected ValidationCounters counters;
    protected AnyContent context;
    protected TestAssertionGroupReportsType reports;
    @XmlAttribute(name = "name")
    protected String name;

    /**
     * Gets the value of the overview property.
     * 
     * @return
     *     possible object is
     *     {@link ValidationOverview }
     *     
     */
    public ValidationOverview getOverview() {
        return overview;
    }

    /**
     * Sets the value of the overview property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidationOverview }
     *     
     */
    public void setOverview(ValidationOverview value) {
        this.overview = value;
    }

    /**
     * Gets the value of the counters property.
     * 
     * @return
     *     possible object is
     *     {@link ValidationCounters }
     *     
     */
    public ValidationCounters getCounters() {
        return counters;
    }

    /**
     * Sets the value of the counters property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidationCounters }
     *     
     */
    public void setCounters(ValidationCounters value) {
        this.counters = value;
    }

    /**
     * Gets the value of the context property.
     * 
     * @return
     *     possible object is
     *     {@link AnyContent }
     *     
     */
    public AnyContent getContext() {
        return context;
    }

    /**
     * Sets the value of the context property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyContent }
     *     
     */
    public void setContext(AnyContent value) {
        this.context = value;
    }

    /**
     * Gets the value of the reports property.
     * 
     * @return
     *     possible object is
     *     {@link TestAssertionGroupReportsType }
     *     
     */
    public TestAssertionGroupReportsType getReports() {
        return reports;
    }

    /**
     * Sets the value of the reports property.
     * 
     * @param value
     *     allowed object is
     *     {@link TestAssertionGroupReportsType }
     *     
     */
    public void setReports(TestAssertionGroupReportsType value) {
        this.reports = value;
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

}
