package com.hao.mytest.designpatterns.prototypepatterns;

import lombok.Data;

/**
 * 附件类
 * Created by haojiahong on 16/5/11.
 */
@Data
public class Attachment {
    private String name;//附件名称

    public void download() {
        System.out.println("下载附件，文件名称为：" + name);
    }
}
