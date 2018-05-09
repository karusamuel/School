package com.nahashon.second.adapter;



public class LevelClass {
    private String Name;
    private String Description;
    private String Img_link;

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setImg_link(String img_link) {
        Img_link = img_link;
    }

    public LevelClass(){

    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getImg_link() {
        return Img_link;
    }
}
