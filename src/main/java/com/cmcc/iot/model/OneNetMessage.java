package com.cmcc.iot.model;

import java.io.Serializable;

public class OneNetMessage implements Serializable{
    Long at;
    Integer type;
    String ds_id;
    String value;
    Integer dev_id;
    Integer status;
    Integer login_type;
    Integer cmd_type;
    String cmd_id;
    String imei;
    Long send_time;
    Integer send_status;
    Long confirm_time;
    Integer confirm_status;
    String confirm_body;

    public Long getAt() {
        return at;
    }

    public void setAt(Long at) {
        this.at = at;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDs_id() {
        return ds_id;
    }

    public void setDs_id(String ds_id) {
        this.ds_id = ds_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getDev_id() {
        return dev_id;
    }

    public void setDev_id(Integer dev_id) {
        this.dev_id = dev_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLogin_type() {
        return login_type;
    }

    public void setLogin_type(Integer login_type) {
        this.login_type = login_type;
    }

    public Integer getCmd_type() {
        return cmd_type;
    }

    public void setCmd_type(Integer cmd_type) {
        this.cmd_type = cmd_type;
    }

    public String getCmd_id() {
        return cmd_id;
    }

    public void setCmd_id(String cmd_id) {
        this.cmd_id = cmd_id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Long getSend_time() {
        return send_time;
    }

    public void setSend_time(Long send_time) {
        this.send_time = send_time;
    }

    public Integer getSend_status() {
        return send_status;
    }

    public void setSend_status(Integer send_status) {
        this.send_status = send_status;
    }

    public Long getConfirm_time() {
        return confirm_time;
    }

    public void setConfirm_time(Long confirm_time) {
        this.confirm_time = confirm_time;
    }

    public Integer getConfirm_status() {
        return confirm_status;
    }

    public void setConfirm_status(Integer confirm_status) {
        this.confirm_status = confirm_status;
    }

    public String getConfirm_body() {
        return confirm_body;
    }

    public void setConfirm_body(String confirm_body) {
        this.confirm_body = confirm_body;
    }


}
