/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Apr 27, 2013  5:09:09 PM
 */
package com.artunion.vo;

import com.artunion.BaseVO;

/**
 * @author CloudKing
 */
@SuppressWarnings("serial")
public class RegQuestionVO extends BaseVO {
    /**
     * 提示问题
     */
    private String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
