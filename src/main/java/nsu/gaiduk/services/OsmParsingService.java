package nsu.gaiduk.services;

import nsu.gaiduk.database.entities.NodeEntity;
import nsu.gaiduk.database.entities.TagEntity;
import org.openstreetmap.osm._0.Node;
import org.openstreetmap.osm._0.Tag;
import org.springframework.stereotype.Service;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OsmParsingService {
    public NodeEntity parseNode(Node node) {
        XMLGregorianCalendar xmlGregorianCalendar = node.getTimestamp();
        LocalDate localDate = LocalDate.of(
                xmlGregorianCalendar.getYear(),
                xmlGregorianCalendar.getMonth(),
                xmlGregorianCalendar.getDay()
        );

        return NodeEntity.builder()
                .id(node.getId())
                .lat(node.getLat())
                .lon(node.getLon())
                .username(node.getUser())
                .uid(node.getUid())
                .visible(node.isVisible())
                .version(node.getVersion())
                .changeset(node.getChangeset())
                .timestamp(localDate)
                .tags(parseTagsList(node.getTag()))
                .build();
    }

    public TagEntity parseTag(Tag tag) {
        return TagEntity.builder()
                .k(tag.getK())
                .v(tag.getV())
                .build();
    }

    public List<TagEntity> parseTagsList(List<Tag> tags) {
        return tags.stream()
                .map(this::parseTag)
                .collect(Collectors.toList());
    }
}
