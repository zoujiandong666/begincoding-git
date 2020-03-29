package study.mengduo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @aothor mengDuo
 * @date 2020/3/9 22:15
 */
@Slf4j
@RestController
public class FirstController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("hello")
    public String hello(){
        log.info("DataSources"+ dataSource.getClass());
        return "hello spring boot";
    }
}
