/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timersandinterceptors;

import java.util.Date;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Nudista
 */
public class Interceptor {
    
    private Date d;

    @AroundInvoke

    public Object intercept(InvocationContext context) throws Exception {
d = new Date();
        System.out.println("Director Interceptor:" + context.getMethod().getName() + " at time " + d.toString() + " by director" );

        Object result = context.proceed();

        System.out.println("Director Interceptor:" + context.getMethod().getName() + " was succesfully done");

        return result;

    }

}
