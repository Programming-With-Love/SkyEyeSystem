package cn.shoxiongdu.SkyEyeSystem.controller;

import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.Platform;
import cn.shoxiongdu.SkyEyeSystem.request.hotspot.HotSpotListReq;
import cn.shoxiongdu.SkyEyeSystem.response.base.Resp;
import cn.shoxiongdu.SkyEyeSystem.service.hotspot.HotSpotService;
import cn.shoxiongdu.SkyEyeSystem.service.hotspot.PlatformService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/hotspot")
@AllArgsConstructor
@Tag(name = "热点管理")
public class HotSpotController {

    PlatformService platformService;
    HotSpotService hotSpotService;

    @PostMapping("/platform")
    @Operation(summary = "添加平台")
    public Resp<Boolean> addPlatform(@RequestBody Platform platform) {
        return Resp.success(platformService.save(platform));
    }

    @GetMapping("/platform")
    @Operation(summary = "平台列表")
    public Resp<List<Platform>> listPlatform() {
        return Resp.success(platformService.list());
    }

    @GetMapping("/wordCount")
    @Operation(summary = "词频统计")
    public Resp<Map<String, Integer>> wordCount() {

        TokenizerEngine engine = TokenizerUtil.createEngine();

        Map<String, Integer> wordCount = new HashMap<>();
        hotSpotService.list().forEach(hotSpot -> {
            System.out.println(hotSpot.getKeyword());
            Result result = engine.parse(hotSpot.getKeyword());
            while (result.hasNext()) {
                String word = result.next().getText();

                if (wordIsFilter(word)) {
                    continue;
                }

                wordCount.compute(word, (key, value) -> value == null ? 1 : value + 1);
            }
        });

        // 按照词频进行排序
        Map<String, Integer> sortedWordCount = new LinkedHashMap<>();

        wordCount.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> sortedWordCount.put(entry.getKey(), entry.getValue()));

        return Resp.success(sortedWordCount);
    }

    /**
     * 判断词是否需要过滤的工具方法
     * 过滤条件:
     * - null
     * - 空字符串
     * - 单个字符
     * - 数字
     *
     * @param word 要判断的词
     * @return 如果词需要过滤，则返回true；否则返回false
     */
    private boolean wordIsFilter(String word) {
        return StringUtils.isBlank(word) || word.length() < 2 || NumberUtils.isCreatable(word);
    }

    @PostMapping("/")
    @Operation(summary = "查询热点")
    public Resp<Page<HotSpot>> listHotSpot(@RequestBody HotSpotListReq req) {
        return Resp.success(hotSpotService.listHotSpot(req));
    }

}
