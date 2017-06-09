
package com.gitb.tr.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TestCaseReportType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TestCaseReportType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gitb.com/tr/v1/}TestStepReportType">
 *       &lt;sequence>
 *         &lt;element name="counters" type="{http://www.gitb.com/tr/v1/}ValidationCounters" minOccurs="0"/>
 *         &lt;element name="reports" type="{http://www.gitb.com/tr/v1/}TestStepReportType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TestCaseReportType", propOrder = {
    "counters",
    "reports"
})
public class TestCaseReportType
    extends TestStepReportType
{

    protected ValidationCounters counters;
    protected List<TestStepReportType> reports;

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
     * Gets the value of the reports property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reports property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReports().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TestStepReportType }
     * 
     * 
     */
    public List<TestStepReportType> getReports() {
        if (reports == null) {
            reports = new ArrayList<TestStepReportType>();
        }
        return this.reports;
    }

}
