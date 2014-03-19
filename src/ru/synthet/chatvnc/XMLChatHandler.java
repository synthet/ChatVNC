/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.synthet.chatvnc;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author bragin_va
 */
public class XMLChatHandler extends DefaultHandler {

    private StringBuffer buffer = new StringBuffer();
    private ArrayList<XMLChatRecord> recordList;
    private XMLChatRecord record;

    @Override
    public void startElement(String namespaceURI, String localName,
            String qName, Attributes atts) throws SAXException {
        buffer.setLength(0);
        if (localName.equals("items")) {
            recordList = new ArrayList<XMLChatRecord>();
        } else if (localName.equals("item")) {
            record = new XMLChatRecord();
        } 
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("item")) {
            recordList.add(record);
        } else if (localName.equals("hostaddress")) {
            record.hostaddress = buffer.toString();
        } else if (localName.equals("presence")) {
            record.presence = buffer.toString();
        } else if (localName.equals("resource")) {
            record.resource = buffer.toString();
        } else if (localName.equals("username")) {
            record.username = buffer.toString();
        } 
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        buffer.append(ch, start, length);
    }

    public ArrayList<XMLChatRecord> retrieveRecordList() {
        return recordList;
    }
}
