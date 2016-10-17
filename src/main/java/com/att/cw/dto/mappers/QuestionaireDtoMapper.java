/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto.mappers;

import com.att.cw.dto.QuestionDto;
import com.att.cw.dto.QuestionaireDto;
import com.att.cw.model.Questionaire;
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
        dto.setQuestions(entity.getQuestions()
                               .stream().map(q->{
                                  return new QuestionDto(q.getId(),
                                                         q.getQuestion(),
                                                         q.isRequired(),
                                                         q.getQuestionType());
                               }).collect(toSet()));
        return dto;
    }

    public static Page<QuestionaireDto> mapEntityPageIntoDTOPage(Pageable page, Page<Questionaire> source) {
        List<QuestionaireDto> dtos = mapEntitiesIntoDTOs(source.getContent());
        return new PageImpl<>(dtos, page, source.getTotalElements());
    }
}
