package cool.coder.allen.com.buildsrc.inject.impl

import cool.coder.allen.com.buildsrc.inject.StubInject
import javassist.CtClass
import javassist.CtMethod
import javassist.CtNewMethod

/**
 * Created by husongzhen on 17/11/24.
 */

public class CopyInject extends StubInject {
    String packageName = "com"

    List<String> sets = Arrays.asList("com.yumingchuan.lib.base.BaseActivity", "android.support.v7.app.AppCompatActivity")

    @Override
    void injectClass(CtClass c, String filePath, String path) {
        CtMethod backPresed
        try {
            backPresed = c.getDeclaredMethod("onBackPressed")
        } catch (Exception e) {
            backPresed == null;
        }
        if (backPresed != null) {
            project.logger.error("insert method: " + c.getName())
            StringBuffer body = new StringBuffer();
            body.append("if (LockUtils.isLockOpen()) {return;}")
            backPresed.insertBefore(body.toString())
            writeFile(c, path)
        } else {
            def superClassName
            try {
                superClassName = c.getSuperclass().getName();
                project.logger.error("super class: " + superClassName)
            } catch (Exception e) {
                superClassName = e.getMessage();
            }

            if (sets.contains(superClassName.toString())) {
                CtMethod method = CtNewMethod.make(createBackPressed(), c)
                c.addMethod(method)
                writeFile(c, path)
                project.logger.error("create method: " + c.getName())
            }
        }
    }

    public String createBackPressed() {
        StringBuffer stringBuffer = new StringBuffer()
        stringBuffer
                .append(" public void onBackPressed() {")
                .append("if (LockUtils.isLockOpen()) {return;}")
                .append("}")
        return stringBuffer.toString()
    }


    @Override
    void importClass() {
        pool.appendClassPath("/Users/husongzhen/.gradle/caches/transforms-1/files-1.1/appcompat-v7-26.0.0-beta1.aar/405d78edce39eb3afdb29de7550fb7af/jars/classes.jar")
        pool.importPackage("com.coder.allen.com.coolaop.LockUtils");
        pool.importPackage("android.support.v7.app.AppCompatActivity");
    }


}
