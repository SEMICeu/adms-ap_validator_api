
package com.gitb.core.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValueEmbeddingEnumeration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ValueEmbeddingEnumeration">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="STRING"/>
 *     &lt;enumeration value="BASE64"/>
 *     &lt;enumeration value="URI"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ValueEmbeddingEnumeration")
@XmlEnum
public enum ValueEmbeddingEnumeration {

    STRING("STRING"),
    @XmlEnumValue("BASE64")
    BASE_64("BASE64"),
    URI("URI");
    private final String value;

    ValueEmbeddingEnumeration(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ValueEmbeddingEnumeration fromValue(String v) {
        for (ValueEmbeddingEnumeration c: ValueEmbeddingEnumeration.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
