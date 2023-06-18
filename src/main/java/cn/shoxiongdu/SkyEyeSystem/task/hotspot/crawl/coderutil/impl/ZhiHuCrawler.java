package cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.coderutil.impl;

import cn.hutool.json.JSONObject;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.Platform;
import cn.shoxiongdu.SkyEyeSystem.mapper.hot.PlatformMapper;
import cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.HotDataCrawler;
import cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.coderutil.AbstractCoderUtilCrawler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ZhiHuCrawler extends AbstractCoderUtilCrawler implements HotDataCrawler {

    PlatformMapper platformMapper;

    private static final String URL = "https://www.coderutil.com/api/resou/v1/zhihu";

    @Override
    public String getUrl() {
        return URL;
    }

    @Override
    public List<HotSpot> crawlHotSpotData() {
        return getCoderUtilData();
    }


    @Override
    public Platform getPlatform() {
        return platformMapper.selectById(3L);
    }

    @Override
    public HotSpot parseHotSpot(JSONObject jsonObject) {
        HotSpot hotSpot = new HotSpot();
        hotSpot.setRank(jsonObject.getInt("rank"));
        hotSpot.setKeyword(jsonObject.get("keyword").toString());
        hotSpot.setUrl(jsonObject.get("url").toString());
        return hotSpot;
    }

}
