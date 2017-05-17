
package com.gitb.core.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StepStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StepStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="PROCESSING"/>
 *     &lt;enumeration value="SKIPPED"/>
 *     &lt;enumeration value="WAITING"/>
 *     &lt;enumeration value="ERROR"/>
 *     &lt;enumeration value="COMPLETED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StepStatus")
@XmlEnum
public enum StepStatus {

    PROCESSING,
    SKIPPED,
    WAITING,
    ERROR,
    COMPLETED;

    public String value() {
        return name();
    }

    public static StepStatus fromValue(String v) {
        return valueOf(v);
    }

}
