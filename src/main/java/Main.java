import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import lombok.extern.slf4j.Slf4j;
import persistance.model.Node;

@Slf4j
public class Main {

    public static void main(String[] args) throws URISyntaxException, JAXBException, XMLStreamException {
        log.info("Hello, World!");

        XmlHandler xmlHandler = new XmlHandler();
        ArrayList<Node> nodes = xmlHandler.unmarshall("compressed/RU-NVS.osm.bz2");

        Map<String, Integer> userChanges = xmlHandler.getUserChanges(nodes);
        Map<String, Integer> tagsCount = xmlHandler.getTagsCount(nodes);

        log.info("---------------------------------------------------------------------------");
        log.info(String.format("%1$45s %2$20s", "User Name", "Changes Amount"));
        log.info("---------------------------------------------------------------------------");
        userChanges.forEach((k, v) -> log.info(String.format("%1$45s %2$20s", k, v)));
        log.info("---------------------------------------------------------------------------");
        log.info(String.format("%1$45s %2$20s", "Tag", "Amount"));
        log.info("---------------------------------------------------------------------------");
        tagsCount.forEach((k, v) -> log.info(String.format("%1$45s %2$20s", k, v)));
        log.info("---------------------------------------------------------------------------");
    }
}
