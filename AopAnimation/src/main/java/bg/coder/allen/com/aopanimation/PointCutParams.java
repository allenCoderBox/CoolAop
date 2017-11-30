package bg.coder.allen.com.aopanimation;

/**
 * Created by husongzhen on 17/11/30.
 */

public class PointCutParams {
    private AopAction action;
    private String[] imports;
    private String pointName;

    public AopAction getAction() {
        return action;
    }

    public PointCutParams setAction(AopAction action) {
        this.action = action;
        return this;
    }

    public String[] getImports() {
        return imports;
    }

    public PointCutParams setImports(String[] imports) {
        this.imports = imports;
        return this;
    }

    public String getPointName() {
        return pointName;
    }

    public PointCutParams setPointName(String pointName) {
        this.pointName = pointName;
        return this;
    }
}
