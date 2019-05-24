package york.test.weatherTest.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import york.test.weatherTest.enums.Cities;
import york.test.weatherTest.bean.ResultBean;
import york.test.weatherTest.dto.District;
import york.test.weatherTest.dto.TaiwanDistricts;
import york.test.weatherTest.dto.TaiwanDistrictsDTO;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class ControllerAop {

    @Inject
    private TaiwanDistrictsDTO taiwanDistrictsDTO;

    @Pointcut(value = "execution(* york.test.weatherTest.controller.WeatherController.*(..))")
    public void inputValidate() {}

    @Around("inputValidate()")
    public ResultBean checkCityName(ProceedingJoinPoint pj) throws Exception {
        log.info("Method call: {} {}", pj.getSignature().getDeclaringType(), pj.getSignature().getName());

        Object[] objects = pj.getArgs();
        ResultBean<?> resultBean;

        try {
            List<String> cities =taiwanDistrictsDTO.getData()
                    .stream()
                    .map(TaiwanDistricts::getCityName)
                    .collect(Collectors.toList());

            Map<String, List<String>> cityDistrictMap = taiwanDistrictsDTO.getData()
                    .stream()
                    .collect(Collectors.toMap(TaiwanDistricts::getCityName, taiwanDistricts ->
                        taiwanDistricts.getDistricts()
                                .stream()
                                .map(District::getName)
                                .collect(Collectors.toList())
                    ));


            String city = String.valueOf(objects[0]);
            String district = String.valueOf(objects[1]);

            if (city.equals(Cities.台中市.toString())) { city = Cities.臺中市.toString();}
            if (city.equals(Cities.台北市.toString())) { city = Cities.臺北市.toString();}
            if (city.equals(Cities.台南市.toString())) { city = Cities.臺南市.toString();}
            objects[0] = city;

            if (!cities.contains(city) || !cityDistrictMap.get(city).contains(district)) {
                throw new IllegalArgumentException(city + ", " + district + " is not available city or district name");
            }

            resultBean = (ResultBean<?>) pj.proceed(objects);

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
