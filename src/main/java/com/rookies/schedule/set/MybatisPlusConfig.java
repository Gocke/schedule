package com.rookies.schedule.set;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@MapperScan("com.rookies.schdulepackage com.ugaoxin.springbootmybatisplus.set;\n" +
        "\n" +
        "import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;\n" +
        "import org.apache.ibatis.reflection.MetaObject;\n" +
        "import org.springframework.stereotype.Component;\n" +
        "\n" +
        "import java.util.Date;\n" +
        "\n" +
        "/** \n" +
        "* @version:1.0\n" +
        "* @Description: test\n" +
        "* @author: Gocke  \n" +
        "*/  \n" +
        " \n" +
        "@Component\n" +
        "public class MetaObjectHandlerConfig implements MetaObjectHandler {\n" +
        "\n" +
        "  @Override\n" +
        "  public void insertFill(MetaObject metaObject) {\n" +
        "    System.out.println(\"插入方法实体填充\");\n" +
        "    setFieldValByName(\"testDate\", new Date(), metaObject);\n" +
        "  }\n" +
        "\n" +
        "  @Override\n" +
        "  public void updateFill(MetaObject metaObject) {\n" +
        "    System.out.println(\"更新方法实体填充\");\n" +
        "  }\n" +
        "}\n.mapper*")//这个注解，作用相当于下面的@Bean MapperScannerConfigurer，2者配置1份即可
public class MybatisPlusConfig {


	  /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
