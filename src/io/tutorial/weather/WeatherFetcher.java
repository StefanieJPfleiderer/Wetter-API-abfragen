package io.tutorial.weather;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class WeatherFetcher {

    private static WeatherFetcher instance;

    private WeatherFetcher() {

    }

    public static WeatherFetcher getInstance() {
        if (instance == null) {
            return new WeatherFetcher();
        }
        return instance;
    }

    public WeatherInfo[] fetch(final String city) throws Exception {
        // URI contains personal key and is removed for security reasons
        // city is used in the URI
        String uri = "";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        Document document = documentBuilder.parse(uri);
        NodeList times = document.getElementsByTagName("time");

        WeatherInfo[] weatherInfos = new WeatherInfo[times.getLength()];

        for (int x = 0; x < times.getLength(); x++) {
            Node time = times.item(x);
            NamedNodeMap timeAttributes = time.getAttributes();

            String timestamp = timeAttributes.getNamedItem("from").getNodeValue();
            NodeList children = time.getChildNodes();
            for (int y = 0; y < children.getLength(); y++) {
                Node child = children.item(y);
                if (child.getNodeName().equals("temperature")) {
                    String temperature = child.getAttributes().getNamedItem("value").getNodeValue();
                    weatherInfos[x] = new WeatherInfo(timestamp, temperature);
                }
            }
        }
        return weatherInfos;
    }


}
