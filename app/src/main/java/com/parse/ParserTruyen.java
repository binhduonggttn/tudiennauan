package com.parse;

import java.io.BufferedReader;
import java.io.StringReader;



import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.mode.ObjectMonNgon;

import java.util.ArrayList;

public class ParserTruyen {
    ArrayList<ObjectMonNgon> myData=null;
    public ArrayList<ObjectMonNgon> getNewList(String XMLData)
    {
        try {

            BufferedReader br=new BufferedReader(new StringReader(XMLData));
            InputSource is=new InputSource(br);

            /************  Parse XML **************/

            XMLParser parser=new XMLParser();
            SAXParserFactory factory=SAXParserFactory.newInstance();
            SAXParser sp=factory.newSAXParser();
            XMLReader reader=sp.getXMLReader();
            reader.setContentHandler(parser);
            reader.parse(is);

            myData=(ArrayList<ObjectMonNgon>) parser.list;





        } catch (Exception e) {
            // TODO: handle exception
        }

        return myData;
    }
}
