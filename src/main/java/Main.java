import java.net.URISyntaxException;
import java.util.Map;
import javax.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import persistance.model.Osm;

@Slf4j
public class Main {

    public static void main(String[] args) throws URISyntaxException, JAXBException {
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
    }
}
