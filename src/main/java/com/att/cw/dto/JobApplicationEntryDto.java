/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto;

import java.util.Date;
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
        private AnswerEntryDto entry = new AnswerEntryDto();

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
        private Set<AnswerEntryOption> options = new HashSet();
        private FileDto file;

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

        public Set<AnswerEntryOption> getOptions() {
            return options;
        }

        public void setOptions(Set<AnswerEntryOption> options) {
            this.options = options;
        }

        public FileDto getFile() {
            return file;
        }

        public void setFile(FileDto file) {
            this.file = file;
        }

        @Override
        public String toString() {
            return "AnswerEntryDto{" + "id=" + id + ", value=" + value + '}';
        }
    }

    public static class AnswerEntryOption {

        private Long id;
        private String value;

        public AnswerEntryOption() {
        }

        public AnswerEntryOption(Long id, String value) {
            this.id = id;
            this.value = value;
        }

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
    }

    public static class FileDto {

        private String name;
        private Long id;
        private String type;
        private Long size;
        private Date lastModifiedDate;

        public FileDto() {
        }

        public FileDto(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        public Date getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(Date lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }
    }

    @Override
    public String toString() {
        return "JobApplicationEntryDto{" + super.toString() + ",questionAnswers=" + questionAnswers + '}';
    }

    /**
     * FileDto builder
     */
    public static class FileDtoBuilder {
        private final FileDto fileDto;

        public FileDtoBuilder() {
            fileDto = new FileDto();
        }

        public FileDtoBuilder setId(Long id) {
            fileDto.setId(id);
            return this;
        }

        public FileDtoBuilder setName(String name) {
            fileDto.setName(name);
            return this;
        }

        public FileDtoBuilder setSize(Long size) {
            fileDto.setSize(size);
            return this;
        }

        public FileDtoBuilder setType(String type) {
            fileDto.setType(type);
            return this;
        }

        public FileDtoBuilder setLastModifiedDate(Date date) {
            fileDto.setLastModifiedDate(date);
            return this;
        }

        public FileDto build() {
            return this.fileDto;
        }
    }
}
