
package com.gitb.core.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValueEmbeddingEnumeration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ValueEmbeddingEnumeration">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="URI"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ValueEmbeddingEnumeration")
@XmlEnum
public enum ValueEmbeddingEnumeration {

    URI;

    public String value() {
        return name();
    }

    public static ValueEmbeddingEnumeration fromValue(String v) {
        return valueOf(v);
    }

}
