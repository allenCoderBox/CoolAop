package cool.coder.allen.com.buildsrc.inject.handle

import javassist.CtClass;

/**
 * Created by husongzhen on 17/12/28.
 */

interface InjectFileHanlder {


    void injectClass(CtClass c, String filePath, String path)

}
