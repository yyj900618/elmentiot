package com.cmcc.iot.model;

public class Location {
    private Double id;

    private String locationname;

    private String locationcode;

    private String lastlevelcode;

    private Double parentid;

    private Double locationlevel;

    private String orderindex;

    private String description;

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public String getLocationname() {
        return locationname;
    }

    public void setLocationname(String locationname) {
        this.locationname = locationname == null ? null : locationname.trim();
    }

    public String getLocationcode() {
        return locationcode;
    }

    public void setLocationcode(String locationcode) {
        this.locationcode = locationcode == null ? null : locationcode.trim();
    }

    public String getLastlevelcode() {
        return lastlevelcode;
    }

    public void setLastlevelcode(String lastlevelcode) {
        this.lastlevelcode = lastlevelcode == null ? null : lastlevelcode.trim();
    }

    public Double getParentid() {
        return parentid;
    }

    public void setParentid(Double parentid) {
        this.parentid = parentid;
    }

    public Double getLocationlevel() {
        return locationlevel;
    }

    public void setLocationlevel(Double locationlevel) {
        this.locationlevel = locationlevel;
    }

    public String getOrderindex() {
        return orderindex;
    }

    public void setOrderindex(String orderindex) {
        this.orderindex = orderindex == null ? null : orderindex.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}