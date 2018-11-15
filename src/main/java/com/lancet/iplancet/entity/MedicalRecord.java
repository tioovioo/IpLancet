package com.lancet.iplancet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author Jimmy
 * @date 2018/11/13
 * @Description:
 */
public class MedicalRecord {


    private  Integer id;
    private String emrId;
    private String pid;
    private String admissionNumber;
    private String treatType;
    private String admissionDepartment;
    private String dischargeDepartment;
    private String sex;
    private Integer age ;
    private String nation;
    private String maritalStatus;
    private String profession;
    private String mainDiagnosis;
    @JsonFormat(pattern = "yyyy年MM月dd日",timezone="GMT+8")
    private Date createTime;


    public MedicalRecord() {
    }

    public MedicalRecord(Integer id, String emrId, String pid, String admissionNumber, String treatType, String admissionDepartment, String dischargeDepartment, String sex, Integer age, String nation, String maritalStatus, String profession, String mainDiagnosis, Date createTime) {
        this.id = id;
        this.emrId = emrId;
        this.pid = pid;
        this.admissionNumber = admissionNumber;
        this.treatType = treatType;
        this.admissionDepartment = admissionDepartment;
        this.dischargeDepartment = dischargeDepartment;
        this.sex = sex;
        this.age = age;
        this.nation = nation;
        this.maritalStatus = maritalStatus;
        this.profession = profession;
        this.mainDiagnosis = mainDiagnosis;
        this.createTime = createTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmrId(String emrId) {
        this.emrId = emrId;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public void setTreatType(String treatType) {
        this.treatType = treatType;
    }

    public String getAdmissionDepartment() {
        return admissionDepartment;
    }

    public void setDischargeDepartment(String dischargeDepartment) {
        this.dischargeDepartment = dischargeDepartment;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setMainDiagnosis(String mainDiagnosis) {
        this.mainDiagnosis = mainDiagnosis;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public String getEmrId() {
        return emrId;
    }

    public String getPid() {
        return pid;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public String getTreatType() {
        return treatType;
    }

    public void setAdmissionDepartment(String admissionDepartment) {
        this.admissionDepartment = admissionDepartment;
    }

    public String getDischargeDepartment() {
        return dischargeDepartment;
    }

    public String getSex() {
        return sex;
    }

    public Integer getAge() {
        return age;
    }

    public String getNation() {
        return nation;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getProfession() {
        return profession;
    }

    public String getMainDiagnosis() {
        return mainDiagnosis;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
