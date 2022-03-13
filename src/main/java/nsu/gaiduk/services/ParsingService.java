package nsu.gaiduk.services;

import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.gaiduk.util.XmlHandler;
import org.openstreetmap.osm._0.Node;
import org.openstreetmap.osm._0.Osm;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParsingService {
    private final OsmParsingService osmParser;
    private final NodeLoaderService loader;

    @PostConstruct
    private void setup() {
        try {
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

            System.out.print("Speed: ");
            long preparedStatementNanoTime = insertNodes(nodes);
            System.out.println(nodesAmount / (preparedStatementNanoTime / 1_000_000_000f) + " nodes per second");
            System.out.println("Nano time = " + preparedStatementNanoTime);
        } catch (JAXBException | URISyntaxException | SQLException e) {
            log.error("Exception occurred: {}", e.getMessage());
        }
    }

    public long insertNodes(List<Node> nodes) throws SQLException {
        long time = System.nanoTime();
        for (Node node : nodes) {
            loader.loadToDatabase(osmParser.parseNode(node));
        }
        return System.nanoTime() - time;
    }
}
