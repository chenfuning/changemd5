package com.ning.tool.config;

public class MainConfig {
    // 原始路径
    private String orgPath;

    //输出路径
    private String outputPath;

    private String nulPath;

    public MainConfig() {
        this.nulPath = "F:\\empty.txt";
    }

    public String getOrgPath() {
        return orgPath;
    }

    public void setOrgPath(String orgPath) {
        this.orgPath = orgPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getNulPath() {
        return nulPath;
    }

    public void setNulPath(String nulPath) {
        this.nulPath = nulPath;
    }
}
