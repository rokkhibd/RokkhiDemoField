package com.rokkhi.demofieldwork.Model;

import java.util.Date;

public class FPayments {

    private String user_id;
    private String ref_id;
    private String fw_phone;
    private String total_earning;
    private String due_earning;
    private String total_buildings;
    private String active_buildings;
    private String due_buildings;
    private String bkash_no;
    private String nogod_no;
    private Date created_at;
    private Date updated_at;
    private Date working_from;
    private String total_meeting;
    private String due_meeting;
    private String total_referral;
    private String due_referral;


    public FPayments() {
    }

    public FPayments(String user_id, String ref_id, String fw_phone, String total_earning, String due_earning, String total_buildings, String active_buildings, String due_buildings, String bkash_no, String nogod_no, Date created_at, Date updated_at, Date working_from, String total_meeting, String due_meeting, String total_referral, String due_referral) {
        this.user_id = user_id;
        this.ref_id = ref_id;
        this.fw_phone = fw_phone;
        this.total_earning = total_earning;
        this.due_earning = due_earning;
        this.total_buildings = total_buildings;
        this.active_buildings = active_buildings;
        this.due_buildings = due_buildings;
        this.bkash_no = bkash_no;
        this.nogod_no = nogod_no;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.working_from = working_from;
        this.total_meeting = total_meeting;
        this.due_meeting = due_meeting;
        this.total_referral = total_referral;
        this.due_referral = due_referral;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRef_id() {
        return ref_id;
    }

    public void setRef_id(String ref_id) {
        this.ref_id = ref_id;
    }

    public String getFw_phone() {
        return fw_phone;
    }

    public void setFw_phone(String fw_phone) {
        this.fw_phone = fw_phone;
    }

    public String getTotal_earning() {
        return total_earning;
    }

    public void setTotal_earning(String total_earning) {
        this.total_earning = total_earning;
    }

    public String getDue_earning() {
        return due_earning;
    }

    public void setDue_earning(String due_earning) {
        this.due_earning = due_earning;
    }

    public String getTotal_buildings() {
        return total_buildings;
    }

    public void setTotal_buildings(String total_buildings) {
        this.total_buildings = total_buildings;
    }

    public String getActive_buildings() {
        return active_buildings;
    }

    public void setActive_buildings(String active_buildings) {
        this.active_buildings = active_buildings;
    }

    public String getDue_buildings() {
        return due_buildings;
    }

    public void setDue_buildings(String due_buildings) {
        this.due_buildings = due_buildings;
    }

    public String getBkash_no() {
        return bkash_no;
    }

    public void setBkash_no(String bkash_no) {
        this.bkash_no = bkash_no;
    }

    public String getNogod_no() {
        return nogod_no;
    }

    public void setNogod_no(String nogod_no) {
        this.nogod_no = nogod_no;
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

    public Date getWorking_from() {
        return working_from;
    }

    public void setWorking_from(Date working_from) {
        this.working_from = working_from;
    }

    public String getTotal_meeting() {
        return total_meeting;
    }

    public void setTotal_meeting(String total_meeting) {
        this.total_meeting = total_meeting;
    }

    public String getDue_meeting() {
        return due_meeting;
    }

    public void setDue_meeting(String due_meeting) {
        this.due_meeting = due_meeting;
    }

    public String getTotal_referral() {
        return total_referral;
    }

    public void setTotal_referral(String total_referral) {
        this.total_referral = total_referral;
    }

    public String getDue_referral() {
        return due_referral;
    }

    public void setDue_referral(String due_referral) {
        this.due_referral = due_referral;
    }
}



