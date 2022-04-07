package nsu.gaiduk.util;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.util.List;

import nsu.gaiduk.Application;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.openstreetmap.osm._0.Node;

@Slf4j
public class XmlHandler {
    public ArrayList<Node> unmarshall(String filePath) throws JAXBException, URISyntaxException, XMLStreamException {
        ArrayList<Node> result = new ArrayList<>();
        URI uri = Application.class.getClassLoader().getResource(filePath).toURI();
        File inputFile = new File(uri);
        try (BZip2CompressorInputStream inputStream = new BZip2CompressorInputStream(new BufferedInputStream(new FileInputStream(inputFile)))) {
            log.debug("Unmarshalling...");
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);
            Unmarshaller unmarshaller = JAXBContext.newInstance(Node.class).createUnmarshaller();
            QName nodeName = new QName("node");

            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.peek();
                if(event.getEventType() != XMLStreamConstants.START_ELEMENT) {
                    eventReader.nextEvent();
                    continue;
                }
                if(!event.asStartElement().getName().equals(nodeName)) {
                    eventReader.nextEvent();
                    continue;
                }
                result.add(unmarshaller.unmarshal(eventReader, Node.class).getValue());
                eventReader.nextEvent();
            }
            log.debug("Unmarshall successful");
            return result;
        } catch (IOException e) {
            log.error("Unmarshall failed with exception: {}", e.getMessage());
        }
        return null;
    }

    public Map<String, Integer> getUserChanges(ArrayList<Node> nodes) {
        Map<String, Integer> userChanges = new HashMap<>();
        nodes.forEach(node -> {
            userChanges.put(node.getUser(), userChanges.getOrDefault(node.getUser(), 0) + 1);
        });
        return sortByValue(userChanges);
    }

    public Map<String, Integer> getTagsCount(ArrayList<Node> nodes) {
        Map<String, Integer> tags = new HashMap<>();
        nodes.forEach(node -> {
            node.getTag().forEach(tag -> tags.put(tag.getK(), tags.getOrDefault(tag.getK(), 0) + 1));
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
