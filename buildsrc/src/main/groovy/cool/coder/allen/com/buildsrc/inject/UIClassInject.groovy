package cool.coder.allen.com.buildsrc.inject

import javassist.NotFoundException
import org.gradle.api.Project

/**
 * Created by husongzhen on 17/11/24.
 */

interface UIClassInject {

    void injectDir(String path, Project project) throws NotFoundException

    void injectJar(String path, Project project) throws NotFoundException
}
