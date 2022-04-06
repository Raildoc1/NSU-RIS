package database.daoImpl;

import database.DatabaseConnector;
import database.dao.TagDao;
import org.openstreetmap.osm._0.Node;
import org.openstreetmap.osm._0.Tag;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PreparedStatementTagDaoImpl implements TagDao {
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

    private void insertTag(Tag tag, Connection connection) throws SQLException {
        String sql = "INSERT INTO tag (k, v) VALUES (?, ?) ON CONFLICT DO NOTHING";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, tag.getK());
        preparedStatement.setString(2, tag.getV());
        preparedStatement.executeUpdate();
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

    private void insertTagToNode(String tagId, BigInteger nodeId, Connection connection) throws SQLException {
        String sql = "INSERT INTO node_tag (node_id, tag_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, nodeId.intValue());
        preparedStatement.setString(2, tagId);
        preparedStatement.executeUpdate();
    }
}
