package study.mengduo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 全局异常处理类
 * @aothor mengDuo
 * @date 2020/3/29 18:29
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object globalException(HttpServletRequest servletRequest,Exception e){
        log.error("url{},msg{}",servletRequest.getRequestURI(),e);
        HashMap<String, Object> map = new HashMap<>();
        map.put("status",-1);
        map.put("msg",e.toString());
        map.put("url",servletRequest.getRequestURI());
        return map;
    }
}
