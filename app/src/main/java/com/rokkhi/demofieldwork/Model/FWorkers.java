package com.rokkhi.demofieldwork.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FWorkers {

    private String fw_name;
    private String user_id;
    private String fw_nid;
    private String fw_phone;
    private String fw_university;
    private String fw_address;
    private String fw_birthday;
    private Date fw_joindate;
    private Date created_at;
    private Date updated_at;
    private String fw_mail;
    private String fw_pic;
    private String thumb_fw_pic;
    private List<String> u_array= new ArrayList<>();

    public FWorkers() {
    }

    public FWorkers(String fw_name, String user_id, String fw_nid, String fw_phone, String fw_university, String fw_address, String fw_birthday, Date fw_joindate, Date created_at, Date updated_at, String fw_mail, String fw_pic, String thumb_fw_pic, List<String> u_array) {
        this.fw_name = fw_name;
        this.user_id = user_id;
        this.fw_nid = fw_nid;
        this.fw_phone = fw_phone;
        this.fw_university = fw_university;
        this.fw_address = fw_address;
        this.fw_birthday = fw_birthday;
        this.fw_joindate = fw_joindate;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.fw_mail = fw_mail;
        this.fw_pic = fw_pic;
        this.thumb_fw_pic = thumb_fw_pic;
        this.u_array = u_array;
    }

    public String getFw_name() {
        return fw_name;
    }

    public void setFw_name(String fw_name) {
        this.fw_name = fw_name;
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

    public Date getFw_joindate() {
        return fw_joindate;
    }

    public void setFw_joindate(Date fw_joindate) {
        this.fw_joindate = fw_joindate;
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

    public String getFw_mail() {
        return fw_mail;
    }

    public void setFw_mail(String fw_mail) {
        this.fw_mail = fw_mail;
    }

    public String getFw_pic() {
        return fw_pic;
    }

    public void setFw_pic(String fw_pic) {
        this.fw_pic = fw_pic;
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



