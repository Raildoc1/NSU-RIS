package database.daoImpl;

import database.dao.TagDao;
import org.openstreetmap.osm._0.Tag;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BatchTagDaoImpl implements TagDao {
    private PreparedStatement insertTagPreparedStatement = null;
    private PreparedStatement insertTagToNodePreparedStatement = null;

    @Override
    public void initialize(Connection connection) {
        try {
            String sql = "INSERT INTO tag (k, v) VALUES (?, ?) ON CONFLICT DO NOTHING";
            insertTagPreparedStatement = connection.prepareStatement(sql);
            sql = "INSERT INTO node_tag (node_id, tag_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
            insertTagToNodePreparedStatement = connection.prepareStatement(sql);
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
            int currentBatchSize = 0;
            for (Tag tag : tags) {
                try {
                    insertTagPreparedStatement.setString(1, tag.getK());
                    insertTagPreparedStatement.setString(2, tag.getV());
                    insertTagPreparedStatement.addBatch();
                    currentBatchSize++;
                    if (currentBatchSize >= 1000) {
                        insertTagPreparedStatement.executeBatch();
                        currentBatchSize = 0;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            insertTagPreparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertTagsToNode(List<Tag> tags, BigInteger nodeId) {
        try {
            int currentBatchSize = 0;
            for (Tag tag : tags) {
                try {
                    insertTagToNodePreparedStatement.setInt(1, nodeId.intValue());
                    insertTagToNodePreparedStatement.setString(2, tag.getK());
                    insertTagToNodePreparedStatement.addBatch();
                    currentBatchSize++;
                    if (currentBatchSize >= 1000) {
                        insertTagToNodePreparedStatement.executeBatch();
                        currentBatchSize = 0;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            insertTagToNodePreparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
