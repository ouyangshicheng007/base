package com.yimi.oysc.global;

import com.yimi.oysc.exception.UserDefineException;
import com.yimi.oysc.vo.common.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/9/27 11:04
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = UserDefineException.class)
    public ResultVO<?> bizExceptionHandler(UserDefineException e){
        log.error("系统自定义异常", e);
        return ResultVO.errorResult(e.getErrCode(), e.getErrMsg());
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value = Exception.class)
    public ResultVO<?> exceptionHandler(HttpServletResponse response, Exception e){
        log.error("系统异常", e);
        switch (response.getStatus()) {
            case HttpServletResponse.SC_BAD_REQUEST:
                return ResultVO.errorResult(1, "请求参数错误，请检查");
            case HttpServletResponse.SC_FORBIDDEN:
                return ResultVO.errorResult(3, "禁止访问");
            default:
                return ResultVO.errorResult(-99, "系统内部异常，请联系管理员");
        }
    }

}
