package com.imooc.model;

public class CheckItem {
    public CheckItem(String name,String text,boolean checked){
        this.name = name;
        this.text = text;
        this.checked = checked;
    }

    private String name;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    private String text;
    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

    private boolean checked;
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    public boolean isChecked() {
        return checked;
    }
}
