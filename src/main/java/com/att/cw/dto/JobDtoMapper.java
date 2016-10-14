/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto;

import com.att.cw.model.Job;
import com.att.cw.model.JobType;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author ebrimatunkara
 */
public final class JobDtoMapper {
    public static List<JobDto> mapEntitiesIntoDTOs(List<Job> entities) {
        return entities.stream()
                .map(JobDtoMapper::mapEntityIntoDTO)
                .collect(toList());
    }

    public static JobDto mapEntityIntoDTO(Job entity) {
        JobType jobType = entity.getJobType();
        JobDto dto = new JobDto();
        dto.setDescription(entity.getDescription());
        dto.setId(entity.getId());
        dto.setCategories(entity.getCategories()
                .stream().map(c->{
                          return new JobComponentDto(c.getId(),c.getName());
                }).collect(toSet()));
        dto.setJobType((jobType != null)? new JobComponentDto(jobType.getId(), jobType.getName()): null);
        dto.setLocation(entity.getLocation());
        dto.setTitle(entity.getTitle());
        dto.setVacancy(entity.getVacancy());
        return dto;
    }

    public static Page<JobDto> mapEntityPageIntoDTOPage(Pageable page, Page<Job> source) {
        List<JobDto> dtos = mapEntitiesIntoDTOs(source.getContent());
        return new PageImpl<>(dtos, page, source.getTotalElements());
    }
}
