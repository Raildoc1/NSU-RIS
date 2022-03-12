package database;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class DatabaseConnector {
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "root";

    private static volatile DatabaseConnector instance;
    public static DatabaseConnector getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnector.class) {
                if (instance == null) {
                    instance = new DatabaseConnector();
                }
            }
        }
        return instance;
    }

    private DatabaseConnector() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getPostgresConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            log.error("Connection Failed. Message: {}", e.getMessage());
        }

        return connection;
    }

    public void deployDatabase() throws SQLException {
        executeSqlCommand(
                "DROP SCHEMA public CASCADE; " +
                "CREATE SCHEMA public;"
        );
        executeSqlCommand("CREATE TABLE IF NOT EXISTS tag (" +
                "k TEXT PRIMARY KEY, " +
                "v TEXT" +
                ");"
        );
        executeSqlCommand("CREATE TABLE IF NOT EXISTS node (" +
                "id NUMERIC PRIMARY KEY, " +
                "lat REAL, lon REAL, username TEXT, " +
                "uid NUMERIC, " +
                "version NUMERIC, " +
                "changeset NUMERIC, " +
                "datestamp DATE" +
                ");"
        );
        executeSqlCommand("CREATE TABLE IF NOT EXISTS node_tag (" +
                "node_id NUMERIC REFERENCES node (id), " +
                "tag_id TEXT REFERENCES tag (k)" +
                ");"
        );
    }

    private void executeSqlCommand(String command) throws SQLException {
        Connection connection = getPostgresConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(command);
    }
}
