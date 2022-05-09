package fit.wenchao.mybatisCodeGen.codegen.exception;

public class PropertyMissingException extends Error {
    public PropertyMissingException(String missedPropertyName) {
        super("property:" + missedPropertyName + " missing");
    }
}
