package cool.coder.allen.com.buildsrc.core.xml

import cool.coder.allen.com.buildsrc.core.config.AopEnvironment
import cool.coder.allen.com.buildsrc.core.utils.CLogger
import org.dom4j.Attribute
import org.dom4j.Document
import org.dom4j.io.SAXReader

/**
 * Created by husongzhen on 17/11/29.
 */

 class XMlClint {


    private List<String> stringList = new ArrayList<>();

     void ideaLibs() {
        def libsDir = new File(AopEnvironment.news().getAppDir() + "/.idea/libraries")
        CLogger.getDefalutLog().err(libsDir.absolutePath)
        libsDir.eachFile { file ->
            SAXReader saxReader = new SAXReader()
            Document document = saxReader.read(file)
            List<Attribute> nodes = document.selectNodes("//component/library/CLASSES/root/@url")
            nodes.each { node ->
                def libDir = node.getValue()
                if (libDir.startsWith("jar://") && !libDir.contains("\$APPLICATION_HOME_DIR\$")) {
                    libDir = libDir
                            .replace("jar://\$USER_HOME\$", AopEnvironment.news().getUserHome())
                            .replace("!/", "")
                            .replace("jar://\$PROJECT_DIR\$", AopEnvironment.news().appDir)
                    CLogger.getDefalutLog().info(libDir)
                    stringList.add(libDir)
                }
            }
        }
    }

    List<String> getStringList() {
        return stringList
    }
}
