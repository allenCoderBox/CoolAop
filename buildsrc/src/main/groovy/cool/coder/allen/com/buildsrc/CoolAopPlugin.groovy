package cool.coder.allen.com.buildsrc

import com.android.build.gradle.AppExtension
import cool.coder.allen.com.buildsrc.core.config.AopEnvironment
import cool.coder.allen.com.buildsrc.core.utils.CLogger
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by husongzhen on 17/11/24.
 */

class CoolAopPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {


        AopEnvironment.news().init(project)
        project.logger.error "================自定义插件start！=========="
        def android = project.extensions.findByType(AppExtension)
        def transform = new AopTransform(project);
        android.registerTransform(transform)
        project.logger.error "================自定义插件end！=========="
//        systemInfo(project)


    }

    private void systemInfo(Project project) {
        System.getProperties().each { p ->
            CLogger.getDefalutLog().info(p.key + " = " + p.value)
        }

        File f2 = new File(this.getClass().getResource("").getPath());
        project.logger.error(f2.path)
        project.logger.error(System.getProperty("java.class.path"))
        project.logger.error(System.getProperty("user.dir"))
        project.logger.error(System.getProperty("user.home"))
        project.logger.error(System.getProperties().toString())
    }
}
