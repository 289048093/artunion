/**
 * 
 */
package com.artunion.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 老师
 * @author LiZhao
 * 
 */
@Entity
@Table(name = "tb_teacher")
public class TeacherEntity extends UserEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 6735093896265648031L;
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
