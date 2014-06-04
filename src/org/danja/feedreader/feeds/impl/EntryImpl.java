/**
 * feedreader-prototype
 *
 * EntryImpl.java
 * 
 * @author danja
 * @date Apr 24, 2014
 *
 */
package org.danja.feedreader.feeds.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.danja.feedreader.feeds.Entry;
import org.danja.feedreader.templating.Templater;

/**
 *  Implementation of Entry, an RSS item/Atom entry/Blog post model
 */
public class EntryImpl extends FeedEntityBase implements Entry {

    private String summary = null;

	/**
     * @param uriString URL of feed
     */
    public EntryImpl(String uriString) {
        super(uriString);
    }

    /**
     * Constructor
     */
    public EntryImpl() {
    //	tidy.init();
	}
	
	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
	public String toString() {
		  return "* Entry *\n" + super.toString() + "content = "+getContent()+"\n";
	}

	@Override
	public void setSummary(String summary) {
		this.summary  = summary;
	}

	@Override
	public String getSummary() {
		return summary;
	}
}