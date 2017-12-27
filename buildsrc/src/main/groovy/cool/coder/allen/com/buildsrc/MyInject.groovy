package cool.coder.allen.com.buildsrc

import cool.coder.allen.com.buildsrc.inject.impl.AopInject
import javassist.NotFoundException
import org.gradle.api.Project

/**
 * Created by husongzhen on 17/11/24.
 */

class MyInject {


    static void injectDir(String path, String packageName, Project project) throws NotFoundException {
        AopInject inject = new AopInject()
        inject.injectDir(path, project)
    }

    static void injectJar(String path, String packageName, Project project) throws NotFoundException {
        AopInject inject = new AopInject()
        inject.injectJar(path, project)
    }


}
