package fit.wenchao.mybatisCodeGen.codegen.templateOuputWriter;

import fit.wenchao.mybatisCodeGen.codegen.OutputFileEx;
import fit.wenchao.mybatisCodeGen.springComponent.ApplicationContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static fit.wenchao.utils.basic.BasicUtils.forList;
import static fit.wenchao.utils.basic.BasicUtils.gloop;

public class TemplateOutputWriterFactory {
    private List<TemplateOutputWriter> outputWriters;

    public TemplateOutputWriterFactory(List<TemplateOutputWriter> outputWriters) {
        this.outputWriters = outputWriters;
    }

    public TemplateOutputWriter getOutputWriter(OutputFileEx outputFile) {
        final TemplateOutputWriter[] resultOutputWriter = {null};
        try {
            gloop(forList(outputWriters), (index, outputWriter, state) -> {
                if (outputWriter.supportsOutputFile(outputFile)) {
                    resultOutputWriter[0] = outputWriter;
                    state.breakLoop();
                    return;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultOutputWriter[0];
    }
}
