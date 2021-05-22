package com.gua.guabank.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * <p>
 * 代码生成器演示
 * </p>
 */
public class MpGenerator {

    //数据库用户名密码
    private static String username = "root";
    private static String password = "888";

    //表前缀，不需要则写空
    private static String tablePrefix = "";
    //数据库连接url
    private static String dbUrl = "jdbc:mysql://localhost:3306/bank?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8";


    /**
     * 如要使用该代码生成工具，请将该jar包添加到 pom文件中，
     * 否则会出现：java.lang.NoClassDefFoundError: org/apache/velocity/context/Context
     * (生成完之后也建议把该jar包去掉，工程代码瘦身)
     * <dependency>
     * <groupId>org.apache.velocity</groupId>
     * <artifactId>velocity-engine-core</artifactId>
     * </dependency>
     *
     * @param args
     */
    public static void main(String[] args) {

        AutoGenerator mpg = new AutoGenerator();

        String outPut = "E:\\output\\";
        if (StringUtils.isEmpty(outPut)) {
            throw new RuntimeException("输出目录不能为空！");
        }
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outPut + "java");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("auto-genergator");

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();

        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setUrl(dbUrl);
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[]{tablePrefix});// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setEntityColumnConstant(true);
        strategy.setInclude(new String[]{"user","bankcard","transfer_record"}); // 需要生成的表
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.gua.guabank.modules.transfar");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        TemplateConfig tc = new TemplateConfig();

        mpg.setConfig(new ConfigBuilder(pc, dsc, strategy, tc, gc));

        ConfigBuilder config = mpg.getConfig();

        Map<String, String> pathInfo = config.getPathInfo();

        //目录设置修改
        pathInfo.put(ConstVal.XML_PATH, outPut + "resources/mapper/");
        mpg.execute();
    }

}
