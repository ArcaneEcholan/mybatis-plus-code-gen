package fit.wenchao.mybatisCodeGen.codegen.templateOuputWriter;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import fit.wenchao.mybatisCodeGen.codegen.OutputFileEx;

import java.util.Map;


public class ServiceImplTemplateOutputWriter extends TemplateOutputWriter {
    public ServiceImplTemplateOutputWriter() {
    }

    @Override
    protected void putAdditionalArgs(Map<String, Object> objectMap, TableInfo tableInfo) {
        super.putAdditionalArgs(objectMap, tableInfo);
        String entityName = tableInfo.getEntityName();
        String daoVarName = entityName.substring(0, 1).toLowerCase() + entityName.substring(1) + "Dao";
        objectMap.put("daoVarName", daoVarName);
    }

    @Override
    protected String getFileName(TableInfo tableInfo) {
        String entityName = tableInfo.getEntityName();
        return entityName + "ServiceImpl.java";
    }

    @Override
    protected String getPackageName(Map<String, Object> objectMap) {
        String parentPackage = getParentPackage(objectMap);
        return parentPackage + ".service.impl";
    }

    @Override
    protected void putFilePackageName(Map<String, Object> objectMap, String packageName) {
        objectMap.put("serviceImplPackageName", packageName);
    }

    @Override
    protected boolean supportsOutputFile(OutputFileEx outputFile) {
        return outputFile.equals(OutputFileEx.serviceImpl);

    }
}
