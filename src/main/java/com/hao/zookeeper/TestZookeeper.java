package com.hao.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by haojiahong on 17/5/15.
 */
public class TestZookeeper {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 100, null);
        String path = "/dmc";

        zooKeeper.create(path, "1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("创建节点" + path + ",数据为:" + new String(zooKeeper.getData(path, null, null)));

        zooKeeper.setData(path, "2".getBytes(), -1);
        System.out.println("修改节点" + path + "，数据为：" + new String(zooKeeper.getData(path, null, null)));


    }
}
