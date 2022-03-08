import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.List;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import persistance.model.Osm;

@Slf4j
public class XmlHandler {

    public Osm unmarshall(String filePath) throws JAXBException, URISyntaxException {
        URI uri = Main.class.getClassLoader().getResource(filePath).toURI();
        File inputFile = new File(uri);
        try (BZip2CompressorInputStream inputStream = new BZip2CompressorInputStream(new BufferedInputStream(new FileInputStream(inputFile)))) {
            log.debug("Unmarshalling...");
            Unmarshaller unmarshaller = JAXBContext.newInstance(Osm.class).createUnmarshaller();
            Osm result = (Osm) unmarshaller.unmarshal(inputStream);
            log.debug("Unmarshall successful");
            return result;
        } catch (IOException e) {
            log.error("Unmarshall failed with exception: {}", e.getMessage());
        }
        return null;
    }

    public Map<String, Integer> getUserChanges(Osm result) {
        Map<String, Integer> userChanges = new HashMap<>();
        result.getNode().forEach(node -> {
            userChanges.put(node.getUser(), userChanges.getOrDefault(node.getUser(), 0) + 1);
        });
        return sortByValue(userChanges);
    }

    public Map<String, Integer> getTagsCount(Osm result) {
        Map<String, Integer> tags = new HashMap<>();
        result.getNode().forEach(node -> {
            if (node.isTagged()) {
                node.getTag().forEach(tag -> tags.put(tag.getK(), tags.getOrDefault(tag.getK(), 0) + 1));
            }
        });
        return sortByValue(tags);
    }

    public static Map<String, Integer> sortByValue(Map<String, Integer> map) {
        List<Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue(Comparator.reverseOrder()));

        Map<String, Integer> result = new LinkedHashMap<>();
        list.forEach(entry -> result.put(entry.getKey(), entry.getValue()));

        return result;
    }
}
