package database.daoImpl;

import database.DatabaseConnector;
import database.dao.TagDao;
import org.openstreetmap.osm._0.Tag;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class StringTagDaoImpl implements TagDao {
    @Override
    public void insertTags(List<Tag> tags) {
        for (Tag tag : tags) {
            insertTag(tag);
        }
    }

    public void insertTag(Tag tag) {
        try (Connection connection = DatabaseConnector.getInstance().getPostgresConnection()) {
            String sql = "INSERT INTO tag (k, v) VALUES ('" +
                    tag.getK() + "','" +
                    tag.getV() + "') ON CONFLICT DO NOTHING";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertTagsToNode(List<Tag> tags, BigInteger nodeId) {
        for (Tag tag : tags) {
            insertTagToNode(tag.getK(), nodeId);
        }
    }

    public void insertTagToNode(String tagId, BigInteger nodeId) {
        try (Connection connection = DatabaseConnector.getInstance().getPostgresConnection()) {
            String sql = "INSERT INTO node_tag (node_id, tag_id) VALUES ('" +
                    nodeId.intValue() + "','" +
                    tagId + "') ON CONFLICT DO NOTHING";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
