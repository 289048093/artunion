/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Apr 27, 2013  5:09:09 PM
 */
package com.artunion.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.artunion.BaseEntity;

/**
 * @author CloudKing
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tb_reg_question")
public class RegQuestionEntity extends BaseEntity {
    /**
     * 提示问题
     */
    @Column(name = "QUESTION_", length = 30, nullable = false)
    private String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
