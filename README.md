# MyBatisCodeGen


### 兼容依赖
```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus</artifactId>
    <version>3.5.2</version>
</dependency>
```
```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.5.2</version>
</dependency>
```
### 导入依赖

```xml
<dependency>
    <groupId>fit.wenchao</groupId>
    <artifactId>mybatis-code-gen-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### 示例配置文件application.yml

```yml
#开启mybatis codegen
mybatis:
  codegen:
    enabled: true
    parent_pck: fit.wenchao.test
    author: wc
    db_url: jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false
    db_username: root
    db_password: wc123456
    controller_on: false
```

### 使用示例
生成代码
```java

@SpringBootApplication
public class MybatisCodeGenTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisCodeGenTestApplication.class, args);
        MybatisCodeGenerator.generateStructureCode();
    }

}
```


### 配置项

enabled: 必填，是否开启MyBatisCodeGen，true则开启，否则不开启

parent_pck:必填， 指定存放生成代码的包，如果指定的包是com.example，那么生成的代码将处于：项目目录/模块目录/src/main/java/com/example

db_url:必填， 指定连接数据库的连接，例如：mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false

db_username:必填，指定连接数据库的用户名

db_password: 必填，指定连接数据库的密码

module_loc:
选填，指定模块路径，必须以/开头，如果不指定，那么默认在当前主模块生成，如果指定的模块名是/testmodule，那么生成的代码将处于：项目目录/testmodule/src/main/java/com/example

author: 选填，指定项目的作者，将在生成的代码注释中体现

controller_on: 选填，默认不生产



