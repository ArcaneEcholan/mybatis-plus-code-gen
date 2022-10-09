package fit.wenchao.mybatisCodeGen.codegen;


import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import fit.wenchao.mybatisCodeGen.codegen.templateOuputWriter.TemplateOutputWriter;
import fit.wenchao.mybatisCodeGen.codegen.templateOuputWriter.TemplateOutputWriterFactory;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.Map;

public class CustomVelocityTemplateEngine extends VelocityTemplateEngine {
    TemplateOutputWriterFactory templateOutputWriterFactory;

    public CustomVelocityTemplateEngine(TemplateOutputWriterFactory templateOutputWriterFactory) {
        this.templateOutputWriterFactory = templateOutputWriterFactory;
    }

    public void publicOutputfile(@NotNull File file, @NotNull Map<String, Object> objectMap, @NotNull String templatePath, boolean fileOverride) {
        outputFile(file, objectMap, templatePath, fileOverride);
    }

    protected void outputCustomFile(Map<String, String> customFile, TableInfo tableInfo, Map<String, Object> objectMap) {
        String entity = (String)objectMap.get("entity");
        entity = entity + "PO";
        objectMap.put("entity", entity);
        customFile.forEach((outputFile, templateFilePath) -> {
            TemplateOutputWriter outputWriter = this.templateOutputWriterFactory.getOutputWriter(OutputFileEx.valueOf(outputFile));
            outputWriter.setTemplateEngine(this).output(templateFilePath, tableInfo, objectMap);
        });
    }

    @Override
    protected void outputEntity(TableInfo tableInfo, Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String entityPath = getPathInfo(OutputFile.entity);
        if (StringUtils.isNotBlank(entityName) && StringUtils.isNotBlank(entityPath)) {
            getTemplateFilePath(template -> template.getEntity(getConfigBuilder().getGlobalConfig().isKotlin())).ifPresent((entity) -> {
                String entityFile = String.format((entityPath + File.separator + "%s" + suffixJavaOrKt()), entityName + "PO");
                outputFile(new File(entityFile), objectMap, entity, getConfigBuilder().getStrategyConfig().entity().isFileOverride());
            });
        }
    }
}