/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.xmlUtils;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.xml.sax.helpers.DefaultHandler;

/**
 * MANUAL http://www.quizful.net/post/sax-parser-java
 *
 * @author PSEVDO tochka
 */
public class SXParser extends DefaultHandler {

    protected String docUrl;
    protected String thisElement = "";
    protected Hashtable tags;

    protected Map<Integer, String> rightsMap = new HashMap<>();

    @PostConstruct
    public void init() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }

    public Map<Integer, String> getRightsMap() {
        return rightsMap;
    }

    protected void generateRightsMap() {

    }

}
