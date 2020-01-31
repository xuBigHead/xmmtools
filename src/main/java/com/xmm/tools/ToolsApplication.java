package com.xmm.tools;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mm.xu
 * @since 2020/1/31
 */
@SpringBootApplication
@MapperScan("com.xmm.tools.**.mapper")
public class ToolsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ToolsApplication.class, args);
    }

}
