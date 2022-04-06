package database.daoImpl;

import database.DatabaseConnector;
import database.dao.NodeDao;
import org.openstreetmap.osm._0.Node;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PreparedStatementNodeDaoImpl implements NodeDao {
    @Override
    public void insertNodes(List<Node> nodes) {
        try (Connection connection = DatabaseConnector.getInstance().getPostgresConnection()) {
            System.out.println("inserting nodes...");
            //connection.setAutoCommit(false);
            for (Node node : nodes) {
                insertNode(node, connection);
            }
            //connection.setAutoCommit(true);
            System.out.println("nodes inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNode(Node node, Connection connection) throws SQLException {
        String sql = "INSERT INTO node (id, lat, lon, username, uid, version, changeset, datestamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ON CONFLICT DO NOTHING";
        createPreparedStatement(connection, sql, node).executeUpdate();
    }

    private PreparedStatement createPreparedStatement(Connection connection, String sql, Node node) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, node.getId().intValue());
        preparedStatement.setDouble(2, node.getLat());
        preparedStatement.setDouble(3, node.getLon());
        preparedStatement.setString(4, node.getUser());
        preparedStatement.setInt(5, node.getUid().intValue());
        preparedStatement.setInt(6, node.getVersion().intValue());
        preparedStatement.setInt(7, node.getChangeset().intValue());
        preparedStatement.setDate(8, new Date(node.getTimestamp().toGregorianCalendar().getTime().getTime()));
        return preparedStatement;
    }
}
