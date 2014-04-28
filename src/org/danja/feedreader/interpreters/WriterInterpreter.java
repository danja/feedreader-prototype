/**
 * feedreader-prototype
 *
 * WriterInterpreter.java
 * 
 * @author danja
 * @date Apr 25, 2014
 *
 */
package org.danja.feedreader.interpreters;

import org.danja.feedreader.feeds.FeedConstants;
import org.danja.feedreader.feeds.Feed;


public class WriterInterpreter extends InterpreterBase {

    public void interpret(Feed feed) {
        System.out.println("\nFeed: "+feed.getUrl());
        System.out.println("type: "+ FeedConstants.formatName(feed.getFormatHint()));
        System.out.println("Writing from WriterInterpreter...");
        feed.downloadToFile("data/"+RDFInterpreterFactory.getFilename(feed));
    }

}
