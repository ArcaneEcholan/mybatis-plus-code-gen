package fit.wenchao.mybatisCodeGen.codegen;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import fit.wenchao.mybatisCodeGen.codegen.CodeGenContext;
import fit.wenchao.mybatisCodeGen.codegen.CustomVelocityTemplateEngine;
import fit.wenchao.mybatisCodeGen.codegen.OutputFileEx;
import fit.wenchao.mybatisCodeGen.codegen.templateOuputWriter.TemplateOutputWriter;
import fit.wenchao.mybatisCodeGen.codegen.templateOuputWriter.TemplateOutputWriterFactory;
import fit.wenchao.utils.collection.MapService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Map;

public class MybatisCodeGenerator {

    static MybatisCodeGenerator mybatisCodeGenerator;

    public static void setStaticMybatisCodeGenerator(  MybatisCodeGenerator mybatisCodeGenerator) {
        MybatisCodeGenerator.mybatisCodeGenerator = mybatisCodeGenerator;
    }

    public static void generateStructureCode() {
        mybatisCodeGenerator.doGenerate();
    }

    CodeGenContext codeGenContext;

    CustomVelocityTemplateEngine customVelocityTemplateEngine;

    public MybatisCodeGenerator(CodeGenContext codeGenContext,
                                CustomVelocityTemplateEngine customVelocityTemplateEngine) {
        this.codeGenContext = codeGenContext;
        this.customVelocityTemplateEngine = customVelocityTemplateEngine;
    }

    public void doGenerate() {

        Map<OutputFile, String> filePathMap = MapService.of(
                OutputFile.xml, codeGenContext.getRES_LOC() + "/mapper",
                OutputFile.entity, codeGenContext.getCODE_LOC() + codeGenContext.getPARENT_DIR().toPath() + "/dao/po",
                OutputFile.mapper, codeGenContext.getCODE_LOC() + codeGenContext.getPARENT_DIR().toPath() + "/dao/mapper",
                OutputFile.service, codeGenContext.getCODE_LOC() + codeGenContext.getPARENT_DIR().toPath() + "/dao/repo",
                OutputFile.serviceImpl, codeGenContext.getCODE_LOC() + codeGenContext.getPARENT_DIR().toPath() + "/dao/repo/impl"
        );

        System.out.println(filePathMap);

        Map<String, String> customOutputFileMap = MapService.of(
                OutputFileEx.repo.toString(), codeGenContext.getTEMPLATE_PATH() + "/repo.java.vm",
                OutputFileEx.repoImpl.toString(), codeGenContext.getTEMPLATE_PATH() + "/repoImpl.java.vm",
                OutputFile.service.toString(), codeGenContext.getTEMPLATE_PATH() + "/myservice.java.vm",
                OutputFile.serviceImpl.toString(), codeGenContext.getTEMPLATE_PATH() + "/myserviceImpl.java.vm"
        );

        System.out.println(customOutputFileMap);

        FastAutoGenerator
                .create(codeGenContext.getDB_URL(),
                        codeGenContext.getDB_USERNAME(),
                        codeGenContext.getDB_PASSWORD())
                .globalConfig(builder -> {
                    builder.author(codeGenContext.getAUTHOR()) // 设置作者
                            .outputDir(codeGenContext.getCODE_LOC())
                            .disableOpenDir(); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(codeGenContext.getPARENT_DIR().toPck())
                            .mapper("dao.mapper")
                            .entity("dao.po")
                            // 设置父包名
                            .pathInfo(filePathMap)// 设置父包模块名
                    ;
                })
                .strategyConfig(builder -> {
                    //默认主键自增
                    builder.entityBuilder().idType(IdType.AUTO);
                    builder.mapperBuilder().enableMapperAnnotation();
                })
                .templateConfig(builder -> builder
                        .disable(TemplateType.SERVICE, TemplateType.SERVICEIMPL)
                        .controller(codeGenContext.getTEMPLATE_PATH() + "/controller.java.vm")
                        .entity(codeGenContext.getTEMPLATE_PATH() + "/entity.java.vm")
                        .mapper(codeGenContext.getTEMPLATE_PATH() + "/mapper.java.vm")
                        .xml(codeGenContext.getTEMPLATE_PATH() + "/mapper.xml.vm")
                )
                .injectionConfig(builder -> builder.customFile(customOutputFileMap))
                .templateEngine(customVelocityTemplateEngine)
                .execute();
    }
}


