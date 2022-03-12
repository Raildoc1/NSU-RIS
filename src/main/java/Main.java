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

        int nodesAmount = 3000;

        List<Node> nodes = osm.getNode().subList(0, nodesAmount);

        DatabaseConnector databaseConnector = DatabaseConnector.getInstance();

        System.out.print("Prepared Statement Speed: ");
        long preparedStatementNanoTime = insertNodes(databaseConnector, new NodeLoader(new PreparedStatementNodeDaoImpl(), new PreparedStatementTagDaoImpl()), nodes);
        System.out.println(nodesAmount / (preparedStatementNanoTime / 1_000_000_000f) + " nodes per second");
        System.out.println("Nano time = " + preparedStatementNanoTime);

        System.out.print("String Command Speed: ");
        long stringNanoTime = insertNodes(databaseConnector, new NodeLoader(new StringNodeDaoImpl(), new StringTagDaoImpl()), nodes);
        System.out.println(nodesAmount / (stringNanoTime / 1_000_000_000f) + " nodes per second");
        System.out.println("Nano time = " + stringNanoTime);

        System.out.print("Batch Speed: ");
        long batchNanoTime = insertNodes(databaseConnector, new NodeLoader(new BatchNodeDaoImpl(), new BatchTagDaoImpl()), nodes);
        System.out.println(nodesAmount / (batchNanoTime / 1_000_000_000f) + " nodes per second");
        System.out.println("Nano time = " + batchNanoTime);
    }

    public static long insertNodes(DatabaseConnector databaseConnector, NodeLoader loader, List<Node> nodes) throws SQLException {
        databaseConnector.deployDatabase();
        long time = System.nanoTime();
        loader.load(nodes);
        return System.nanoTime() - time;
    }
}
