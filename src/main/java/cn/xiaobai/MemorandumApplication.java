package cn.xiaobai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 备忘录启动入口
 */
@EnableTransactionManagement
@SpringBootApplication
@MapperScan("cn.xiaobai.mapper")
public class MemorandumApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemorandumApplication.class, args);
    }

}
