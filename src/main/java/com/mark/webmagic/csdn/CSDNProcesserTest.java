package com.mark.webmagic.csdn;

import us.codecraft.webmagic.Spider;

public class CSDNProcesserTest{
    private static final String TARGET_URL = "http://blog.csdn.net/ycd500756";
    public static void main(String[] args){
        Spider.create(new CSDNProcesser())
                .addUrl(TARGET_URL)
                .addPipeline(new CSDNPipeLine())
                .thread(5)
                .run();
    }
}
