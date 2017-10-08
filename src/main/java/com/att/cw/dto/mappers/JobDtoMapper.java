/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto.mappers;

import com.att.cw.dto.JobComponentDto;
import com.att.cw.dto.JobDto;
import com.att.cw.dto.JobQuestionDto;
import com.att.cw.model.Job;
import com.att.cw.model.JobType;
import com.att.cw.support.DataUtils;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * JobDto mapper
 *
 * @author ebrimatunkara
 */
public final class JobDtoMapper implements BaseEntityMapper<JobDto,Job>{

    public static List<JobDto> mapEntitiesIntoDTOs(List<Job> entities) {
        return entities.stream()
                .map(JobDtoMapper::mapEntityIntoDTO)
                .collect(toList());
    }

    public static JobDto mapEntityIntoDTO(Job entity) {
        JobType jobType = entity.getJobType();
        JobDto dto = new JobDto();
        dto.setId(entity.getId());
        dto.setCategories(entity.getCategories()
                .stream().map(c -> {
                    return new JobComponentDto(c.getId(), c.getName());
                }).collect(toSet()));
        dto.setJobType((jobType != null) ? new JobComponentDto(jobType.getId(), jobType.getName()) : null);
        dto.setLocation(entity.getLocation());
        dto.setTitle(entity.getTitle());
        dto.setVacancy(entity.getVacancy());
        dto.setDescription(DataUtils.bytesToString(entity.getDescription()));
        dto.setResponsibilities(DataUtils.bytesToString(entity.getResponsibilities()));
        dto.setSkills(DataUtils.bytesToString(entity.getSkills()));
        return dto;
    }

    public static JobDto mapFullEntityIntoDto(Job entity) {
        JobDto dto = mapEntityIntoDTO(entity);
        List<JobQuestionDto> questions = JobQuestionDtoMapper.mapEntitiesIntoDTOs(entity.getQuestions().stream().collect(toList()));
        dto.setQuestions(questions.stream().collect(toSet()));
        return dto;
    }

    public static Page<JobDto> mapEntityPageIntoDTOPage(Pageable page, Page<Job> source) {
        List<JobDto> dtos = mapEntitiesIntoDTOs(source.getContent());
        return new PageImpl<>(dtos, page, source.getTotalElements());
    }

    @Override
    public JobDto map(Job entity) {
         return JobDtoMapper.mapEntityIntoDTO(entity);
    }
}
