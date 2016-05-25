package com.hao.mytest.designpatterns.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by haojiahong on 16/5/10.
 */
public class XMLUtil {
    public static Object getBean() {
        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = null;
            document = documentBuilder.parse(new File("/Users/haojiahong/work/mytest/mytest/src/main/resources/config.xml"));
            NodeList nl = document.getElementsByTagName("className");
            Node classNode = nl.item(0).getFirstChild();
            String className = classNode.getNodeValue();
            Class c = Class.forName(className);
            Object o = c.newInstance();
            return o;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
