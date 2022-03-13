package nsu.gaiduk.database.daoImpl;

import nsu.gaiduk.database.DatabaseConnector;
import nsu.gaiduk.database.dao.TagDao;
import org.openstreetmap.osm._0.Tag;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BatchTagDaoImpl implements TagDao {

    @Override
    public void insertTags(List<Tag> tags) {
        try (Connection connection = DatabaseConnector.getInstance().getPostgresConnection()) {
            String sql = "INSERT INTO tag (k, v) VALUES (?, ?) ON CONFLICT DO NOTHING";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            AtomicInteger count = new AtomicInteger();
            for (Tag tag : tags) {
                try {
                    preparedStatement.setString(1, tag.getK());
                    preparedStatement.setString(2, tag.getV());
                    preparedStatement.addBatch();
                    count.getAndIncrement();
                    if (count.intValue() >= 10) {
                        preparedStatement.executeBatch();
                        preparedStatement = connection.prepareStatement(sql);
                        count.set(0);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertTagsToNode(List<Tag> tags, BigInteger nodeId) {
        try (Connection connection = DatabaseConnector.getInstance().getPostgresConnection()) {
            String sql = "INSERT INTO node_tag (node_id, tag_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
            AtomicInteger count = new AtomicInteger();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (Tag tag : tags) {
                try {
                    preparedStatement.setInt(1, nodeId.intValue());
                    preparedStatement.setString(2, tag.getK());
                    preparedStatement.addBatch();
                    count.getAndIncrement();
                    if (count.intValue() >= 10) {
                        preparedStatement.executeBatch();
                        preparedStatement = connection.prepareStatement(sql);
                        count.set(0);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
