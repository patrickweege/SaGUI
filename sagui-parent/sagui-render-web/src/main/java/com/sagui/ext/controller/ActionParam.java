package com.sagui.ext.controller;

public enum ActionParam {

    PAGE_ID("PAGE_ID"), // 
    COMPONENT_ID("COMPONENT_ID"), // 
    EVENT("EVENT"), //
    NEW_VALUE("NEW_VALUE");

    private final String key;

    ActionParam(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
