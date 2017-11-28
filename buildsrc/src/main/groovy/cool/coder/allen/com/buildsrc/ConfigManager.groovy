package cool.coder.allen.com.buildsrc

import cool.coder.allen.com.buildsrc.inject.UIClassInject
import org.gradle.api.Project

/**
 * Created by husongzhen on 17/11/26.
 */

public class ConfigManager {

    static  Set<UIClassInject> injects;

    public static void parseConfig(Project project) {
        injects = new HashSet<>();
        File pathFile = new File("./");

        String appPath = pathFile.absolutePath.replace(".", "");

        File file = new File(appPath + "/config.properties")

        project.logger.error(file.absolutePath)
        if (!file.exists()) {
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file))
        String tempClass;
        while (tempClass = reader.readLine()) {
            if (tempClass != null) {
                Class clazz = Class.forName(tempClass);
                UIClassInject inject = clazz.newInstance()
                injects.add(inject)
            }
        }
    }


    public static Set<UIClassInject> getInjects() {
        return injects;
    }

}
