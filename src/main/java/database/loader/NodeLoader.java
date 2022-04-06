package database.loader;

import database.dao.NodeDao;
import database.dao.TagDao;

import org.openstreetmap.osm._0.Node;
import org.openstreetmap.osm._0.Tag;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class NodeLoader implements IDataLoader<Node>{

    private NodeDao nodeDao;
    private TagDao tagDao;
    private Connection connection;

    public NodeLoader(NodeDao nodeDao, TagDao tagDao, Connection connection) {
        this.nodeDao = nodeDao;
        this.tagDao = tagDao;
        this.connection = connection;
    }

    @Override
    public void load(List<Node> nodes) {
        try {
            connection.setAutoCommit(false);
            nodeDao.insertNodes(nodes);
            connection.commit();
            for (Node node : nodes) {
                List<Tag> tags = node.getTag();
                tagDao.insertTags(tags, connection);
                tagDao.insertTagsToNode(tags, node.getId(), connection);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
