/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto.mappers;

import com.att.cw.dto.QuestionOptionDto;
import com.att.cw.model.QuestionOption;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * QuestionOptionDtoMapper
 *
 * @author ebrimatunkara
 */
public final class QuestionOptionDtoMapper {

    public static List<QuestionOptionDto> mapEntitiesIntoDTOs(List<QuestionOption> entities) {
        return entities.stream()
                .map(QuestionOptionDtoMapper::mapEntityIntoDTO)
                .collect(toList());
    }

    public static QuestionOptionDto mapEntityIntoDTO(QuestionOption entity) {
        return new QuestionOptionDto(entity.getId(), entity.getValue());
    }

    public static QuestionOption mapDtoIntoEntity(QuestionOptionDto dto) {
        return new QuestionOption(dto.getId(), dto.getValue());
    }

    public static Page<QuestionOptionDto> mapEntityPageIntoDTOPage(Pageable page, Page<QuestionOption> source) {
        List<QuestionOptionDto> dtos = mapEntitiesIntoDTOs(source.getContent());
        return new PageImpl<>(dtos, page, source.getTotalElements());
    }
}
