package bg.coder.allen.com.aopanimation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by husongzhen on 17/11/30.
 */


@Target(METHOD)
@Retention(RUNTIME)
public @interface PointCut {
    String pointName();
}
