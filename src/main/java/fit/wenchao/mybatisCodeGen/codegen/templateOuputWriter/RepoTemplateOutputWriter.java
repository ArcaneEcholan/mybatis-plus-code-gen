package fit.wenchao.mybatisCodeGen.codegen.templateOuputWriter;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import fit.wenchao.mybatisCodeGen.codegen.OutputFileEx;
import org.springframework.stereotype.Component;

import java.util.Map;


public class RepoTemplateOutputWriter extends TemplateOutputWriter {

    public RepoTemplateOutputWriter() {
    }

    public RepoTemplateOutputWriter(AbstractTemplateEngine templateEngine) {
        super(templateEngine);
    }

    @Override
    protected String getFileName(TableInfo tableInfo) {
        String entityName = tableInfo.getEntityName();
        return entityName + "Dao.java";
    }

    @Override
    protected String getPackageName(Map<String, Object> objectMap) {
        String parentPackage = getParentPackage(objectMap);
        return parentPackage + ".dao.repo";
    }

    @Override
    protected void putFilePackageName(Map<String, Object> objectMap, String packageName) {
        objectMap.put("repoPackageName", packageName);
    }

    @Override
    protected boolean supportsOutputFile(OutputFileEx outputFile) {
        return outputFile.equals(OutputFileEx.repo);
    }
}
