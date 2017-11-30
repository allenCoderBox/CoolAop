package cool.coder.allen.com.buildsrc.core.config;

import org.gradle.api.Project;

import java.util.List;

import cool.coder.allen.com.buildsrc.core.utils.CLogger;
import cool.coder.allen.com.buildsrc.core.xml.XMlClint;

/**
 * Created by husongzhen on 17/11/29.
 */

public class AopEnvironment {


    private Project project;
    private String appDir;
    private String buildDir;

    private String userHome;
    private XMlClint xMlClint;


    private AopEnvironment() {
    }


    public static AopEnvironment news() {
        return Clazz.environment;
    }


    private static class Clazz {
        private static final AopEnvironment environment = new AopEnvironment();
    }

    public void init(Project project) {
        this.project = project;
        this.appDir = project.getRootDir().getAbsolutePath();
        this.buildDir = project.getBuildDir().getAbsolutePath();
        this.userHome = System.getProperty("user.home");
        CLogger.initLog();
        xMlClint = new XMlClint();
        xMlClint.ideaLibs();
    }

    public String getAppDir() {
        return appDir;
    }


    public String getUserHome() {
        return userHome;
    }


    public String getBuildDir() {
        return buildDir;
    }


    public Project getProject() {
        return project;
    }


    public List<String> getDepLibdir() {
        return xMlClint.getStringList();
    }
}
