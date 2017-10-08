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
import com.att.cw.dto.QuestionaireDto;
import com.att.cw.model.QuestionCategory;
import com.att.cw.model.QuestionType;
import com.att.cw.model.Questionaire;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.comparator.CompoundComparator;

/**
 * QuestionaireDtoMapper
 *
 * @author ebrimatunkara
 */
public final class QuestionaireDtoMapper {

    public static List<QuestionaireDto> mapEntitiesIntoDTOs(List<Questionaire> entities) {
        return entities.stream()
                .map(QuestionaireDtoMapper::mapEntityIntoDTO)
                .collect(toList());
    }

    public static QuestionaireDto mapEntityIntoDTO(Questionaire entity) {
        QuestionaireDto dto = new QuestionaireDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setRank(entity.getRank());

        dto.setQuestions(entity.getQuestions()
                .stream().map(q -> {
                    QuestionType qt = q.getQuestionType();
                    QuestionCategory category = q.getCategory();
                    //set questiontype dto
                    QuestionTypeDto qtDto = (qt != null) ? new QuestionTypeDto(qt.getId(),
                            qt.getName(),
                            qt.getDescription(),
                            qt.getShowOptions(),
                            qt.getStyle()) : null;

                    JobQuestionDto dt = new JobQuestionDto(q.getId(),
                            q.getQuestion(),
                            q.isRequired(),
                            qtDto,
                            q.getOptions().stream()
                            .map(op -> {
                                return new QuestionOptionDto(op.getId(), op.getValue());
                            }).collect(toSet()),
                            q.getRank()
                    );

                    if (category != null) {
                        dt.setCategory(new QuestionCategoryDto(category.getId(), category.getCategory()));
                    }
                    return dt;
                })
                .sorted(new QuestionRankComparator())
                .collect(Collectors.toCollection(LinkedHashSet::new)));
        return dto;
    }

    public static Page<QuestionaireDto> mapEntityPageIntoDTOPage(Pageable page, Page<Questionaire> source) {
        List<QuestionaireDto> dtos = mapEntitiesIntoDTOs(source.getContent());
        return new PageImpl<>(dtos, page, source.getTotalElements());
    }
    
    private static class QuestionRankComparator implements Comparator<JobQuestionDto> {
        @Override
        public int compare(JobQuestionDto o1, JobQuestionDto o2) {
            Integer a = o1.getRank();
            Integer b = o2.getRank();
            return a.compareTo(b);
        }

    }
}
