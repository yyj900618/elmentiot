package com.cmcc.iot.model;

public class Auth {
    private Integer id;

    private String shriostr;

    private String description;

    private String name;

    private Integer isvalid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShriostr() {
        return shriostr;
    }

    public void setShriostr(String shriostr) {
        this.shriostr = shriostr == null ? null : shriostr.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }
}