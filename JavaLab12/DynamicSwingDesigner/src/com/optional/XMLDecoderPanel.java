package com.optional;

import com.compulsory.DesignPanel;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Tutorial: https://howtodoinjava.com/java/serialization/xmlencoder-and-xmldecoder-example/
 * This class is a functional class and it helps us to deserialize from XML an object
 */
public class XMLDecoderPanel {

    public static DesignPanel deserializeFromXML() throws IOException {
        FileInputStream fis = new FileInputStream("panel.xml");
        XMLDecoder decoder = new XMLDecoder(fis);
        DesignPanel decodedPanel = (DesignPanel) decoder.readObject();
        decoder.close();
        fis.close();
        return decodedPanel;
    }
}
