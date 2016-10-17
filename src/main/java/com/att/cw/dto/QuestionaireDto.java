/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * QuestionaireDTO
 * @author ebrimatunkara
 */
public class QuestionaireDto extends JobComponentDto{
    private Set<QuestionDto> questions = new HashSet();
    public QuestionaireDto() {
    }

    public QuestionaireDto(Long id, String name) {
        super(id, name);
    }

    public Set<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionDto> questions) {
        this.questions = questions;
    }
}
