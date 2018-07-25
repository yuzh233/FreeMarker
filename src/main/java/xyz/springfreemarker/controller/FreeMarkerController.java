package xyz.springfreemarker.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import xyz.springfreemarker.pojo.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: yu_zh
 * DateTime: 2018/07/25 14:16
 */
@Controller
public class FreeMarkerController {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    /**
     * 1、从spring容器中获得FreeMarkerConfigurer对象。
     * 2、从FreeMarkerConfigurer对象中获得Configuration对象。
     * 3、使用Configuration对象获得Template对象。
     * 4、创建数据集
     * 5、创建输出文件的Writer对象。
     * 6、调用模板对象的process方法，生成文件。
     * 7、关闭流。
     *
     * @throws Exception
     */
    @RequestMapping("/genHtml")
    @ResponseBody
    public String genHtml(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Template template = configuration.getTemplate("template1.ftl");
        Map model = new HashMap<>();
        model.put("key", "world");
        model.put("pojo", new Person(100000, "lisa"));
        model.put("personList", Arrays.asList(
                new Person(1, "lisa"),
                new Person(2, "rose"),
                new Person(3, "jacky"),
                new Person(4, "john"),
                new Person(5, "tom")));
        Map personMap = new HashMap<>();
        personMap.put("key1", new Person(1, "lisa"));
        personMap.put("key2", "value2");
        personMap.put("key3", "value3");
        personMap.put("key4", "value4");
        personMap.put("key5", "value5");
        model.put("personMap", personMap);
        model.put("date", new Date());

        Writer out = new FileWriter(new File("D:/spring-freemarker.html"));
        template.process(model, out);
        out.close();
        return "ok";
    }
}
