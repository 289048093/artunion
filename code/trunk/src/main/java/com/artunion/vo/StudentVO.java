/**
 * 
 */
package com.artunion.vo;

/**
 * 学生
 * 
 * @author LiZhao
 * 
 */
public class StudentVO extends UserVO {
    /**
     * 
     */
    private static final long serialVersionUID = -4121153069613736737L;
    /**
     * 学校
     */
    private String school;
    /**
     * 年级
     */
    private String grade;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
