package database.daoImpl;

import database.DatabaseConnector;
import database.dao.NodeDao;
import org.openstreetmap.osm._0.Node;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class StringNodeDaoImpl implements NodeDao {
    Statement statement;

    @Override
    public void insertNodes(List<Node> nodes, Connection connection) {
        try {
            System.out.println("inserting nodes...");
            statement = connection.createStatement();
            for (Node node : nodes) {
                insertNode(node, connection);
            }
            System.out.println("nodes inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNode(Node node, Connection connection) throws SQLException {
        String sql = "INSERT INTO node (id, lat, lon, username, uid, version, changeset, datestamp) VALUES (" +
                node.getId().intValue() + ", " +
                node.getLat() + "," +
                node.getLon() + ",'" +
                node.getUser() + "'," +
                node.getUid().intValue() + "," +
                node.getVersion().intValue() + "," +
                node.getChangeset().intValue() + ",'" +
                new Date(node.getTimestamp().toGregorianCalendar().getTime().getTime()).toLocalDate() + " +07')"
                + " ON CONFLICT DO NOTHING";
        statement.executeUpdate(sql);
    }
}
