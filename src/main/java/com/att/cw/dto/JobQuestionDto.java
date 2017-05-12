/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * JobQuestionDto
 *
 * @author ebrimatunkara
 */
public class JobQuestionDto {

    private Long id;
    private String question;
    private Map<String, Object> entries = new HashMap();
    private Boolean required = false;
    private QuestionTypeDto questionType;
    private String referenceNumber;
    private QuestionCategoryDto category;
    private Set<QuestionOptionDto> options = new HashSet();

    public JobQuestionDto() {
    }

    public JobQuestionDto(Long id) {
        this.id = id;
    }

    public JobQuestionDto(Long id,
            String question,
            Boolean required,
            QuestionTypeDto type) {
        this.id = id;
        this.question = question;
        this.required = required;
        this.questionType = type;
    }

    public JobQuestionDto(Long id, String question,
            Boolean required,
            QuestionTypeDto type,
            Set<QuestionOptionDto> options) {
        this(id, question, required, type);
        this.options = options;
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

    public JobComponentDto getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionTypeDto type) {
        this.questionType = type;
    }

    public Boolean isRequired() {
        return required;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Set<QuestionOptionDto> getOptions() {
        return options;
    }

    public void setOptions(Set<QuestionOptionDto> options) {
        this.options = options;
    }

    public QuestionCategoryDto getCategory() {
        return category;
    }

    public void setCategory(QuestionCategoryDto category) {
        this.category = category;
    }

    public Map<String, Object> getEntries() {
        return entries;
    }

    public void setEntries(Map<String, Object> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "JobQuestionDto{" + "id=" + id + ", question=" + question + ", required=" + required + ", questionType=" + questionType + ", referenceNumber=" + referenceNumber + ", category=" + category + ", options=" + options + '}';
    }

    static public class JobQuestionDtoBuilder {

        private final JobQuestionDto dto;

        public JobQuestionDtoBuilder() {
            dto = new JobQuestionDto();
        }

        public JobQuestionDtoBuilder setId(Long id) {
            this.dto.setId(id);
            return this;
        }

        public JobQuestionDtoBuilder setQuestion(String question) {
            this.dto.setQuestion(question);
            return this;
        }

        public JobQuestionDtoBuilder setRequired(boolean required) {
            this.dto.setRequired(required);
            return this;
        }

        public JobQuestionDtoBuilder setCategory(QuestionCategoryDto category) {
            this.dto.setCategory(category);
            return this;
        }

        public JobQuestionDtoBuilder setOptions(Set<QuestionOptionDto> options) {
            this.dto.setOptions(options);
            return this;
        }

        public JobQuestionDtoBuilder setEntries(Map<String, Object> entries) {
            this.dto.setEntries(entries);
            return this;
        }

        public JobQuestionDto build() {
            return dto;
        }

    }
}
