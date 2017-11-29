package cool.coder.allen.com.buildsrc.core.utils;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import cool.coder.allen.com.buildsrc.core.config.AopEnvironment;

/**
 * Created by husongzhen on 17/11/29.
 */

public class CLogger {


    private static PrintWriter pw;
    private String name;
    private static CLogger logger;


    public static final void initLog() {
        File file = new File(AopEnvironment.news().getBuildDir() + "/Coolaop.log");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            pw = new PrintWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static final CLogger create(String name) {
        return new CLogger(name);
    }


    public static final CLogger getDefalutLog() {
        if (logger == null) {
            logger = new CLogger("coolAop");
        }
        return logger;
    }


    private CLogger(String name) {
        this.name = name;
    }


    public void info(String info) {
        pw.printf("[%s] : %s%n", name, info);
        pw.flush();
    }


    public void err(String error) {
        pw.printf("[%s] [Error]: %s%n", name, error);
        pw.flush();
    }

    public void debug(String debug) {
        pw.printf("[%s] [Debug]: %s%n", name, debug);
        pw.flush();
    }


    public static void close() {
        pw.close();
        pw = null;
    }


}
