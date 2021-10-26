package aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 通过xml添加AOP
 */
public class AopAct {

    public void startAct() {
        System.out.println("做前");
    }

    public void endAct() {
        System.out.println("做后");
    }

    public void check(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("做前");

        joinPoint.proceed();

        System.out.println("做后");
    }
}
