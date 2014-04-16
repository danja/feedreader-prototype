package org.danja.feedreader.parsers;

import org.danja.feedreader.feeds.Entry;
import org.danja.feedreader.feeds.EntryImpl;
import org.danja.feedreader.feeds.EntryList;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX2 handler for RSS 2.0 XML
 *  
 */

// Sourced added for Planet
public class Rss2Handler extends DefaultHandler {

    // added for Planet
    private String sourceURI = "";

    private String sourceTitle = "";
    
    private String author = "";
    
    private final static char IN_NOTHING = 0;

    private final static char IN_CHANNEL = 1;

    private final static char IN_ITEM = 2;

    private char state = IN_NOTHING;

    private StringBuffer textBuffer;

    private Entry entry;

    private EntryList entries;

    // added for Planet
    private String feedTitle = "";
    private String feedLink = "";
    
    public Rss2Handler() {
        textBuffer = new StringBuffer();
    }

    public void setEntryList(EntryList entries) {
        this.entries = entries;
    }

    public void startElement(String namespaceURI, String localName,
            String qName, Attributes attributes) {
        switch (state) {
        case IN_NOTHING:
            if ("channel".equals(localName)) {
                state = IN_CHANNEL;
            }
            return;

        case IN_CHANNEL:
//          added for Planet
            textBuffer = new StringBuffer();
            if ("item".equals(localName)) {
                state = IN_ITEM;
                entry = new EntryImpl();
//              added for Planet
             
                entry.setAuthor(author);
                entry.setSourceTitle(feedTitle); 
                entry.setSourceLink(feedLink); 
            }
            return;

        case IN_ITEM:
            textBuffer = new StringBuffer();
            return;

        default:
            return;
        }
    }

    public void characters(char[] ch, int start, int length) {
        textBuffer.append(ch, start, length);
    }

    public void endElement(String namespaceURI, String localName, String qName) {

        switch (state) {

        case IN_NOTHING:
            return;

        case IN_CHANNEL: // switch down
            if ("channel".equals(localName)) {
                state = IN_NOTHING;
            }
            
            // added for Planet
            if ("title".equals(localName)) {
                feedTitle = textBuffer.toString();
                return;
            }
            if ("author".equals(localName)) {
                author = textBuffer.toString();
                return;
            }
            if ("link".equals(localName)) {
                feedLink = textBuffer.toString();
                return;
            }
            return;

        case IN_ITEM:
            if ("item".equals(localName)) {
                state = IN_CHANNEL;
                entries.addEntry(entry);
                return;
            }
            if ("title".equals(localName)) {
                entry.setTitle(textBuffer.toString());
                return;
            }
            if ("description".equals(localName)) {
                entry.setContent(textBuffer.toString());
                return;
            }

            // added for Planet
            if ("guid".equals(localName)) {
                entry.setURIString(textBuffer.toString());
                return;
            }
            if ("pubDate".equals(localName)) {
                entry.setDate(textBuffer.toString());
                return;
            }

            if ("link".equals(localName)) {
                entry.setLink(textBuffer.toString());
                return;
            }

            textBuffer.append(localName);
            return;

        default:
            return;
        }
    }

    
    


}
