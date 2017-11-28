package cool.coder.allen.com.buildsrc

import com.android.build.gradle.AppExtension
import org.dom4j.Document
import org.dom4j.Node
import org.dom4j.io.SAXReader
import org.gradle.api.Plugin
import org.gradle.api.Project


/**
 * Created by husongzhen on 17/11/24.
 */

class CoolAopPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        String homeDir = project.getRootDir()
        def libsDir = new File(homeDir + "/.idea/libraries")
        libsDir.eachFile { file ->
            project.logger.error(file.name)
            SAXReader saxReader = new SAXReader()
            Document document = saxReader.read(file)



            List<Node> nodes = document.selectNodes("//component/library/CLASSES")
            nodes.each { node ->

                project.logger.error(node.selectSingleNode("root").asXML())
            }


        }













        assert "a" == "b"



        project.logger.error "================自定义插件start！=========="
        project.logger.error project.rootDir.absolutePath

        def android = project.extensions.findByType(AppExtension)
        def transform = new AopTransform(project);
        android.registerTransform(transform)
        project.logger.error "================自定义插件end！=========="
    }
}
