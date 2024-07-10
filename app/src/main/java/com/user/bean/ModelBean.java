package com.user.bean;

import java.io.Serializable;
import java.util.List;

public class ModelBean implements Serializable {
    private String ChineseName;
    private String EnglishName;
    private Boolean gender;
    private String nation;
    private String nationality;
    private String birthplace;
    private String data;
    private String school;
    private String constellation;
    private String height;
    private String weight;
    private String occupation;
    private String achievements;
    private List<Integer> list;

    public ModelBean(String chineseName, String englishName, Boolean gender, String nation, String nationality, String birthplace, String data, String school, String constellation, String height, String weight, String occupation, String achievements, List<Integer> list) {
        ChineseName = chineseName;
        EnglishName = englishName;
        this.gender = gender;
        this.nation = nation;
        this.nationality = nationality;
        this.birthplace = birthplace;
        this.data = data;
        this.school = school;
        this.constellation = constellation;
        this.height = height;
        this.weight = weight;
        this.occupation = occupation;
        this.achievements = achievements;
        this.list = list;
    }

    public String getChineseName() {
        return ChineseName;
    }

    public void setChineseName(String chineseName) {
        ChineseName = chineseName;
    }

    public String getEnglishName() {
        return EnglishName;
    }

    public void setEnglishName(String englishName) {
        EnglishName = englishName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}
