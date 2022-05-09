package fit.wenchao.mybatisCodeGen.codegen;

public enum OutputFileEx {
    entity,
    service,
    serviceImpl,
    mapper,
    xml,
    controller,
    other,
    repo,
    repoImpl;

    public String toString() {
        return name();
    }
}