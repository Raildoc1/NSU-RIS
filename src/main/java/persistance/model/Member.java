package persistance.model;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import lombok.Data;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "member")
public class Member {

    @XmlAttribute(name = "type")
    protected String type;
    @XmlAttribute(name = "ref")
    @XmlSchemaType(name = "unsignedLong")
    protected BigInteger ref;
    @XmlAttribute(name = "role")
    protected String role;
}
