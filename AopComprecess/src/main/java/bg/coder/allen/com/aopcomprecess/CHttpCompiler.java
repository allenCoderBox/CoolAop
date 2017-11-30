package bg.coder.allen.com.aopcomprecess;

import com.google.auto.service.AutoService;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import bg.coder.allen.com.aopanimation.AopAction;
import bg.coder.allen.com.aopanimation.PointCut;

/**
 * 作者：husongzhen on 17/9/30 10:17
 * 邮箱：husongzhen@musikid.com
 */

@AutoService(Processor.class)
public class CHttpCompiler extends AbstractProcessor {


    private Filer mFiler;
    private Elements mElementUtils;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
        mElementUtils = processingEnvironment.getElementUtils();
        messager = processingEnvironment.getMessager();
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(PointCut.class)) {
            PointCut pointCut = element.getAnnotation(PointCut.class);
            Class[] classes = pointCut.imports();
            AopAction action = pointCut.action();
            String a = pointCut.point();
            error(element, a);
        }
        return false;
    }

    private boolean isValid(Class fHttpClass, String targetThing, Element element) {
        // 父元素必须是类，而不能是接口或枚举
        if (element.getKind() != ElementKind.CLASS) {
            error(element, "@%s %s may only be contained in classes. (%s.%s)",
                    fHttpClass.getSimpleName(), targetThing, element.getSimpleName(),
                    element.getSimpleName());
            return false;
        }
        return true;
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(PointCut.class.getName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private void error(Element e, String msg) {
        messager.printMessage(Diagnostic.Kind.ERROR, msg, e);
    }


    private void error(Element e, String msg, Object... args) {
        messager.printMessage(Diagnostic.Kind.ERROR, msg, e);
    }


    private void msg(Element e, String msg, Object... args) {
        messager.printMessage(Diagnostic.Kind.NOTE, String.format(msg, args), e);
    }
}
