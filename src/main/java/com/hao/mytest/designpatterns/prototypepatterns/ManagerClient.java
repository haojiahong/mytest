package com.hao.mytest.designpatterns.prototypepatterns;

/**
 * Created by haojiahong on 16/5/11.
 */
public class ManagerClient {
    public static void main(String[] args) {
        PrototypeManager manager = PrototypeManager.getPrototypeManager();
        manager.addOfficialDocument("far", new FAR());
        manager.addOfficialDocument("srs", new SRS());
        OfficialDocument doc1, doc2;
        doc1 = manager.getOfficialDocument("far");
        doc1.display();
        doc2 = manager.getOfficialDocument("far");
        doc2.display();
        System.out.println("两个文档相同吗" + (doc1 == doc2));
    }
}
