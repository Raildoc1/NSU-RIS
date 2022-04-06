package database.daoImpl;

import database.DatabaseConnector;
import database.dao.NodeDao;
import org.openstreetmap.osm._0.Node;

import java.sql.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BatchNodeDaoImpl implements NodeDao {
    @Override
    public void insertNodes(List<Node> nodes) {
        try (Connection connection = DatabaseConnector.getInstance().getPostgresConnection()) {
            System.out.println("inserting nodes...");
            //connection.setAutoCommit(false);
            String sql = "INSERT INTO node (id, lat, lon, username, uid, version, changeset, datestamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ON CONFLICT DO NOTHING";
            AtomicInteger count = new AtomicInteger();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (Node node : nodes) {
                try {
                    preparedStatement.setInt(1, node.getId().intValue());
                    preparedStatement.setDouble(2, node.getLat());
                    preparedStatement.setDouble(3, node.getLon());
                    preparedStatement.setString(4, node.getUser());
                    preparedStatement.setInt(5, node.getUid().intValue());
                    preparedStatement.setInt(6, node.getVersion().intValue());
                    preparedStatement.setInt(7, node.getChangeset().intValue());
                    preparedStatement.setDate(8, new Date(node.getTimestamp().toGregorianCalendar().getTime().getTime()));
                    preparedStatement.addBatch();
                    count.getAndIncrement();
                    if (count.intValue() == 1000) {
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
            //connection.setAutoCommit(true);
            System.out.println("nodes inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
