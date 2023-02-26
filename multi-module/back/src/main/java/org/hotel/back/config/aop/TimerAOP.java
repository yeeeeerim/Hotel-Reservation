package org.hotel.back.config.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAOP {


    @Pointcut("@annotation(org.hotel.back.annotation.PerformTimer)")
    private void enableTimer() {}


    @Around("enableTimer()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        var stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();

        System.out.println("성능시간 : " + stopWatch.getTotalTimeSeconds());

        return result;
    }
}
