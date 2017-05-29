
package com.gitb.core.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for outputFormatEnumeration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="outputFormatEnumeration">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="XML"/>
 *     &lt;enumeration value="JSON"/>
 *     &lt;enumeration value="TSV"/>
 *     &lt;enumeration value="CSV"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "outputFormatEnumeration")
@XmlEnum
public enum OutputFormatEnumeration {

    XML,
    JSON,
    TSV,
    CSV;

    public String value() {
        return name();
    }

    public static OutputFormatEnumeration fromValue(String v) {
        return valueOf(v);
    }

}
