package bg.coder.allen.com.aopanimation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by husongzhen on 17/11/30.
 */


@Target(TYPE)
@Retention(SOURCE)
public @interface PointCut {
    String point();

    AopAction action();

    Class[] imports();
}
