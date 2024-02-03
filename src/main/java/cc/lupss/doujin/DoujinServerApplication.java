package cc.lupss.doujin.doujinserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("cc.lupss.doujin.doujinserver.dal.mapper")
@EnableAsync
@EnableScheduling
@ComponentScan(basePackages = {
        "cc.lupss.doujin.doujinserver.controller",
        "cc.lupss.doujin.doujinserver.config",
        "cc.lupss.doujin.doujinserver.service",
        "cc.lupss.doujin.doujinserver.dal.*"})
public class DoujinServerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DoujinServerApplication.class, args);
    }
}
