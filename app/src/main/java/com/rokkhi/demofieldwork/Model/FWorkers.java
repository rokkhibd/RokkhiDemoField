package com.rokkhi.demofieldwork.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FWorkers {

    private String user_id;
    private String fw_nid;
    private String fw_phone;
    private String fw_university;
    private String fw_address;
    private String fw_birthday;
    private Date created_at;
    private Date updated_at;
    private String thumb_fw_pic;
    private List<String> u_array= new ArrayList<>();


    public FWorkers() {
    }

    public FWorkers(String user_id, String fw_nid, String fw_phone, String fw_university, String fw_address, String fw_birthday, Date created_at, Date updated_at, String thumb_fw_pic, List<String> u_array) {
        this.user_id = user_id;
        this.fw_nid = fw_nid;
        this.fw_phone = fw_phone;
        this.fw_university = fw_university;
        this.fw_address = fw_address;
        this.fw_birthday = fw_birthday;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.thumb_fw_pic = thumb_fw_pic;
        this.u_array = u_array;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFw_nid() {
        return fw_nid;
    }

    public void setFw_nid(String fw_nid) {
        this.fw_nid = fw_nid;
    }

    public String getFw_phone() {
        return fw_phone;
    }

    public void setFw_phone(String fw_phone) {
        this.fw_phone = fw_phone;
    }

    public String getFw_university() {
        return fw_university;
    }

    public void setFw_university(String fw_university) {
        this.fw_university = fw_university;
    }

    public String getFw_address() {
        return fw_address;
    }

    public void setFw_address(String fw_address) {
        this.fw_address = fw_address;
    }

    public String getFw_birthday() {
        return fw_birthday;
    }

    public void setFw_birthday(String fw_birthday) {
        this.fw_birthday = fw_birthday;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getThumb_fw_pic() {
        return thumb_fw_pic;
    }

    public void setThumb_fw_pic(String thumb_fw_pic) {
        this.thumb_fw_pic = thumb_fw_pic;
    }

    public List<String> getU_array() {
        return u_array;
    }

    public void setU_array(List<String> u_array) {
        this.u_array = u_array;
    }
}



