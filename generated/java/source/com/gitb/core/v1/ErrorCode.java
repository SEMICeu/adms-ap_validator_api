
package com.gitb.core.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ErrorCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ErrorCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACTOR_DEFINITION_NOT_FOUND"/>
 *     &lt;enumeration value="ARTIFACT_NOT_FOUND"/>
 *     &lt;enumeration value="CANCELLATION"/>
 *     &lt;enumeration value="DATATYPE_ERROR"/>
 *     &lt;enumeration value="INTERNAL_ERROR"/>
 *     &lt;enumeration value="INVALID_SESSION"/>
 *     &lt;enumeration value="INVALID_TEST_CASE"/>
 *     &lt;enumeration value="MISSING_CONFIGURATION"/>
 *     &lt;enumeration value="INVALID_CONFIGURATION"/>
 *     &lt;enumeration value="TEST_CASE_DEFINITION_NOT_FOUND"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ErrorCode")
@XmlEnum
public enum ErrorCode {

    ACTOR_DEFINITION_NOT_FOUND,
    ARTIFACT_NOT_FOUND,
    CANCELLATION,
    DATATYPE_ERROR,
    INTERNAL_ERROR,
    INVALID_SESSION,
    INVALID_TEST_CASE,
    MISSING_CONFIGURATION,
    INVALID_CONFIGURATION,
    TEST_CASE_DEFINITION_NOT_FOUND;

    public String value() {
        return name();
    }

    public static ErrorCode fromValue(String v) {
        return valueOf(v);
    }

}
