
package com.gitb.core.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TestCaseType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TestCaseType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="CONFORMANCE"/>
 *     &lt;enumeration value="INTEROPERABILITY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TestCaseType")
@XmlEnum
public enum TestCaseType {

    CONFORMANCE,
    INTEROPERABILITY;

    public String value() {
        return name();
    }

    public static TestCaseType fromValue(String v) {
        return valueOf(v);
    }

}
