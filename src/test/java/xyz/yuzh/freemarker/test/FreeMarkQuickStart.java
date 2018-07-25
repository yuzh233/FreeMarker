package xyz.yuzh.freemarker.test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import xyz.springfreemarker.pojo.Person;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: yu_zh
 * DateTime: 2018/07/25 12:17
 */
public class FreeMarkQuickStart {

    public String filePath = "D:\\IdeaProjects\\taotao-parent\\FreeMarker\\src\\main\\webapp\\WEB-INF\\ftl";

    /**
     * 使用模板技术实现静态网页输出
     * <p>
     * 第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对应的版本号。
     * 第二步：设置模板文件所在的路径。
     * 第三步：设置模板文件使用的字符集。一般就是utf-8.
     * 第四步：加载一个模板，创建一个模板对象。
     * 第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
     * 第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
     * 第七步：调用模板对象的process方法输出文件。
     * 第八步：关闭流。
     */
    @Test
    public void genHtml() throws Exception {
        //创建一个Configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //设置模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File(filePath));
        //设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");
        //创建模板文件，再加载模板文件 模板文件的后缀官方提供是.ftl 其实任何类型都行。
        Template template = configuration.getTemplate("template1.ftl");
        //创建模板文件需要展示数据的数据集对象，可以使用POJO，也可以使用map 一般是使用map
        Map model = new HashMap<>();
        // [简单的key]
        model.put("key", "world");
        // [pojo]
        model.put("pojo", new Person(100000, "lisa"));
        // [List]
        model.put("personList", Arrays.asList(
                new Person(1, "lisa"),
                new Person(2, "rose"),
                new Person(3, "jacky"),
                new Person(4, "john"),
                new Person(5, "tom")));
        // [Map]
        Map personMap = new HashMap<>();
        personMap.put("key1",new Person(1, "lisa"));
        personMap.put("key2","value2");
        personMap.put("key3","value3");
        personMap.put("key4","value4");
        personMap.put("key5","value5");
        model.put("personMap", personMap);

        // [date] 当前版本不支持java8的 LocaleDateTime
        model.put("date",new Date());

        // [test Null]
//        ftl.put("test","有值");

        //创建一个FileWriter对象 指定生成的静态文件的文件路径及文件名
        FileWriter writer = new FileWriter(new File(filePath + "/result.html"));
        //调用模板对象的process方法输出文件
        template.process(model, writer);
        //关闭
        writer.close();
    }
}
