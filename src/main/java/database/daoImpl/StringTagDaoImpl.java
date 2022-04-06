package database.daoImpl;

import database.DatabaseConnector;
import database.dao.TagDao;
import org.openstreetmap.osm._0.Node;
import org.openstreetmap.osm._0.Tag;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class StringTagDaoImpl implements TagDao {
    @Override
    public void insertTags(List<Tag> tags, Connection connection) {
        try {
            for (Tag tag : tags) {
                insertTag(tag, connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTag(Tag tag, Connection connection) throws SQLException {
        String sql = "INSERT INTO tag (k, v) VALUES ('" +
                tag.getK() + "','" +
                tag.getV() + "') ON CONFLICT DO NOTHING";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    @Override
    public void insertTagsToNode(List<Tag> tags, BigInteger nodeId, Connection connection) {
        try {
            for (Tag tag : tags) {
                insertTagToNode(tag.getK(), nodeId, connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTagToNode(String tagId, BigInteger nodeId, Connection connection) throws SQLException {
        String sql = "INSERT INTO node_tag (node_id, tag_id) VALUES ('" +
                nodeId.intValue() + "','" +
                tagId + "') ON CONFLICT DO NOTHING";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }
}
