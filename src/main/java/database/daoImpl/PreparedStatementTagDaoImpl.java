package database.daoImpl;

import database.dao.TagDao;
import org.openstreetmap.osm._0.Tag;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PreparedStatementTagDaoImpl implements TagDao {
    private PreparedStatement insertTagPreparedStatement = null;
    private PreparedStatement insertTagToNodePreparedStatement = null;

    @Override
    public void initialize(Connection connection) {
        try {
            insertTagPreparedStatement = connection.prepareStatement("INSERT INTO tag (k, v) VALUES (?, ?) ON CONFLICT DO NOTHING");
            insertTagToNodePreparedStatement = connection.prepareStatement("INSERT INTO node_tag (node_id, tag_id) VALUES (?, ?) ON CONFLICT DO NOTHING");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            insertTagPreparedStatement.close();
            insertTagToNodePreparedStatement.close();
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

    private void insertTag(Tag tag) throws SQLException {
        insertTagPreparedStatement.setString(1, tag.getK());
        insertTagPreparedStatement.setString(2, tag.getV());
        insertTagPreparedStatement.executeUpdate();
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

    private void insertTagToNode(String tagId, BigInteger nodeId) throws SQLException {
        insertTagToNodePreparedStatement.setInt(1, nodeId.intValue());
        insertTagToNodePreparedStatement.setString(2, tagId);
        insertTagToNodePreparedStatement.executeUpdate();
    }
}
