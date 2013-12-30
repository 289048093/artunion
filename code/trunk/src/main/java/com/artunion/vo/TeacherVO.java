/**
 * 
 */
package com.artunion.vo;


/**
 * 老师
 * 
 * @author LiZhao
 * 
 */
public class TeacherVO extends UserVO {

    /**
     * 
     */
    private static final long serialVersionUID = -901212537209979896L;

    /**
     * 学校
     */
    private String school;

    /**
     * 科目
     */
    private String major;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}
