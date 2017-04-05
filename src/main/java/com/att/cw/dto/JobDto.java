/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto;

import com.att.cw.model.JobLocation;
import com.att.cw.model.JobVacancy;
import java.util.HashSet;
import java.util.Set;

/**
 * JobDto
 *
 * @author ebrimatunkara
 */
public class JobDto {

    private Long id;
    /**
     * job title
     */
    private String title;
    /**
     * job description
     */
    private String description;

    /**
     * job skills
     */
    private String skills;

    /**
     * Job category
     */
    private Set<JobComponentDto> categories = new HashSet();

    private JobComponentDto jobType;
    /**
     * job work flow
     */
    private JobVacancy vacancy;

    private JobLocation location;

    private Set<JobQuestionDto> questions = new HashSet();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<JobComponentDto> getCategories() {
        return categories;
    }

    public void setCategories(Set<JobComponentDto> categories) {
        this.categories = categories;
    }

    public JobComponentDto getJobType() {
        return jobType;
    }

    public void setJobType(JobComponentDto jobType) {
        this.jobType = jobType;
    }

    public JobVacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(JobVacancy vacancy) {
        this.vacancy = vacancy;
    }

    public JobLocation getLocation() {
        return location;
    }

    public void setLocation(JobLocation location) {
        this.location = location;
    }

    public Set<JobQuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<JobQuestionDto> questions) {
        this.questions = questions;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

}
