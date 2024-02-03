package cc.lupss.doujin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("cc.lupss.doujin.dal.mapper")
@EnableAsync
@EnableScheduling
@ComponentScan(basePackages = {
        "cc.lupss.doujin.controller",
        "cc.lupss.doujin.config",
        "cc.lupss.doujin.service",
        "cc.lupss.doujin.dal.*"})
public class DoujinServerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DoujinServerApplication.class, args);
    }
}
