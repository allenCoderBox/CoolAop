package cool.coder.allen.com.buildsrc.core.model
/**
 * Created by husongzhen on 17/12/28.
 */

class AopHelper {
    public static final String pointCut = "bg.coder.allen.com.aopanimation.PointCut"


    private List<MethodFilter> filterList = new ArrayList<>()

    void put(MethodFilter methodFilter) {
        if (methodFilter != null) {
            filterList.add(methodFilter)
        }
    }


    boolean isContainFilter(String clazzName) {
        filterList.each { MethodFilter methodFilter ->
            if (methodFilter.metaClass.equals(clazzName)) {
                return true
            }
        }
        return false
    }

    List<MethodFilter> getFilterList() {
        return filterList
    }


}
