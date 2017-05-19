
package com.gitb.core.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.gitb.core.v1 package. 
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

    private final static QName _Module_QNAME = new QName("http://www.gitb.com/core/v1/", "module");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gitb.core.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TestModule }
     * 
     */
    public TestModule createTestModule() {
        return new TestModule();
    }

    /**
     * Create an instance of {@link Metadata }
     * 
     */
    public Metadata createMetadata() {
        return new Metadata();
    }

    /**
     * Create an instance of {@link ConfigurationParameters }
     * 
     */
    public ConfigurationParameters createConfigurationParameters() {
        return new ConfigurationParameters();
    }

    /**
     * Create an instance of {@link TypedParameters }
     * 
     */
    public TypedParameters createTypedParameters() {
        return new TypedParameters();
    }

    /**
     * Create an instance of {@link TypedParameter }
     * 
     */
    public TypedParameter createTypedParameter() {
        return new TypedParameter();
    }

    /**
     * Create an instance of {@link Parameter }
     * 
     */
    public Parameter createParameter() {
        return new Parameter();
    }

    /**
     * Create an instance of {@link ValidationModule }
     * 
     */
    public ValidationModule createValidationModule() {
        return new ValidationModule();
    }

    /**
     * Create an instance of {@link OutputFormat }
     * 
     */
    public OutputFormat createOutputFormat() {
        return new OutputFormat();
    }

    /**
     * Create an instance of {@link URIContent }
     * 
     */
    public URIContent createURIContent() {
        return new URIContent();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestModule }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gitb.com/core/v1/", name = "module")
    public JAXBElement<TestModule> createModule(TestModule value) {
        return new JAXBElement<TestModule>(_Module_QNAME, TestModule.class, null, value);
    }

}
