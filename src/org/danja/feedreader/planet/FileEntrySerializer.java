/*
 * Danny Ayers Oct 24, 2004
 * http://dannyayers.com
 * 
 */
package org.danja.feedreader.planet;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.danja.feedreader.feeds.Entry;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class FileEntrySerializer {

    private Document doc;

    private Element channelElement;

    private static TransformerFactory transformerFactory = TransformerFactory
            .newInstance();

    private static DocumentBuilderFactory factory = DocumentBuilderFactory
            .newInstance();

    public void loadDocumentShell(String filename) {
        doc = loadXml(filename);
        channelElement = getChannelElement();
    }

    public Element getChannelElement() {
        NodeList top = doc.getDocumentElement().getElementsByTagName("channel");
        return (Element) top.item(0);
    }

    public void addEntry(Entry entry) {
        Element item = doc.createElement("item");
        item.appendChild(simpleElement("guid", entry.getURIString()));
        item.appendChild(simpleElement("title", entry.getTitle()));
        item.appendChild(simpleElement("description", unescape(entry
                .getContent())));
        item.appendChild(simpleElement("link", entry.getLink()));
        item.appendChild(simpleElement("pubDate", entry.getDate()));
        String sourceTitle = entry.getAuthor();
        if(sourceTitle.equals("")){
            sourceTitle = entry.getSourceTitle();
        }
        Element sourceElement = simpleElement("source", sourceTitle);
        sourceElement.setAttribute("url", entry.getSourceLink());
        item.appendChild(sourceElement);
        channelElement.appendChild(item);
    }

    public String unescape(String content) {
        content = content.replaceAll("&amp;", "&");
        content = content.replaceAll("&apos;", "'");
        content = content.replaceAll("&quot;", "\"");
        content = content.replaceAll("&lt;.+?&gt;", "");
        return content;
    }

    private Element simpleElement(String name, String value) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        return element;
    }

    public static Document loadXml(String filename) {
        Document doc = null;
        try {
            doc = factory.newDocumentBuilder().parse(new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static void writeXmlFile(Document doc, String outputFilename,
            String xslFilename) {
        try {
            Source source = new DOMSource(doc);

            Result result = new StreamResult(new File(outputFilename));

            Transformer transformer = getTransformer(xslFilename);

            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void transformWrite(String outputFilename, String xslFilename) {
        writeXmlFile(doc, outputFilename, xslFilename);
    }

    public void write(String outputFilename) {
        writeXmlFile(doc, outputFilename, null);
    }

    public static Transformer getTransformer(String xslFilename) {
        Transformer transformer = null;
        try {
            if (xslFilename == null) {
                return transformerFactory.newTransformer();
            }
            Source xslSource = new StreamSource(
                    new FileInputStream(xslFilename));

            Templates template = transformerFactory.newTemplates(xslSource);
            transformer = template.newTransformer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transformer;
    }

    public void clearEntries() {
        NodeList items = channelElement.getElementsByTagName("item");
        List itemList = new ArrayList();
        for (int i = 0; i < items.getLength(); i++) {
            itemList.add(items.item(i));
        }
        for (int i = 0; i < items.getLength(); i++) {
            channelElement.removeChild((Element) itemList.get(i));
        }
    }

}