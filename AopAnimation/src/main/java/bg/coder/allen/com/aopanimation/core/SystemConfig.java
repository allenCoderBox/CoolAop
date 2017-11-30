package bg.coder.allen.com.aopanimation.core;

/**
 * Created by husongzhen on 17/11/30.
 */

public class SystemConfig {

    public static final String getUserDir() {
        return System.getProperty("user.home");
    }


    public static final String getAppHomeDir() {
        return System.getProperty("user.dir");
    }

}
