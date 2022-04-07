package database.daoImpl;

import database.DatabaseConnector;
import database.dao.NodeDao;
import org.openstreetmap.osm._0.Node;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PreparedStatementNodeDaoImpl implements NodeDao {
    @Override
    public void insertNodes(List<Node> nodes, Connection connection) {
        String sql = "INSERT INTO node (id, lat, lon, username, uid, version, changeset, datestamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ON CONFLICT DO NOTHING";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            System.out.println("inserting nodes...");
            for (Node node : nodes) {
                insertNode(node, preparedStatement);
            }
            System.out.println("nodes inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNode(Node node, PreparedStatement preparedStatement) throws SQLException {
        fillPreparedStatement(preparedStatement, node);
        preparedStatement.executeUpdate();
    }

    private static void fillPreparedStatement(PreparedStatement preparedStatement, Node node) throws SQLException {
        preparedStatement.setInt(1, node.getId().intValue());
        preparedStatement.setDouble(2, node.getLat());
        preparedStatement.setDouble(3, node.getLon());
        preparedStatement.setString(4, node.getUser());
        preparedStatement.setInt(5, node.getUid().intValue());
        preparedStatement.setInt(6, node.getVersion().intValue());
        preparedStatement.setInt(7, node.getChangeset().intValue());
        preparedStatement.setDate(8, new Date(node.getTimestamp().toGregorianCalendar().getTime().getTime()));
    }
}
