package somes.oneTimeDeals.teded_word_parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by anme on 12.07.17.
 */
public class SAXPars extends DefaultHandler {
    private String thisElement;

    private Set<String> words = new HashSet<>();

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse XML...");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        thisElement = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (thisElement.equals("s")) {

            String word = new String(ch, start, length).trim();
            if(word.length()>2)  words.add(word);
//            doc.setId(new Integer(new String(ch, start, length)));
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        thisElement = "";
    }

    @Override
    public void endDocument() {
        System.out.println("Stop parse XML...");
    }

    public Set<String> getWords() {
        return words;
    }
}
