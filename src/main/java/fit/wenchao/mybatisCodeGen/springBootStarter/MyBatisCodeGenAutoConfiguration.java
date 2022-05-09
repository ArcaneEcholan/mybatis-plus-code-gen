package fit.wenchao.mybatisCodeGen.springBootStarter;

import fit.wenchao.mybatisCodeGen.codegen.CodeGenContext;
import fit.wenchao.mybatisCodeGen.codegen.CustomVelocityTemplateEngine;
import fit.wenchao.mybatisCodeGen.codegen.MybatisCodeGenerator;
import fit.wenchao.mybatisCodeGen.codegen.MybatisCodeGeneratorProperties;
import fit.wenchao.mybatisCodeGen.codegen.exception.PropertyMissingException;
import fit.wenchao.mybatisCodeGen.codegen.templateOuputWriter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Arrays.asList;

@Configuration
@ConditionalOnClass(MybatisCodeGenerator.class)
@EnableConfigurationProperties(MybatisCodeGeneratorProperties.class)
public class MyBatisCodeGenAutoConfiguration {

    @Autowired
    private MybatisCodeGeneratorProperties mybatisCodeGeneratorProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "mybatis.codegen", value = "enabled", havingValue = "true")
    MybatisCodeGenerator mybatisCodeGenerator(CodeGenContext codeGenContext,
                                              CustomVelocityTemplateEngine customVelocityTemplateEngine) {
        checkPropertiesIntegrity();
        MybatisCodeGenerator mybatisCodeGenerator = new MybatisCodeGenerator(codeGenContext,
                customVelocityTemplateEngine);
        //为generator提供一个便捷的全局访问点，线程不安全
        MybatisCodeGenerator.setStaticMybatisCodeGenerator(mybatisCodeGenerator);
        return mybatisCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "mybatis.codegen", value = "enabled", havingValue = "true")
    CustomVelocityTemplateEngine customVelocityTemplateEngine(TemplateOutputWriterFactory templateOutputWriterFactory) {
        return new CustomVelocityTemplateEngine(templateOutputWriterFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "mybatis.codegen", value = "enabled", havingValue = "true")
    CodeGenContext codeGenContext() {
        checkPropertiesIntegrity();
        return new CodeGenContext(mybatisCodeGeneratorProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "mybatis.codegen", value = "enabled", havingValue = "true")
    TemplateOutputWriterFactory templateOutputWriterFactory(
            RepoImplTemplateOutputWriter repoImplTemplateOutputWriter,
            RepoTemplateOutputWriter repoTemplateOutputWriter,
            ServiceImplTemplateOutputWriter serviceImplTemplateOutputWriter,
            ServiceTemplateOutputWriter serviceTemplateOutputWriter
    ) {
        return new TemplateOutputWriterFactory(
                asList(repoImplTemplateOutputWriter,
                        repoTemplateOutputWriter,
                        serviceImplTemplateOutputWriter,
                        serviceTemplateOutputWriter));
    }

    // region register OutputWriters
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "mybatis.codegen", value = "enabled", havingValue = "true")
    RepoImplTemplateOutputWriter repoImplTemplateOutputWriter() {
        return new RepoImplTemplateOutputWriter();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "mybatis.codegen", value = "enabled", havingValue = "true")
    RepoTemplateOutputWriter repoTemplateOutputWriter() {
        return new RepoTemplateOutputWriter();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "mybatis.codegen", value = "enabled", havingValue = "true")
    ServiceImplTemplateOutputWriter serviceImplTemplateOutputWriter() {
        return new ServiceImplTemplateOutputWriter();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "mybatis.codegen", value = "enabled", havingValue = "true")
    ServiceTemplateOutputWriter serviceTemplateOutputWriter() {
        return new ServiceTemplateOutputWriter();
    }
    // endregion

    private void checkPropertiesIntegrity() {
        if (mybatisCodeGeneratorProperties.getParent_pck() == null) {
            throw new PropertyMissingException("property parentPackage required");
        }

        if (mybatisCodeGeneratorProperties.getDb_url() == null) {
            throw new PropertyMissingException("property dbUrl required");
        }

        if (mybatisCodeGeneratorProperties.getDb_username() == null) {
            throw new PropertyMissingException("property dbUsername required");
        }

        if (mybatisCodeGeneratorProperties.getDb_password() == null) {
            throw new PropertyMissingException("property dbPassword required");
        }
    }

}