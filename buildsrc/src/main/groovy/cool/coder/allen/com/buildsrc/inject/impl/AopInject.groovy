package cool.coder.allen.com.buildsrc.inject.impl

import cool.coder.allen.com.buildsrc.inject.StubInject
import javassist.CtClass
import javassist.CtMethod
import javassist.CtNewMethod
/**
 *
 *
 *
 *
 *
 * 一般我们在生成类的时候，都有哪些方案
 * 在方法前后插入代码
 * 给现有类添加新方法
 *
 *
 *
 * Created by husongzhen on 17/11/28.
 */








public class AopInject extends StubInject {


    @Override
    public void injectClass(CtClass c, String filePath, String path) {
        CtClass superClass = c.getSuperclass();
        if (superClass.name.contains("OnCreateWrapper")) {
            return
        }
        if (c.name.contains("OnCreateWrapper")) {
            return
        }
        CtMethod[] backPreseds = c.getDeclaredMethods("onBackPressed")
        for (CtMethod backPresed : backPreseds) {
            String methodName = backPresed.name;
            String newMethodName = methodName + "\$impl"
            project.logger.error(methodName)
            CtMethod newMethod = CtNewMethod.copy(backPresed, newMethodName, c, null);
            c.addMethod(newMethod)
            backPresed.setBody(getProxyMethodBody(methodName))
        }

        writeFile(c, path)
    }

    private String getProxyMethodBody(String methodName) {
        StringBuffer body = new StringBuffer();
        body.append("new BackPressedAop().setParams(this, \"" + methodName + "\")")
        body.append(".setObjects(\$args)")
        body.append(".excute(); ")
        return body.toString();
    }


    @Override
    public void importClass() {
        pool.importPackage("com.coder.allen.com.coolaop.LockUtils");
        pool.importPackage("com.coder.allen.com.coolaop.Aop.impl.BackPressedAop");
        pool.importPackage("com.coder.allen.com.coolaop.Aop.OnCreateWrapper");
    }

}
