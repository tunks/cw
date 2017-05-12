/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto.mappers;

import com.att.cw.dto.JobQuestionDto;
import com.att.cw.dto.QuestionCategoryDto;
import com.att.cw.dto.QuestionOptionDto;
import com.att.cw.dto.QuestionTypeDto;
import com.att.cw.model.QuestionType;
import com.att.cw.model.JobQuestion;
import com.att.cw.model.QuestionCategory;
import com.att.cw.model.QuestionOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * JobQuestionDto mapper
 *
 * @author ebrimatunkara
 */
public final class JobQuestionDtoMapper {

    public static List<JobQuestionDto> mapEntitiesIntoDTOs(List<JobQuestion> entities) {
        return entities.stream()
                .map(JobQuestionDtoMapper::mapEntityIntoDTO)
                .collect(toList());
    }

    public static JobQuestionDto mapEntityIntoDTO(JobQuestion entity) {
        QuestionType qt = entity.getQuestionType();
        QuestionCategory category = entity.getCategory();
        JobQuestionDto dto = new JobQuestionDto();
        dto.setId(entity.getId());
        dto.setQuestion(entity.getQuestion());
        dto.setRequired(entity.isRequired());
        dto.setReferenceNumber(entity.getReferenceNumber());
        dto.setCategory((category != null) ? new QuestionCategoryDto(category.getId(), category.getCategory()) : null);
        dto.setQuestionType(mapQuestionType(qt));
        dto.setOptions(mapQuestionOptions(entity.getOptions()));
        return dto;
    }

    public static Set<QuestionOptionDto> mapQuestionOptions(Set<QuestionOption> options) {
        return (options != null) ? options
                .stream()
                .map(op -> {
                    return new QuestionOptionDto(op.getId(), op.getValue());
                }).collect(toSet()) : new HashSet();
    }

    public static QuestionTypeDto mapQuestionType(QuestionType qt) {
        return (qt != null) ? new QuestionTypeDto(qt.getId(),
                qt.getName(),
                qt.getDescription(),
                qt.getShowOptions(),
                qt.getStyle()) : null;
    }

    public static Page<JobQuestionDto> mapEntityPageIntoDTOPage(Pageable page, Page<JobQuestion> source) {
        List<JobQuestionDto> dtos = mapEntitiesIntoDTOs(source.getContent());
        return new PageImpl<>(dtos, page, source.getTotalElements());
    }
}
