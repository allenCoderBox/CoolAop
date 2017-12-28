package cool.coder.allen.com.buildsrc.inject.impl

import cool.coder.allen.com.buildsrc.core.model.AopHelper
import cool.coder.allen.com.buildsrc.core.model.MethodFilter
import cool.coder.allen.com.buildsrc.core.utils.CLogger
import cool.coder.allen.com.buildsrc.inject.StubInject
import cool.coder.allen.com.buildsrc.inject.handle.InjectFileHanlder
import javassist.CtClass
import javassist.CtMethod
import javassist.CtNewMethod
import javassist.bytecode.AnnotationsAttribute
import javassist.bytecode.MethodInfo
import javassist.bytecode.annotation.StringMemberValue

import java.lang.annotation.Annotation

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


class AopInject extends StubInject {
    private AopHelper aopHelper


    void injectClass(CtClass c, String filePath, String path) {


        Annotation pointCut = c.getAnnotation(AopHelper.pointCut)
        if (pointCut != null) {

        }




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
            CLogger.defalutLog.info("merge method : " + methodName)
            CtMethod newMethod = CtNewMethod.copy(backPresed, newMethodName, c, null);
            c.addMethod(newMethod)
            backPresed.setBody(getProxyMethodBody(methodName))
        }

        writeFile(c, path)
    }

    private String getProxyMethodBody(String clazz, String methodName) {
        StringBuffer body = new StringBuffer();
        body.append("new " + clazz + "().setParams(this, \"" + methodName + "\")")
        body.append(".setObjects(\$args)")
        body.append(".excute(); ")
        return body.toString();
    }

    @Override
    void injectAction(String path) {
        aopHelper = new AopHelper()
        loadPool(path)
        injectFile(path, new InjectFileHanlder() {
            @Override
            void injectClass(CtClass c, String filePath, String rootPath) {
                c.getDeclaredMethods().each { CtMethod method ->
                    method.getAnnotations().each { Annotation annotation ->
                        if (annotation.annotationType().canonicalName.equals(AopHelper.pointCut)) {
                            MethodInfo methodInfo = method.getMethodInfo()
                            AnnotationsAttribute attribute = methodInfo.getAttribute(AnnotationsAttribute.visibleTag)
                            //获取注解属性
                            javassist.bytecode.annotation.Annotation mAnnotation = attribute.getAnnotation(annotation.annotationType().canonicalName);
                            //获取注解
                            String pointName = ((StringMemberValue) mAnnotation.getMemberValue("pointName")).value
                            aopHelper.put(new MethodFilter().setClazz(c.name).setFiterMethod(pointName))
                        }
                    }
                }
            }
        })



        injectFile(path, new InjectFileHanlder() {
            @Override
            void injectClass(CtClass c, String filePath, String rootPath) {
                if (aopHelper.isContainFilter(c.name)) {
                    return
                }

                aopHelper.getFilterList().each { MethodFilter filter ->

                    CtMethod[] backPreseds = c.getDeclaredMethods(filter.getFiterMethod())
                    for (CtMethod backPresed : backPreseds) {
                        String methodName = backPresed.name;
                        String newMethodName = methodName + "\$impl"
                        CtMethod newMethod = CtNewMethod.copy(backPresed, newMethodName, c, null);
                        c.addMethod(newMethod)
                        backPresed.setBody(getProxyMethodBody(filter.getClazz(), methodName))
                        CLogger.defalutLog.info("merge method : " + c.name + "." + methodName)
                    }
                    writeFile(c, path)
                }
            }
        })


    }

    @Override
    void importClass() {
//        pool.importPackage("com.coder.allen.com.coolaop.Aop.impl.BackPressedAop");
    }

}
