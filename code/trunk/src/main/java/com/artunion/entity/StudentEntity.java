/**
 * 
 */
package com.artunion.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 学生
 * 
 * @author Lizhao
 * 
 */
@Entity
@Table(name = "tb_student")
public class StudentEntity extends UserEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 7865978241819203832L;

    /**
     * 学校
     */
    @Column(name = "SCHOOL_")
    private String shool;

    /**
     * 年级
     */
    @Column(name = "GRADE_")
    private String grade;

    public String getShool() {
        return shool;
    }

    public void setShool(String shool) {
        this.shool = shool;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
