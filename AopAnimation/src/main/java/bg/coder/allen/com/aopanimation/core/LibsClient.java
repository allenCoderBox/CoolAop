package bg.coder.allen.com.aopanimation.core;


import com.alibaba.fastjson.JSON;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by husongzhen on 17/11/29.
 */

public class LibsClient {


    private String configPath = SystemConfig.getAppHomeDir() + "/libs_config.txt";

    private List<String> stringList = new ArrayList<>();


    public LibsClient readLibsDir(final File libsDir) {
        Observable.create(new ObservableOnSubscribe<File>() {
            @Override
            public void subscribe(ObservableEmitter<File> e) throws Exception {
                for (File file : libsDir.listFiles()) {
                    e.onNext(file);
                }
            }
        }).map(new Function<File, List<Attribute>>() {
            @Override
            public List<Attribute> apply(File file) throws Exception {
                SAXReader saxReader = new SAXReader();
                Document document = saxReader.read(file);
                List nodes = document.selectNodes("//component/library/CLASSES/root/@url");
                return nodes;
            }
        }).flatMap(new Function<List<Attribute>, ObservableSource<Attribute>>() {
            @Override
            public ObservableSource<Attribute> apply(List<Attribute> attributes) throws Exception {
                return Observable.fromIterable(attributes);
            }
        }).filter(new Predicate<Attribute>() {
            @Override
            public boolean test(Attribute attribute) throws Exception {
                String libDir = attribute.getValue();
                return libDir.startsWith("jar://") && !libDir.contains("$APPLICATION_HOME_DIR$");
            }
        }).map(new Function<Attribute, String>() {
            @Override
            public String apply(Attribute attribute) throws Exception {
                String libDir = attribute.getValue();
                libDir = libDir
                        .replace("jar://$USER_HOME$", SystemConfig.getUserDir())
                        .replace("!/", "")
                        .replace("jar://$PROJECT_DIR$", SystemConfig.getAppHomeDir());
                return libDir;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                stringList.add(s);
            }
        });
        return this;
    }


    public LibsClient writeToConfig() {
        FileWriter writer = null;
        try {
            writer = new FileWriter(new File(configPath));
            writer.write(JSON.toJSONString(stringList));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return this;
    }


    public List<String> readFromConfig() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(configPath));
            StringBuffer buffer = new StringBuffer();
            String ch;

            while ((ch = reader.readLine()) != null) {
                buffer.append(ch);
            }
            List<String> libs = JSON.parseArray(buffer.toString(), String.class);
            return libs;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
