/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto;

import com.att.cw.dto.mappers.JobQuestionDtoMapper;
import com.att.cw.model.Job;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author ebrimatunkara
 */
public class JobQuestionListDto {

    private Long id;
    private String title;
    private Map<String, Set<JobQuestionDto>> questions;

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

    public Map<String, Set<JobQuestionDto>> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<String, Set<JobQuestionDto>> questions) {
        this.questions = questions;
    }

    public static JobQuestionListDto build(Job job) {
        JobQuestionListDto dto = new JobQuestionListDto();
        List<JobQuestionDto> items = JobQuestionDtoMapper.mapEntitiesIntoDTOs(job.getQuestions().stream().collect(toList()));
        Map<String, Set<JobQuestionDto>> questions = new HashMap();
        items.stream().forEach((item) -> {
            QuestionCategoryDto cat = item.getCategory();// .getCategory()
            if (cat != null) {
                String key = cat.getName();
                if (!questions.containsKey(key)) {
                    questions.put(key, new HashSet());
                }
                questions.get(key).add(item);
            }
        });
        dto.setId(job.getId());
        dto.setQuestions(questions);
        dto.setTitle(job.getTitle());
        return dto;
    }
}
