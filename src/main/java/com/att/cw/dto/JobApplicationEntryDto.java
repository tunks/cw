/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ebrimatunkara
 */
public class JobApplicationEntryDto extends JobApplicationEntry {

    private Set<QuestionAnswerDto> questionAnswers;

    public JobApplicationEntryDto() {
        this.questionAnswers = new HashSet();
    }

    public Set<QuestionAnswerDto> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(Set<QuestionAnswerDto> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    public static class QuestionAnswerDto {

        private JobQuestionDto question;
        private AnswerDto answer;

        public JobQuestionDto getQuestion() {
            return question;
        }

        public void setQuestion(JobQuestionDto question) {
            this.question = question;
        }

        public AnswerDto getAnswer() {
            return answer;
        }

        public void setAnswer(AnswerDto answer) {
            this.answer = answer;
        }

        @Override
        public String toString() {
            return "{" + "question=" + question + ", answer=" + answer + '}';
        }

    }

    public static class AnswerDto {

        private Long id;
        private AnswerEntryDto entry;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public AnswerEntryDto getEntry() {
            return entry;
        }

        public void setEntry(AnswerEntryDto entry) {
            this.entry = entry;
        }

        @Override
        public String toString() {
            return "{" + "id=" + id + ", entry=" + entry + '}';
        }
    }

    public static class AnswerEntryDto {

        private Long id;
        private String value;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "AnswerEntryDto{" + "id=" + id + ", value=" + value + '}';
        }

    }

    @Override
    public String toString() {
        return "JobApplicationEntryDto{"+ super.toString() + ",questionAnswers=" + questionAnswers + '}';
    }

}
