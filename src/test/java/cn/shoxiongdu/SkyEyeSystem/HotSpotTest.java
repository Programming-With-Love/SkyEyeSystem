package cn.shoxiongdu.SkyEyeSystem;

import cn.shoxiongdu.SkyEyeSystem.task.hotspot.CrawlerTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HotSpotTest {

    @Autowired
    CrawlerTask crawlerTask;

    @Test
    void test() {

        crawlerTask.crawl();


    }





}
