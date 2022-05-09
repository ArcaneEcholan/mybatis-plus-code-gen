package fit.wenchao.mybatisCodeGen.codegen.templateOuputWriter;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import fit.wenchao.mybatisCodeGen.codegen.OutputFileEx;
import org.springframework.stereotype.Component;

import java.util.Map;


public class RepoImplTemplateOutputWriter extends TemplateOutputWriter {

    public RepoImplTemplateOutputWriter() {
    }

    public RepoImplTemplateOutputWriter(AbstractTemplateEngine templateEngine) {
        super(templateEngine);
    }


    @Override
    protected String getFileName(TableInfo tableInfo) {
        String entityName = tableInfo.getEntityName();
        return entityName + "DaoImpl.java";
    }

    @Override
    protected String getPackageName(Map<String, Object> objectMap) {
        String parentPackage = getParentPackage(objectMap);
        return parentPackage + ".dao.repo.impl";
    }

    @Override
    protected void putFilePackageName(Map<String, Object> objectMap, String packageName) {
        objectMap.put("repoImplPackageName", packageName);
    }

    @Override
    protected boolean supportsOutputFile(OutputFileEx outputFile) {
        return outputFile.equals(OutputFileEx.repoImpl);
    }
}
