package com.fantacode.fitme;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Athul on 08-03-2016.
 */
public class XmlParser {

    private XmlPullParserFactory xmlFactoryObject;
    private XmlPullParser myParser;
    private Context m_context;


    public XmlParser(Context context){
        m_context = context;
    }

    public List<String> getQuotes(String cat){
        List<String> quotes = new ArrayList<String>();
        try {
            xmlFactoryObject = XmlPullParserFactory.newInstance();
            myParser = xmlFactoryObject.newPullParser();
            InputStream is = m_context.getResources().openRawResource(R.raw.quotes);

            myParser.setInput(is, null);

            int event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT)
            {
                String name=myParser.getName();
                switch (event){
                    case XmlPullParser.START_TAG:
                        if(name.equals("Quote")){
                            String category = myParser.getAttributeValue(null,"Category");
                            if(category.equals(cat)){
                                myParser.nextTag();
                                myParser.next();
                                quotes.add(myParser.getText());
                            }

                        }
                        break;

                    case XmlPullParser.END_TAG:
                        break;
                }
                event = myParser.next();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return quotes;
    }
}
