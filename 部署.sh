appName="SkyEyeSystem-0.0.1-SNAPSHOT.jar"

# 编译
mvn clean
mvn package

# 上传文件  确保已经配置了SSH公钥身份验证

scp './target/'$appName root@114.115.209.223:'/root/SpringBootJar/'

# 执行
ssh root@114.115.209.223 << EOF
# 停止当前运行在80端口的应用
sudo fuser -k 8080/tcp

# 后台启动上传的 JAR 文件
nohup java -jar /root/SpringBootJar/$appName --spring.profiles.active=HWCloud > /dev/null 2>&1 &
EOF

echo "http://114.115.209.223:8080/api/swagger-ui/index.html"