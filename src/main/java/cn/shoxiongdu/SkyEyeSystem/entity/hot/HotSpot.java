package cn.shoxiongdu.SkyEyeSystem.entity.hot;

import cn.shoxiongdu.SkyEyeSystem.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 热点类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HotSpot extends BaseEntity {

    /**
     * 平台id
     */
    private long platformId = -1;

    /**
     * 排序
     */
    @TableField("`rank`")
    private int rank = -1;

    /**
     * 关键字
     */
    private String keyword = "";

    /**
     * 链接
     */
    private String url = "";

    /**
     * 封面
     */
    private String image = "";

    /**
     * 热度
     */
    private int hotValue = 0;
}
