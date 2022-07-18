package fit.wenchao.mybatisCodeGen.codegen;

import fit.wenchao.utils.string.DirStr;
import lombok.Getter;

@Getter
public class CodeGenContext {
    static final String SRC = "/src/main/java";
    static final String RESOURCES = "/src/main/resources";

    /**
     * 项目根目录，是项目的绝对路径
     */
    final String PROJECT_ROOT_PATH = System.getProperty("user.dir");

    /**
     * 生成代码路径，以File.separator开头
     */
    String CODE_LOC;

    /**
     * 生成资源路径
     */
    String RES_LOC;

    /**
     * 生成的所有代码都将放在该包（目录）下
     */
    DirStr PARENT_DIR;

    private final MybatisCodeGeneratorProperties mybatisCodeGeneratorProperties;

    public CodeGenContext(MybatisCodeGeneratorProperties mybatisCodeGeneratorProperties) {
        this.mybatisCodeGeneratorProperties = mybatisCodeGeneratorProperties;

        PARENT_DIR = new DirStr(mybatisCodeGeneratorProperties.getParent_pck());

        CODE_LOC = PROJECT_ROOT_PATH + mybatisCodeGeneratorProperties.getModule_loc() + SRC;

        RES_LOC = PROJECT_ROOT_PATH + mybatisCodeGeneratorProperties.getModule_loc() + RESOURCES;
    }

    public String getPARENT_PCK() {
        return mybatisCodeGeneratorProperties.getParent_pck();
    }

    public String getAUTHOR() {
        return mybatisCodeGeneratorProperties.getAuthor();
    }

    public String getDB_URL() {
        return mybatisCodeGeneratorProperties.getDb_url();
    }

    public Boolean genControllers() {
        return mybatisCodeGeneratorProperties.getController_on();
    }

    public String getMODULE_LOC() {
        return mybatisCodeGeneratorProperties.getModule_loc();
    }

    public String getDB_USERNAME() {
        return mybatisCodeGeneratorProperties.getDb_username();
    }

    public String getDB_PASSWORD() {
        return mybatisCodeGeneratorProperties.getDb_password();
    }

    public String getTEMPLATE_PATH() {
        return mybatisCodeGeneratorProperties.getTemplate_path();
    }
}
