package persistance.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Data;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "bounds",
    "node",
    "way",
    "relation"
})
@XmlRootElement(name = "osm")
public class Osm {

    @XmlElement(required = true)
    protected Bounds bounds;
    protected List<Node> node;
    protected List<Way> way;
    protected List<Relation> relation;
    @XmlAttribute(name = "version")
    protected Float version;
    @XmlAttribute(name = "generator")
    protected String generator;

    public List<Node> getNode() {
        if (node == null) {
            node = new ArrayList<>();
        }
        return this.node;
    }

    public List<Way> getWay() {
        if (way == null) {
            way = new ArrayList<>();
        }
        return this.way;
    }

    public List<Relation> getRelation() {
        if (relation == null) {
            relation = new ArrayList<>();
        }
        return this.relation;
    }

    public float getVersion() {
        if (version == null) {
            return  0.6F;
        } else {
            return version;
        }
    }

    public String getGenerator() {
        if (generator == null) {
            return "CGImap 0.0.2";
        } else {
            return generator;
        }
    }
}
