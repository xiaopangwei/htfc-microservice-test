package cn.htfc.web.job;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
@MapperScan("cn.htfc.web.job.mapper")
public class JobApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
    }


}
