package studio.geek.util;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Liuqi
 * Date: 2017/4/9.
 */

/**
 * 简单的代理类统计执行时间
 */
public class MyInvocationHandler implements InvocationHandler {
    private static Logger logger = SimpleLogger.getSimpleLogger(MyInvocationHandler.class);
    private Object target;

    public MyInvocationHandler() {
        super();
    }

    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        long end = System.currentTimeMillis();
        logger.debug(target.getClass().getSimpleName() + " " + method.getName() + " 花费时间 :" + (end - start) + "ms");
        return result;
    }
}
