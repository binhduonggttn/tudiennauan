package com.parse;

/**
 * Created by Administrator on 07/05/2016.
 */
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.mode.ObjectMonNgon;
import java.util.ArrayList;

public class XMLParser extends DefaultHandler {
    ArrayList<ObjectMonNgon> list=null;

    // string builder acts as a buffer
    StringBuilder builder;

    ObjectMonNgon jobsValues=null;

    public ArrayList<ObjectMonNgon> getItemList() {
        return list;
    }


    // Initialize the arraylist
    // @throws SAXException

    @Override
    public void startDocument() throws SAXException {

        /******* Create ArrayList To Store XmlValuesModel object ******/
        list = new ArrayList<ObjectMonNgon>();
    }


    // Initialize the temp XmlValuesModel object which will hold the parsed info
    // and the string builder that will store the read characters
    // @param uri
    // @param localName ( Parsed Node name will come in localName  )
    // @param qName
    // @param attributes
    // @throws SAXException

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        /****  When New XML Node initiating to parse this function called *****/

        // Create StringBuilder object to store xml node value
        builder=new StringBuilder();

        if(localName.equals("items")){

        }
        else if(localName.equals("item")){

            // Log.i("parse","----Job start----");
            /********** Create Model Object  *********/
            jobsValues = new ObjectMonNgon();
        }
    }



    // Finished reading the login tag, add it to arraylist
    // @param uri
    // @param localName
    // @param qName
    // @throws SAXException

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {


        if(localName.equals("item")){

            /** finished reading a job xml node, add it to the arraylist **/
            list.add(jobsValues );

        }     else  if(localName.equalsIgnoreCase("id")){

            jobsValues.setId(builder.toString());
        }
        else  if(localName.equalsIgnoreCase("name")){

            jobsValues.setName(builder.toString());
        }
        else if(localName.equalsIgnoreCase("show")){

            jobsValues.setShow(builder.toString());
        }  else if(localName.equalsIgnoreCase("img")){

            jobsValues.setImg(builder.toString());
        }



        // Log.i("parse",localName.toString()+"========="+builder.toString());
    }


    // Read the value of each xml NODE
    // @param ch
    // @param start
    // @param length
    // @throws SAXException

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        /******  Read the characters and append them to the buffer  ******/
        String tempString=new String(ch, start, length);
        builder.append(tempString);
    }
}
