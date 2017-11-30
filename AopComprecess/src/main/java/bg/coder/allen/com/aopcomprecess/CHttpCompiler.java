package bg.coder.allen.com.aopcomprecess;

import com.alibaba.fastjson.JSON;
import com.google.auto.service.AutoService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import bg.coder.allen.com.aopanimation.AopAction;
import bg.coder.allen.com.aopanimation.PointCut;
import bg.coder.allen.com.aopanimation.PointCutParams;
import bg.coder.allen.com.aopanimation.core.LibsClient;
import bg.coder.allen.com.aopanimation.core.SystemConfig;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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
    public boolean process(Set<? extends TypeElement> set, final RoundEnvironment roundEnvironment) {
        Observable.create(new ObservableOnSubscribe<Element>() {
            @Override
            public void subscribe(ObservableEmitter<Element> e) throws Exception {
                for (Element element : roundEnvironment.getElementsAnnotatedWith(PointCut.class)) {
                    e.onNext(element);
                }
            }
        }).map(new Function<Element, PointCutParams>() {
            @Override
            public PointCutParams apply(Element element) throws Exception {
                PointCut pointCut = element.getAnnotation(PointCut.class);
                String[] classes = pointCut.importsPool();
                AopAction action = pointCut.action();
                String a = pointCut.pointName();
                return new PointCutParams()
                        .setAction(action)
                        .setImports(classes)
                        .setPointName(a);
            }
        }).subscribe(new Consumer<PointCutParams>() {
            @Override
            public void accept(PointCutParams pointCutParams) throws Exception {
                try {
                    FileWriter writer = new FileWriter(new File(SystemConfig.getAppHomeDir() + "/point_cut_config.txt"));
                    writer.append(JSON.toJSONString(pointCutParams));
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        new LibsClient()
                .readLibsDir(new File(SystemConfig.getAppHomeDir() + "/.idea/libraries"))
                .writeToConfig();
        return false;
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
