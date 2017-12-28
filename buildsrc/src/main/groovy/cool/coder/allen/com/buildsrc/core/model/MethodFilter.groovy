package cool.coder.allen.com.buildsrc.core.model;

/**
 * Created by husongzhen on 17/12/28.
 */

 class MethodFilter {

    private String clazz;
    private String fiterMethod;

     String getClazz() {
        return clazz;
    }

     MethodFilter setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }

     String getFiterMethod() {
        return fiterMethod;
    }

     MethodFilter setFiterMethod(String fiterMethod) {
        this.fiterMethod = fiterMethod;
        return this;
    }
}
