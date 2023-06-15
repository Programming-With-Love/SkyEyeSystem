package cn.shoxiongdu.SkyEyeSystem.task.hotspot;

import cn.hutool.json.JSONObject;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.Platform;

import java.util.List;

/**
 * 爬取接口
 */
public interface HotDataCrawler {

    /**
     * 爬取数据
     * @return
     */
    List<HotSpot> crawlHotSpotData();

    /**
     * 获取当前平台id
     * @return
     */
    public Long getPlatformId();

    /**
     * json对象转HotSpot
     * @param jsonObject
     * @return
     */
    HotSpot parseHotSpot(JSONObject jsonObject);
}
