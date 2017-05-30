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
 *
 * @author ebrimatunkara
 */
public class QuestionaireDto extends JobComponentDto {

    private Set<JobQuestionDto> questions = new HashSet();
    private int rank = 0;
    
    public QuestionaireDto() {
    }

    public QuestionaireDto(Long id, String name) {
        super(id, name);
    }

    public Set<JobQuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<JobQuestionDto> questions) {
        this.questions = questions;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
