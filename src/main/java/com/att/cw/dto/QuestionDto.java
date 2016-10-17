/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto;

import com.att.cw.model.QuestionOptionType;

/**
 * QuestionDto
 * @author ebrimatunkara
 */
public class QuestionDto {
    private Long id;
    private String question;
    private Boolean required = false;
    private QuestionOptionType type;

    public QuestionDto() {
    }

    public QuestionDto(Long id, String question, Boolean required, QuestionOptionType type) {
        this.id = id;
        this.question = question;
        this.required = required;
        this.type = type;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public QuestionOptionType getType() {
        return type;
    }

    public void setType(QuestionOptionType type) {
        this.type = type;
    }

    public Boolean isRequired() {
        return required;
    }
}
