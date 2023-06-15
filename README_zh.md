


<h3 align="center">SpringBootWebStarter</h3>

  <p align="center">
     a SpringBoot web starter
    <br />
    <a href="./README_zh.md">中文</a>
    ·
    <a href="./README.md">English</a>
  </p>
<details open="open">
  <summary>目录</summary>
  <ol>
    <li>
      <a href="#关于项目">关于项目</a>
    </li>
    <li>
      <a href="#快速启动">快速启动</a>
      <ul>
        <li><a href="#先决条件">先决条件</a></li>
        <li><a href="#安装">安装</a></li>
      </ul>
    </li>
    <li><a href="#使用">使用</a></li>
    <li><a href="#贡献">贡献</a></li>
    <li><a href="#许可证">许可证</a></li>
    <li><a href="#联系">联系</a></li>
  </ol>
</details>

## 关于项目

GitHub上有很多很棒的SpringBoot的Starter，但是，我没有找到一个真正适合我需求的Starter，所以我创建了这个Web增强的模板。



原因如下：



- 你的时间应该集中在创造一些惊人的东西上。解决问题并帮助他人的项目

- 你不应该一遍又一遍地做同样的任务
  - 统一接口响应封装
  - 统一请求对象封装
  - 统一实体类的封装
  - 支持跨域配置
  - ......

当然，没有一个Starter可以满足所有项目，因为您的需求可能不同。所以我会在不久的将来添加更多。您还可以通过分叉此回购并创建拉取请求或打开问题来建议更改。感谢所有为扩展此模板做出贡献的人！



## 快速启动

此处说明了如何快速的使用本Starter。

### 先决条件

确保您的项目是SpringBoot启动器。并且版本大于3.0.0。
### 安装

## 使用

#### 1. 跨域配置支持

默认是不开启跨域配置支持的，如果您想要支持跨域配置，请在您的主配置文件开启如下开关

``` yaml
cors:
  enable: true
```

此时，您的程序就会自动启用支持跨域的配置了。



#### 2. Swagger配置

Swagger的接口调试页面 标题会自动读取您的项目名称、端口号、全局请求路径等配置。如果您没有配置，会使用默认值。所以如果需要的话，请在配置文件中加入相关配置。

``` yaml
spring:
  application:
    name: [you app name]
#
server:
  port: [you app port]
  servlet:
    context-path: [ you app context-path ]
```

程序启动之后，会自动打印Swagger页面的地址，端口和接口统一前缀会自动读取。

```java
Local Server DEV: http://localhost:8080/api/swagger-ui/index.html
```



#### 3. 统一实体类封装

将您的数据库实体类继承该类，就会拥有相关的通用的数据库字段。

``` java
cn.shoxiongdu.SkyEyeSystem.entity.base.BaseEntity.java
```

通用数据库字段如下

| 属性名称   | 属性类型      | 说明                         | 备注                                                         |
| ---------- | ------------- | ---------------------------- | ------------------------------------------------------------ |
| id         | Long          | 数据库自增id                 |                                                              |
| createTime | LocalDateTime | 记录创建时间                 | 创建对应的对象会自动填充当前时间。                           |
| updateTime | LocalDateTime | 记录更新时间                 | 如果是更新操作，请调用该父类的update()方法即可。内部会自动填充当前时间。 |
| deleted    | byte          | 删除标记  0-未删除  1-已删除 | 如果是删除操作，请调用该父类的delete()方法即可。内部会自动设置为1。同时更新其updateTime |

当您的实体类继承该BaseEntity之后，相关通用字段会自动生效。

#### 4. 统一接口响应

```java
package cn.shoxiongdu.SkyEyeSystem.response.base;

public class Resp<T> {
    private int code;
    private String message;
    private T data;
    private boolean success;
}
```

用法

``` java
// 成功 响应业务数据
Resp.success("业务数据");

// 失败  直接响应定义好的错误码枚举
Resp.error(RespENUM.SERVER_ERROR);
// 失败 响应异常对象
Resp.error(new RunTimeException("运行时错误信息"));
// 失败 响应指定错误码和错误信息
Resp.error(405,"用户登陆过期");
```

已知的错误码枚举

```java
public enum RespENUM {

    SUCCESS(200, "操作成功"),

    CLIENT_ERROR_AUTH_NOT_LOGIN(401, "未登陆"),
    CLIENT_ERROR_AUTH_LOGIN(402, "登陆失败"),
    CLIENT_ERROR_AUTH_OVERDUE(403, "登陆过期"),
    CLIENT_ERROR_PARAM(405, "参数校验失败"),

    SERVER_ERROR(500, "服务器内部错误");
}
```



#### 5. 统一分页请求对象封装

直接继承即可.

```java
public class PageRequest {
    private int pageNumber = 1;
    private int pageSize = 10;
}
```



#### 6. Redis相关操作

前置配置: 在您的Resource/config/中创建文件:  [redis.setting](src/main/resources/config/redis.setting)  并配置相应的主机和端口号。

```
host=114.115.209.223
port=6379
```

用法

``` java
@AutoWrite RedisHelper redisHelper

// 获取Jedis实例
redisHelper.getJedis();

// 获取key前缀，如果您在配置文件中配置了redis.key.prefix则返回该值，如果没有配置，则返回appName。
redisHelper.getKeyPrefix();
```



#### 7. Request工具类

``` java

// 通过请求对象解析请求IP。
String ip = RequestUtils.getIpAddress(HttpServletRequest request);

System.out.print(ip);
```



#### 8. ResponseUtils工具类

``` java
// 给指定response返回文件 
ResponseUtils.writeFile(new File("filePath"), response, fileName);

// 给指定response返回字节数组
byte[] byte = new byte[1024];
// 业务操作，写入内容...

ResponseUtils.writeFile(byte, response, fileName);
```



## 贡献

贡献使开源社区成为一个学习、激励和创造的绝佳场所。非常感谢您所做的任何贡献。



1.fork项目

2.创建功能分支（`git checkout-b Feature/AamazingFeature`）

3.提交更改（`git-Commit-m'添加一些AmazingFeature`）

4.推送至分支（`git Push origin feature/AamazingFeature`）

5.打开拉取请求


## 许可证

基于MIT的许可证分发，传输请遵循相关开源协议: [MIT许可证](./LICENSE )



## 联系

- 杜少雄 email@shaoxiongdu.cn
- 微信: 15603430511
- 个人博客: https://shaoxiongdu.cn
