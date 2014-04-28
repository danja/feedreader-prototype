/**
 * TODO rename
 * feedreader-prototype
 *
 * FeedImpl.java
 * 
 * @author danja
 * @date Apr 25, 2014
 *
 */
package org.danja.feedreader.feeds;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.danja.feedreader.content.Templater;
import org.danja.feedreader.interpreters.Interpreter;
import org.danja.feedreader.io.HttpConnector;
import org.danja.feedreader.main.Config;

/**
 * Models a feed, wrapped around HttpConnection
 *  
 */
public class FeedImpl extends FeedEntityBase implements Feed,
        FeedEntity {
	
	private EntryList entryList = new EntryListImpl();

    private long lastRefresh;

    private long refreshPeriod;

    private static final double ditherFactor = 0.1D;

    private int lives = Config.MAX_LIVES;

    private char hint = FeedConstants.UNKNOWN;

    private boolean isNew = false;

    private HttpConnector httpConnector = null;

    private Interpreter interpreter;

	private boolean firstCall;

    public FeedImpl() {
    }



    public boolean refresh() {
//        if (now() < lastRefresh + refreshPeriod + getPeriodDither()) {
//            return false;
//        }
    	
    	
        if (httpConnector == null) {
        	httpConnector = new HttpConnector();
        
        		httpConnector.setConditional(!firstCall); // first GET is unconditional
        
        	String url = getUrl();
        	System.out.println("URL in FeedImpl.refresh = "+url);
        	httpConnector.setUrl(url);
        }
        isNew = httpConnector.load();

        if (isNew) {
            System.out.println("Connected, interpreting...");
            System.out.println("interpretor ="+interpreter);
            interpreter.interpret(this);
            lives = Config.MAX_LIVES;
        } else {
            if (httpConnector.isDead()) {
                System.out.println("Error, feed life lost.");
                lives--;
                refreshPeriod = refreshPeriod*2;
            }
        }
        lastRefresh = now();
        System.out.println("isNew = "+isNew);
        return isNew;
    }

    private long now() {
        return (new Date()).getTime();
    }

    private long getPeriodDither() {
        return (long) (Math.random() * ditherFactor * refreshPeriod);
    }
    
    public boolean shouldExpire() {
        return lives < 1;
    }

    public void setFormatHint(char hint) {
        this.hint = hint;
    }

    public char getFormatHint() {
        return hint;
    }
    
    public Interpreter getInterpreter() {
        return interpreter;
    }

    public boolean isNew() {
        return isNew;
    }

    public boolean isDead() {
        return httpConnector.isDead();
    }

    public String getETag() {
        return httpConnector.getETag();
    }

    public String getLastModified() {
        return httpConnector.getLastModified();
    }

    public String getContentEncoding() {
        return httpConnector.getContentEncoding();
    }

    public String getContentType() {
        return httpConnector.getContentType();
    }

    public InputStream getInputStream() {
        return httpConnector.getInputStream();
    }

    public String getStatus() {
        return httpConnector.getStatus();
    }

    public void downloadToFile(String filename) {
        httpConnector.downloadToFile(filename);
    }

    public void setInterpreter(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    public long getRefreshPeriod() {
        return refreshPeriod;
    }


    public void setRefreshPeriod(long refreshPeriod) {
        this.refreshPeriod = refreshPeriod;
    }

    public long getLastRefresh() {
        return lastRefresh;
    }
    

    
	@Override
	public String toTurtle() {
		Map<String, Object> data = getTemplateDataMap();
		data.put("type", "rss:channel");
	
		System.out.println(Templater.dataMapToString(data));
		System.out.println("--FEED--");
		return Templater.apply("feed-turtle", data);
	}
	
	public String toString() {
		String string = "* Feed *\n"+getUrl()+"\nFormat = "+ FeedConstants.formatName(getFormatHint())
				+"\n"+interpreter;
		  return super.toString()+string;
	}



	@Override
	public void addEntry(Entry entry) {
		entryList.addEntry(entry);
	}



	@Override
	public EntryList getEntries() {
		return entryList;
	}



	@Override
	public void setFirstCall(boolean firstCall) {
		this.firstCall = firstCall;
	}
	}
