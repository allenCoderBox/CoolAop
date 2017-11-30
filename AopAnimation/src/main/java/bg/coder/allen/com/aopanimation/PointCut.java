package bg.coder.allen.com.aopanimation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Created by husongzhen on 17/11/30.
 */


@Target(TYPE)
@Retention(CLASS)
public @interface PointCut {
    String pointName();

    AopAction action();

    String[] importsPool();
}
