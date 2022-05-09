package fit.wenchao.mybatisCodeGen.codegen.templateOuputWriter;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import fit.wenchao.mybatisCodeGen.codegen.OutputFileEx;
import org.springframework.stereotype.Component;

import java.util.Map;


public class ServiceTemplateOutputWriter extends TemplateOutputWriter {

    public ServiceTemplateOutputWriter() {
    }

    public ServiceTemplateOutputWriter(AbstractTemplateEngine templateEngine) {
        super(templateEngine);
    }

    protected String getFileName(TableInfo tableInfo) {
        String entityName = tableInfo.getEntityName();
        return entityName + "Service.java";
    }

    protected String getPackageName(Map<String, Object> objectMap) {
        String parentPackage = getParentPackage(objectMap);
        return parentPackage + ".service";
    }

    protected void putFilePackageName(Map<String, Object> objectMap, String packageName) {
        objectMap.put("servicePackageName", packageName);
    }

    @Override
    protected boolean supportsOutputFile(OutputFileEx outputFile) {
        return outputFile.equals(OutputFileEx.service);
    }
}
