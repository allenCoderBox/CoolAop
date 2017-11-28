package cool.coder.allen.com.buildsrc

import javassist.CtMethod

class Utils {


    static String getMethodName(CtMethod ctmethod) {
        def methodName = ctmethod.getName();
        return methodName.substring(
                methodName.lastIndexOf('.') + 1, methodName.length());
    }

    static String getClassName(String filePath, String packageName) {
        int index = indexPackage(filePath, packageName)
        int end = filePath.length() - 6 // .class = 6
        return filePath.substring(index, end).replace('\\', '.').replace('/', '.')
    }

    static boolean isInMyPackage(String filePath, String packageName) {
        int index = indexPackage(filePath, packageName)
        return index != -1;
    }


    private static int indexPackage(String filePath, String packageName) {
        filePath.indexOf(packageName)
    }
}