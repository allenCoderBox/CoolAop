package cool.coder.allen.com.buildsrc.core.config

import cool.coder.allen.com.buildsrc.core.utils.CLogger
import cool.coder.allen.com.buildsrc.core.xml.XMlClint
import org.gradle.api.Project

/**
 * Created by husongzhen on 17/11/29.
 */

class AopEnvironment {


    private Project project;
    private String appDir;
    private String buildDir;

    private String userHome;
    private XMlClint xMlClint;


    private AopEnvironment() {
    }


    static AopEnvironment news() {
        return Clazz.environment;
    }


    private static class Clazz {
        private static final AopEnvironment environment = new AopEnvironment();
    }

    void init() {
        init();
    }

    void init(Project project) {
        this.project = project;
        this.appDir = project.getRootDir().getAbsolutePath();
        this.buildDir = project.getBuildDir().getAbsolutePath();
        this.userHome = System.getProperty("user.home");
        CLogger.initLog();
        xMlClint = new XMlClint();
        xMlClint.ideaLibs();
    }

    String getAppDir() {
        return appDir;
    }


    String getUserHome() {
        return userHome;
    }


    String getBuildDir() {
        return buildDir;
    }


    Project getProject() {
        return project;
    }


    List<String> getDepLibdir() {
        return xMlClint.getStringList();
    }
}
