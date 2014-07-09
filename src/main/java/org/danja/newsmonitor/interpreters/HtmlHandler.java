/**
 * NOT USED
 * 
 * feedreader-prototype
 *
 * HtmlHandler.java
 * 
 * @author danja
 * @date Apr 25, 2014
 *
 */
package org.danja.newsmonitor.interpreters;

import java.util.ArrayList;
import java.util.List;

import org.danja.newsmonitor.model.Link;
import org.danja.newsmonitor.model.impl.LinkImpl;
import org.danja.newsmonitor.utils.ContentType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HtmlHandler extends FeedHandlerBase {
    
    private List links = new ArrayList();

    public void startElement(String namespaceURI, String localName,
            String qName, Attributes attributes) {
        if ("link".equalsIgnoreCase(localName)) {
            Link link = new LinkImpl();
            link.setRel(attributes.getValue("rel"));
            link.setHref(attributes.getValue("href"));
            link.setContentType(attributes.getValue("type"));
            link.setFormat(ContentType.getTypeName(attributes.getValue("type")));
            // type = ContentType.getTypeName(type);
            links.add(link);
        }
    }

    public List getLinks() {
        return links;
    }

    private List comments = new ArrayList();
    
    public List getComments() {
        return comments;
    }

    public void comment(String comment) {
        comments.add(comment);
    }

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}
}