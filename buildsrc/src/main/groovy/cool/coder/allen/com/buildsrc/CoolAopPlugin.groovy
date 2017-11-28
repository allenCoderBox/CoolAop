package cool.coder.allen.com.buildsrc

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
/**
 * Created by husongzhen on 17/11/24.
 */

class CoolAopPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.logger.error "================自定义插件start！=========="
        def android = project.extensions.findByType(AppExtension)
        def transform = new AopTransform(project);
        android.registerTransform(transform)
        project.logger.error "================自定义插件end！=========="
    }
}
