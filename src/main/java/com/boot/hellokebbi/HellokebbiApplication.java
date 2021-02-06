package com.boot.hellokebbi;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @PackageName: com.boot.hellokebbi
 * @ClassName: HellokebbiApplication
 * @Description: This is HellokebbiApplication class by Skwen.
 * @Author: Skwen
 * @Date: 2020-10-13 18:04
 */
@SpringBootApplication
//@Slf4j
@RetrofitScan("com.github.lianjiatech.retrofit.spring.boot.test")
public class HellokebbiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HellokebbiApplication.class, args);
        System.out.println("HellokebbiApplication-启动完毕!");
    }

}
