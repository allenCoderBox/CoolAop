package cool.coder.allen.com.buildsrc.inject

import cool.coder.allen.com.buildsrc.utils.JarZipUtil
import javassist.ClassPool
import javassist.CtClass
import javassist.NotFoundException
import org.gradle.api.Project
/**
 * Created by husongzhen on 17/11/24.
 */

abstract class StubInject implements UIClassInject {
    protected ClassPool pool = ClassPool.getDefault();
    protected Project project


    @Override
    void injectDir(String path, Project project) throws NotFoundException {
        this.project = project
        loadPool(path)
        injectFile(path)
    }





    private void injectFile(String path) {
        File dir = new File(path);
        if (dir.isDirectory()) {
            dir.eachFileRecurse { File file ->
                String filePath = file.absolutePath;
                if (checkPath(filePath)) {
                    // 判断当前目录是否是在我们的应用包里面
                    if (isInMyPackage(filePath)) {
                        String className = getClassName(filePath);
                        transFile(filePath, className, path)
                    }
                }
            }
        }
    }

    @Override
    void injectJar(String path, Project project) throws NotFoundException {
        File jarFile = new File(path)
        // jar包解压后的保存路径
        String jarZipDir = jarFile.getParent() + "/" + jarFile.getName().replace('.jar', '')
        // 解压jar包, 返回jar包中所有class的完整类名的集合（带.class后缀）
        List classNameList = JarZipUtil.unzipJar(path, jarZipDir)
        // 删除原来的jar包
        jarFile.delete()
        try {
            injectDir(jarZipDir, project)
        } catch (Exception e) {
            project.logger.error(e.getMessage())
        }

        // 从新打包jar
        JarZipUtil.zipJar(jarZipDir, path)

        // 删除目录
        FileUtils.deleteDirectory(new File(jarZipDir))
    }


    private void loadPool(String path) {
        pool.appendClassPath(path)
        //project.android.bootClasspath 加入android.jar，否则找不到android相关的所有类
        pool.appendClassPath(project.android.bootClasspath[0].toString());
        importClass()
    }

    protected boolean checkPath(String filePath) {
        return filePath.endsWith(".class") && !filePath.contains('R$') && !filePath.contains('$') && !filePath.contains('R.class') && !filePath.contains("BuildConfig.class")
    }

    void transFile(String filePath, String className, String path) {
        try {
            CtClass c = pool.getCtClass(className)
            if (c.isFrozen()) {
                c.defrost()
            }
            injectClass(c, filePath, path)
            c.detach()//用完一定记得要卸载，否则pool里的永远是旧的代码
        } catch (Exception e) {
            project.logger.error("inject class: " + e.getMessage())
        }

    }

    abstract void injectClass(CtClass c, String filePath, String path);

    void writeFile(CtClass c, String path) {
        c.writeFile(path)
    }


    abstract void importClass()


    abstract String getClassName(String filePath)

    abstract boolean isInMyPackage(String filePath)

}
