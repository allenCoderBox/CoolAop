package bg.coder.allen.com.aopanimation;

/**
 * Created by husongzhen on 17/11/30.
 */

public class PointCutParams {
    private AopAction action;
    private String pointName;

    public AopAction getAction() {
        return action;
    }

    public PointCutParams setAction(AopAction action) {
        this.action = action;
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
