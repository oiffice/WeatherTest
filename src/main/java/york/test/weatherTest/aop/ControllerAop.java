package york.test.weatherTest.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import york.test.weatherTest.bean.ResultBean;

@Aspect
@Component
@Slf4j
public class ControllerAop {

    @Pointcut(value = "execution(* york.test.weatherTest.controller.WeatherController.*(..))")
    public void inputValidate() {}

    @Around("inputValidate()")
    public ResultBean checkCityName(ProceedingJoinPoint pj) throws Exception {
        log.info("Method call: {} {}", pj.getSignature().getDeclaringType(), pj.getSignature().getName());

        Object[] objects = pj.getArgs();
        ResultBean<?> resultBean;

        try {
            //TODO: currently pass all args

            resultBean = (ResultBean<?>) pj.proceed();
        } catch (Throwable throwable) {

            log.error("User request weather information failed: {}", throwable.getMessage());

            resultBean = new ResultBean();
            resultBean.setMessage(throwable.getMessage());
            resultBean.setCode(ResultBean.FAILED);

//            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
//            request.setAttribute("resultBean", resultBean);
//            request.setAttribute("exception", new InvalidateCityNameException(pj.getSignature() + t.getMessage()));
//            request.getRequestDispatcher("/error/error00").forward(request, response);
        }

        return resultBean;
    }

}
