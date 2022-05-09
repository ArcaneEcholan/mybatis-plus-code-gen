package fit.wenchao.mybatisCodeGen.codegen.templateOuputWriter;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import fit.wenchao.mybatisCodeGen.codegen.CodeGenContext;
import fit.wenchao.mybatisCodeGen.codegen.OutputFileEx;
import fit.wenchao.utils.string.DirStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;

public abstract class TemplateOutputWriter {

    @Autowired
    CodeGenContext codeGenContext;

    AbstractTemplateEngine templateEngine;

    public TemplateOutputWriter() {
    }

    public TemplateOutputWriter setTemplateEngine(AbstractTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        return this;
    }

    public TemplateOutputWriter(AbstractTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public void output(String templateFilePath, TableInfo tableInfo, Map<String, Object> objectMap) {
        putAdditionalArgs(objectMap, tableInfo);
        String fileName = getFileName(tableInfo);
        DirStr packageName = new DirStr(getPackageName(objectMap));
        putFilePackageName(objectMap, packageName.toPck());
        String OutputFilePath = String.format((getOutputDir(packageName.toPath()) + File.separator + "%s"), fileName);
        templateEngine.outputFile(new File(OutputFilePath), objectMap, templateFilePath, templateEngine.getConfigBuilder().getInjectionConfig().isFileOverride());
    }

    protected void putAdditionalArgs(Map<String, Object> objectMap, TableInfo tableInfo) {

    }

    protected String getOutputDir(String packagePath) {
        return codeGenContext.getCODE_LOC() + packagePath;
    }

    protected String getParentPackage(Map<String, Object> objectMap) {
        Map<String, String> pck = (Map<String, String>) objectMap.get("package");
        return pck.get("Parent");
    }

    protected abstract String getFileName(TableInfo tableInfo);

    protected abstract String getPackageName(Map<String, Object> objectMap);

    protected abstract void putFilePackageName(Map<String, Object> objectMap, String packageName);

    protected abstract boolean supportsOutputFile(OutputFileEx outputFile);
}
