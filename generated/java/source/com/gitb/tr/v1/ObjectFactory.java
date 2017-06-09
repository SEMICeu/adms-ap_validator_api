
package com.gitb.tr.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.gitb.tr.v1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TestCaseReport_QNAME = new QName("http://www.gitb.com/tr/v1/", "TestCaseReport");
    private final static QName _TestStepReport_QNAME = new QName("http://www.gitb.com/tr/v1/", "TestStepReport");
    private final static QName _TestAssertionGroupReportsTypeError_QNAME = new QName("http://www.gitb.com/tr/v1/", "error");
    private final static QName _TestAssertionGroupReportsTypeInfo_QNAME = new QName("http://www.gitb.com/tr/v1/", "info");
    private final static QName _TestAssertionGroupReportsTypeWarning_QNAME = new QName("http://www.gitb.com/tr/v1/", "warning");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gitb.tr.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TestCaseReportType }
     * 
     */
    public TestCaseReportType createTestCaseReportType() {
        return new TestCaseReportType();
    }

    /**
     * Create an instance of {@link TestAssertionGroupReportsType }
     * 
     */
    public TestAssertionGroupReportsType createTestAssertionGroupReportsType() {
        return new TestAssertionGroupReportsType();
    }

    /**
     * Create an instance of {@link BAR }
     * 
     */
    public BAR createBAR() {
        return new BAR();
    }

    /**
     * Create an instance of {@link TAR }
     * 
     */
    public TAR createTAR() {
        return new TAR();
    }

    /**
     * Create an instance of {@link ValidationCounters }
     * 
     */
    public ValidationCounters createValidationCounters() {
        return new ValidationCounters();
    }

    /**
     * Create an instance of {@link ValidationOverview }
     * 
     */
    public ValidationOverview createValidationOverview() {
        return new ValidationOverview();
    }

    /**
     * Create an instance of {@link DR }
     * 
     */
    public DR createDR() {
        return new DR();
    }

    /**
     * Create an instance of {@link SR }
     * 
     */
    public SR createSR() {
        return new SR();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestCaseReportType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gitb.com/tr/v1/", name = "TestCaseReport")
    public JAXBElement<TestCaseReportType> createTestCaseReport(TestCaseReportType value) {
        return new JAXBElement<TestCaseReportType>(_TestCaseReport_QNAME, TestCaseReportType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestStepReportType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gitb.com/tr/v1/", name = "TestStepReport")
    public JAXBElement<TestStepReportType> createTestStepReport(TestStepReportType value) {
        return new JAXBElement<TestStepReportType>(_TestStepReport_QNAME, TestStepReportType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestAssertionReportType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gitb.com/tr/v1/", name = "error", scope = TestAssertionGroupReportsType.class)
    public JAXBElement<TestAssertionReportType> createTestAssertionGroupReportsTypeError(TestAssertionReportType value) {
        return new JAXBElement<TestAssertionReportType>(_TestAssertionGroupReportsTypeError_QNAME, TestAssertionReportType.class, TestAssertionGroupReportsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestAssertionReportType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gitb.com/tr/v1/", name = "info", scope = TestAssertionGroupReportsType.class)
    public JAXBElement<TestAssertionReportType> createTestAssertionGroupReportsTypeInfo(TestAssertionReportType value) {
        return new JAXBElement<TestAssertionReportType>(_TestAssertionGroupReportsTypeInfo_QNAME, TestAssertionReportType.class, TestAssertionGroupReportsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestAssertionReportType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gitb.com/tr/v1/", name = "warning", scope = TestAssertionGroupReportsType.class)
    public JAXBElement<TestAssertionReportType> createTestAssertionGroupReportsTypeWarning(TestAssertionReportType value) {
        return new JAXBElement<TestAssertionReportType>(_TestAssertionGroupReportsTypeWarning_QNAME, TestAssertionReportType.class, TestAssertionGroupReportsType.class, value);
    }

}
