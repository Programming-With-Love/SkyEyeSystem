<h3 align="center">SpringBootWebStarter</h3>

  <p align="center">
     a SpringBoot web starter
    <br />
    <a href="./README_zh.md">中文</a>
    ·
    <a href="./README.md">English</a>
  </p>
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About the Project</a>
    </li>
    <li>
      <a href="#quick-start">Quick Start</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

## About the Project

There are many great SpringBoot starters on GitHub, but I didn't find one that meets my needs, so I created this Web-enhanced template.



Reasons:

- Your time should be focused on creating amazing things. Solving problems and helping others is the right thing to do.

- You shouldn't be doing the same task over and over again.
  - Unified interface response encapsulation
  - Unified request object encapsulation
  - Unified encapsulation of entity classes
  - Support for cross-domain configuration
  - ......

Of course, no Starter can meet all projects, because your needs may be different. So I will add more in the near future. You can also suggest changes by forking this repository and creating pull requests or opening issues. Thanks to everyone who contributed to expanding this template!



## Quick Start

This section explains how to quickly use this Starter.

### Prerequisites

Make sure your project is a SpringBoot starter. And the version is greater than 3.0.0.
### Installation

## Usage

#### 1. Cross-domain configuration support

Cross-domain configuration support is not enabled by default. If you want to support cross-domain configuration, please turn on the following switch in your main configuration file.

``` yaml
cors:
  enable: true
```

At this point, your program will automatically enable cross-domain support.



#### 2.  Swagger configuration

The title of the Swagger interface debugging page will automatically read your project name, port number, and global request path configuration. If you do not configure it, the default values will be used. So if necessary, please add relevant configuration in the configuration file.

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

After the program starts, the address of the Swagger page and the interface unified prefix will be automatically printed.

```java
Local Server DEV: http://localhost:8080/api/swagger-ui/index.html
```



#### 3. Unified entity class encapsulation

Inherit this class to your database entity class to have relevant common database fields.

``` java
cn.shoxiongdu.SkyEyeSystem.entity.base.BaseEntity.java
```

Common database fields are as follows

| Attribute Name | Attribute Type | Description            | Remarks                                                                        |
| -------------- | -------------- | ---------------------- | ------------------------------------------------------------------------------ |
| id             | Long           | Database auto-increment id |                                                                                |
| createTime     | LocalDateTime  | Record creation time   | The current time is automatically filled when creating the corresponding object. |
| updateTime     | LocalDateTime  | Record updated time    | If it is an update operation, please call the update() method of this parent class. The current time will be automatically filled inside.                                      |
| deleted        | byte           | Delete flag  0-not deleted 1-deleted | If it is a delete operation, please call the delete() method of this parent class. It will be automatically set to 1. At the same time, its updateTime will also be updated.      |

After your entity class inherits the Base Entity, the relevant common fields automatically take effect.

#### 4. Unified interface response

```java
package cn.shoxiongdu.SkyEyeSystem.response.base;

public class Resp<T> {
  private int code;
  private String message;
  private T data;
  private boolean success;
}
```

Usage

``` java
// Success, response to business data
Resp.success("Business data");

// Failed, directly responds to the error code enumeration defined
Resp.error(RespENUM.SERVER_ERROR);
// Failed, respond to exception object
Resp.error(new RunTimeException("Runtime error message"));
// Failed, respond to the specified error code and error message
Resp.error(405,"User login expired");
```

Known error code enumeration

```java
public enum RespENUM {

    SUCCESS(200, "Operation succeeded"),

    CLIENT_ERROR_AUTH_NOT_LOGIN(401, "Not logged in"),
    CLIENT_ERROR_AUTH_LOGIN(402, "Login failed"),
    CLIENT_ERROR_AUTH_OVERDUE(403, "Login expired"),
    CLIENT_ERROR_PARAM(405, "Parameter verification failed"),

    SERVER_ERROR(500, "internal server Error");
}
```



#### 5. Unified paging request object encapsulation

Just inherit it.

```java
public class PageRequest {
    private int pageNumber = 1;
    private int pageSize = 10;
}
```



#### 6. Redis-related operations

Pre-configuration: Create a file in your Resource/config/ directory: [redis.setting](src/main/resources/config/redis.setting) and configure the corresponding host and port number.

```
host=114.115.209.223
port=6379
```

Usage

``` java
@AutoWrite RedisHelper redisHelper

// Get Jedis instance.
redisHelper.getJedis();

// Get key prefix, if you have configured redis.key.prefix in the configuration file, this value will be returned, otherwise appName will be returned.
redisHelper.getKeyPrefix();
```



#### 7. Request tool class

``` java

// Parse the request IP through the request object.
String ip = RequestUtils.getIpAddress(HttpServletRequest request);

System.out.print(ip);
```



#### 8. ResponseUtils tool class

``` java
// Return the file to the specified response
ResponseUtils.writeFile(new File("filePath"), response, fileName);

// Return bytes array to the specified response
byte[] byte = new byte[1024];
// Business operation, write in content...

ResponseUtils.writeFile(byte, response, fileName);
```



## Contributing

Contributions will make the open source community an excellent place for learning, motivation, and creativity. Thank you very much for any contribution you have made.



1. Fork the project

2. Create a branch for the feature (git checkout-b Feature/AamazingFeature)

3. Commit changes (`git-Commit-m'Add some AmazingFeature'`)

4. Push the branch (`git Push origin feature / AamazingFeature`)

5. Open a pull request


## License

Distributed based on the MIT license, please follow the corresponding open source agreement during transmission: [MIT license](./LICENSE )



## Contact

- 杜少雄 email@shaoxiongdu.cn
- WeChat: 15603430511
- Personal blog: https://shaoxiongdu.cn
