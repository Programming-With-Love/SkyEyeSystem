package cn.shoxiongdu.SkyEyeSystem.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.Platform;
import cn.shoxiongdu.SkyEyeSystem.entity.user.User;
import cn.shoxiongdu.SkyEyeSystem.response.base.Resp;
import cn.shoxiongdu.SkyEyeSystem.service.hotspot.PlatformService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotspot")
@AllArgsConstructor
@Tag(name = "热点管理")
public class HotSpotController {

    PlatformService platformService;

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

}
