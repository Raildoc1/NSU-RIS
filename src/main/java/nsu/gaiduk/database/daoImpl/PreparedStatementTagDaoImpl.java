package nsu.gaiduk.database.daoImpl;

import nsu.gaiduk.database.DatabaseConnector;
import nsu.gaiduk.database.dao.TagDao;
import org.openstreetmap.osm._0.Tag;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PreparedStatementTagDaoImpl implements TagDao {
    @Override
    public void insertTags(List<Tag> tags) {
        for (Tag tag : tags) {
            insertTag(tag);
        }
    }

    private void insertTag(Tag tag) {
        try (Connection connection = DatabaseConnector.getInstance().getPostgresConnection()) {
            String sql = "INSERT INTO tag (k, v) VALUES (?, ?) ON CONFLICT DO NOTHING";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tag.getK());
            preparedStatement.setString(2, tag.getV());
            preparedStatement.executeUpdate();
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

    private void insertTagToNode(String tagId, BigInteger nodeId) {
        try (Connection connection = DatabaseConnector.getInstance().getPostgresConnection()) {
            String sql = "INSERT INTO node_tag (node_id, tag_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, nodeId.intValue());
            preparedStatement.setString(2, tagId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
