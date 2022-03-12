package database.dao;

import org.openstreetmap.osm._0.Node;

import java.util.List;

public interface NodeDao {
    void insertNodes(List<Node> nodes);
}
