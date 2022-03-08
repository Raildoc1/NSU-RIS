package persistance.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Data;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "bounds")
public class Bounds {

    @XmlAttribute(name = "minlat")
    protected Double minlat;
    @XmlAttribute(name = "minlon")
    protected Double minlon;
    @XmlAttribute(name = "maxlat")
    protected Double maxlat;
    @XmlAttribute(name = "maxlon")
    protected Double maxlon;
}
