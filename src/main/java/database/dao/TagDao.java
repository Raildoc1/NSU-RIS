package database.dao;

import org.openstreetmap.osm._0.Tag;

import java.math.BigInteger;
import java.util.List;

public interface TagDao {
    void insertTags(List<Tag> tags);
    void insertTagsToNode(List<Tag> tags, BigInteger nodeId);
}