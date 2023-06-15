package cn.shoxiongdu.SkyEyeSystem.hotspot;

import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import cn.shoxiongdu.SkyEyeSystem.mapper.hot.HotSpotMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class WordCloudTest {
    @Autowired
    HotSpotMapper hotSpotMapper;

    @Test
    void test() {

        Map<String, Integer> wordCount = getWordCount();
    }

    private Map<String, Integer> getWordCount() {

        TokenizerEngine engine = TokenizerUtil.createEngine();

        Map<String, Integer> wordCount = new HashMap<>();
        hotSpotMapper.selectList(null).forEach(hotSpot -> {

            Result result = engine.parse(hotSpot.getKeyword());
            if (result.hasNext()) {
                String word = result.next().getText();
                if (wordCount.containsKey(word)) {
                    wordCount.put(word, wordCount.get(word) + 1);
                } else {
                    wordCount.put(word, 1);
                }
            }

        });
        return wordCount;
    }


}
