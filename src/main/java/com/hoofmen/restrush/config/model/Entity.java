package com.hoofmen.restrush.config.model;

import java.util.List;
import java.util.Map;

public class Entity {
    private String packageName;
    private List<String> imports;
    private String className;
    private Map<String, Entity> attributesMap;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Map<String, Entity> getAttributesMap() {
        return attributesMap;
    }

    public void setAttributesMap(Map<String, Entity> attributesMap) {
        this.attributesMap = attributesMap;
    }
}
