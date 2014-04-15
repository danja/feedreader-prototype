/*
 * Danny Ayers Aug 18, 2004 http://dannyayers.com
 *  
 */
package org.urss.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParserFactory;

import org.urss.feeds.EntryList;
import org.urss.feeds.EntryListImpl;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class ParserTemp extends DefaultHandler {

    public static void main(String[] args) {
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            parserFactory.setNamespaceAware(true);
            XMLReader xmlReader = parserFactory.newSAXParser().getXMLReader();

            xmlReader.setContentHandler(new ParserTemp());
            InputStream inputStream = new FileInputStream(new File(args[0]));
            InputSource inputSource = new InputSource(inputStream);
            xmlReader.parse(inputSource);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startElement(String namespaceURI, String localName,
            String qName, Attributes attributes) {
        System.out.println("\nElement ns = '" + namespaceURI+"' name = '"
                + localName+"'");
        for (int i = 0; i < attributes.getLength(); i++) {
            System.out.println("attr ns = '" + attributes.getURI(i) + "' name = '"
                    + attributes.getLocalName(i) + "'");
        }
    }
}