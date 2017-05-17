
package com.gitb.core.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TestRoleEnumeration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TestRoleEnumeration">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUT"/>
 *     &lt;enumeration value="SIMULATED"/>
 *     &lt;enumeration value="MONITOR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TestRoleEnumeration")
@XmlEnum
public enum TestRoleEnumeration {

    SUT,
    SIMULATED,
    MONITOR;

    public String value() {
        return name();
    }

    public static TestRoleEnumeration fromValue(String v) {
        return valueOf(v);
    }

}
