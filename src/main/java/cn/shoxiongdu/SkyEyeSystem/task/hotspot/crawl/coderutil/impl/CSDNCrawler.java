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
import java.util.Objects;

@Component
@AllArgsConstructor
public class CSDNCrawler extends AbstractCoderUtilCrawler implements HotDataCrawler {

    PlatformMapper platformMapper;

    @Override
    public String getUrl() {
        return "https://www.coderutil.com/api/resou/v1/csdn";
    }

    @Override
    public List<HotSpot> crawlHotSpotData() {
        return getCoderUtilData();
    }


    @Override
    public Platform getPlatform() {
        return platformMapper.selectById(5L);
    }

    @Override
    public HotSpot parseHotSpot(JSONObject jsonObject) {
        HotSpot hotSpot = new HotSpot();
        hotSpot.setRank(jsonObject.getInt("rank"));
        hotSpot.setKeyword(jsonObject.getStr("keyword"));
        hotSpot.setUrl(jsonObject.getStr("url"));
        hotSpot.setHotValue( Objects.isNull(jsonObject.getInt("hotRankScore")) ? 0 : jsonObject.getInt("hotRankScore"));
        return hotSpot;
    }

}
