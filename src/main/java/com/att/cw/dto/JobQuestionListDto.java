/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto;

import com.att.cw.dto.mappers.JobQuestionDtoMapper;
import com.att.cw.model.Job;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author ebrimatunkara
 */
public class JobQuestionListDto {

    private Long id;
    private String title;
    private List<JobQuestionDto> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<JobQuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<JobQuestionDto> questions) {
        this.questions = questions;
    }

    
    public static JobQuestionListDto build(Job job){
        JobQuestionListDto dto = new JobQuestionListDto();
                  List<JobQuestionDto> questions =JobQuestionDtoMapper.mapEntitiesIntoDTOs(job.getQuestions()
                    .stream()
                    .collect(toList()));
        dto.setId(job.getId());
        dto.setQuestions(questions);
        dto.setTitle(job.getTitle());
        return dto;
    }
}
