package database.daoImpl;

import database.dao.TagDao;
import org.openstreetmap.osm._0.Tag;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class StringTagDaoImpl implements TagDao {
    private Statement statement = null;

    @Override
    public void initialize(Connection connection) {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertTags(List<Tag> tags) {
        try {
            for (Tag tag : tags) {
                insertTag(tag);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTag(Tag tag) throws SQLException {
        String sql = "INSERT INTO tag (k, v) VALUES ('" +
                tag.getK() + "','" +
                tag.getV() + "') ON CONFLICT DO NOTHING";
        statement.executeUpdate(sql);
    }

    @Override
    public void insertTagsToNode(List<Tag> tags, BigInteger nodeId) {
        try {
            for (Tag tag : tags) {
                insertTagToNode(tag.getK(), nodeId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTagToNode(String tagId, BigInteger nodeId) throws SQLException {
        String sql = "INSERT INTO node_tag (node_id, tag_id) VALUES ('" +
                nodeId.intValue() + "','" +
                tagId + "') ON CONFLICT DO NOTHING";
        statement.executeUpdate(sql);
    }
}
