
package com.gitb.tr.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TestAssertionGroupReportsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TestAssertionGroupReportsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="reports" type="{http://www.gitb.com/tr/v1/}TAR" maxOccurs="unbounded"/>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element name="info" type="{http://www.gitb.com/tr/v1/}TestAssertionReportType"/>
 *           &lt;element name="warning" type="{http://www.gitb.com/tr/v1/}TestAssertionReportType"/>
 *           &lt;element name="error" type="{http://www.gitb.com/tr/v1/}TestAssertionReportType"/>
 *         &lt;/choice>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TestAssertionGroupReportsType", propOrder = {
    "reports",
    "infoOrWarningOrError"
})
public class TestAssertionGroupReportsType {

    protected List<TAR> reports;
    @XmlElementRefs({
        @XmlElementRef(name = "warning", namespace = "http://www.gitb.com/tr/v1/", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "info", namespace = "http://www.gitb.com/tr/v1/", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "error", namespace = "http://www.gitb.com/tr/v1/", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<TestAssertionReportType>> infoOrWarningOrError;

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
     * {@link TAR }
     * 
     * 
     */
    public List<TAR> getReports() {
        if (reports == null) {
            reports = new ArrayList<TAR>();
        }
        return this.reports;
    }

    /**
     * Gets the value of the infoOrWarningOrError property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the infoOrWarningOrError property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInfoOrWarningOrError().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link TestAssertionReportType }{@code >}
     * {@link JAXBElement }{@code <}{@link TestAssertionReportType }{@code >}
     * {@link JAXBElement }{@code <}{@link TestAssertionReportType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<TestAssertionReportType>> getInfoOrWarningOrError() {
        if (infoOrWarningOrError == null) {
            infoOrWarningOrError = new ArrayList<JAXBElement<TestAssertionReportType>>();
        }
        return this.infoOrWarningOrError;
    }

}
