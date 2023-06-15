package cn.shoxiongdu.SkyEyeSystem.task.hotspot;

import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.mapper.hot.HotSpotMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CrawlerTask {

    // 所有平台
    @Autowired private List<HotDataCrawler> platformCrawlerList;

    @Autowired private HotSpotMapper hotSpotMapper;

    @Scheduled(fixedRate = 3600000) // 每1小时执行一次
    public void crawl() {
        log.info("开始爬取热点数据");
        // 循环所有平台 用线程池执行爬取
        platformCrawlerList.forEach(crawler -> {
            log.info("开始平台 -> " + crawler.getPlatformId());

            Long platformId = crawler.getPlatformId();

            // 爬取数据
            List<HotSpot> hotSpots = crawler.crawlHotSpotData();

            // 处理数据
            hotSpots.forEach(hotSpot -> {
                hotSpot.setPlatformId(platformId);

                    // 删除旧数据
                    hotSpotMapper.deleteByKeyword(hotSpot.getKeyword());

                    // 插入
                    hotSpotMapper.insert(hotSpot);

            });
            log.info("结束平台 -> " + crawler.getPlatformId());
        });
        log.info("爬取热点数据完成");

    }

}
