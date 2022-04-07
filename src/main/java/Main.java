import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import database.DatabaseConnector;
import database.daoImpl.*;
import database.loader.NodeLoader;
import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import org.openstreetmap.osm._0.Node;
import org.openstreetmap.osm._0.Osm;

@Slf4j
public class Main {

    public static void main(String[] args) throws URISyntaxException, JAXBException, SQLException {
        log.info("Hello, World!");

        XmlHandler xmlHandler = new XmlHandler();
        Osm osm = xmlHandler.unmarshall("compressed/RU-NVS.osm.bz2");

        Map<String, Integer> userChanges = xmlHandler.getUserChanges(osm);
        Map<String, Integer> tagsCount = xmlHandler.getTagsCount(osm);

        log.info("---------------------------------------------------------------------------");
        log.info(String.format("%1$45s %2$20s", "User Name", "Changes Amount"));
        log.info("---------------------------------------------------------------------------");
        userChanges.forEach((k, v) -> log.info(String.format("%1$45s %2$20s", k, v)));
        log.info("---------------------------------------------------------------------------");
        log.info(String.format("%1$45s %2$20s", "Tag", "Amount"));
        log.info("---------------------------------------------------------------------------");
        tagsCount.forEach((k, v) -> log.info(String.format("%1$45s %2$20s", k, v)));
        log.info("---------------------------------------------------------------------------");

        int nodesAmount = 12000;

        List<Node> nodes = osm.getNode().subList(0, nodesAmount);
        System.out.print("Nodes amount = " + nodes.size());

        DatabaseConnector databaseConnector = DatabaseConnector.getInstance();

        // 1335.4691 nodes per second
        try(Connection connection = databaseConnector.getPostgresConnection()) {
            System.out.print("Prepared Statement Speed: ");
            long preparedStatementNanoTime = insertNodes(databaseConnector, new NodeLoader(new PreparedStatementNodeDaoImpl(), new PreparedStatementTagDaoImpl(), connection), nodes);
            System.out.println(nodes.size() / (preparedStatementNanoTime / 1_000_000_000f) + " nodes per second");
            System.out.println("Nano time = " + preparedStatementNanoTime);
            System.out.println("");
        }

        // 1226.472 nodes per second
        try(Connection connection = databaseConnector.getPostgresConnection()) {
            System.out.print("String Command Speed: ");
            long stringNanoTime = insertNodes(databaseConnector, new NodeLoader(new StringNodeDaoImpl(), new StringTagDaoImpl(), connection), nodes);
            System.out.println(nodes.size() / (stringNanoTime / 1_000_000_000f) + " nodes per second");
            System.out.println("Nano time = " + stringNanoTime);
            System.out.println("");
        }
        
        // 9500.254 nodes per second
        try(Connection connection = databaseConnector.getPostgresConnection()) {
            System.out.print("Batch Speed: ");
            long batchNanoTime = insertNodes(databaseConnector, new NodeLoader(new BatchNodeDaoImpl(), new BatchTagDaoImpl(), connection), nodes);
            System.out.println(nodes.size() / (batchNanoTime / 1_000_000_000f) + " nodes per second");
            System.out.println("Nano time = " + batchNanoTime);
            System.out.println("");
        }
    }

    public static long insertNodes(DatabaseConnector databaseConnector, NodeLoader loader, List<Node> nodes) throws SQLException {
        System.out.print("Deploying database...");
        databaseConnector.deployDatabase();
        long time = System.nanoTime();
        System.out.print("Loading database...");
        loader.load(nodes);
        return System.nanoTime() - time;
    }
}
