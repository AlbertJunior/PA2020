package com.optional;

import javax.swing.*;
import java.beans.ExceptionListener;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Tutorial: https://howtodoinjava.com/java/serialization/xmlencoder-and-xmldecoder-example/
 * This class is a functional class and it helps us to serialize to XML an object
 */
public class XMLEncoderPanel {

    public static void serializeToXML(JPanel container) throws IOException {
        FileOutputStream fos = new FileOutputStream("panel.xml");
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                System.out.println("Exception! :" + e.toString());
            }
        });
        encoder.writeObject(container);
        encoder.close();
        fos.close();
    }
}
