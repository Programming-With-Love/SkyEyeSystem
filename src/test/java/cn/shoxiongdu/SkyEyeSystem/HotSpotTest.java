package cn.shoxiongdu.SkyEyeSystem;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.response.hotspot.CoderUtilBaseRes;
import cn.shoxiongdu.SkyEyeSystem.task.hotspot.CrawlerTask;
import cn.shoxiongdu.SkyEyeSystem.task.hotspot.HotDataCrawler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class HotSpotTest {

    @Autowired
    CrawlerTask crawlerTask;

    @Test
    void test() {

        crawlerTask.crawl();


    }





}
