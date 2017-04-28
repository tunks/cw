/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto.mappers;

import com.att.cw.dto.QuestionCategoryDto;
import com.att.cw.model.QuestionCategory;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author ebrimatunkara
 */
public final class QuestionCategoryDtoMapper {

    public static List<QuestionCategoryDto> mapEntitiesIntoDTOs(List<QuestionCategory> entities) {
        return entities.stream()
                .map(QuestionCategoryDtoMapper::mapEntityIntoDTO)
                .collect(toList());
    }

    public static QuestionCategoryDto mapEntityIntoDTO(QuestionCategory entity) {
        QuestionCategoryDto dto = new QuestionCategoryDto();
        dto.setId(entity.getId());
        dto.setCategory(entity.getCategory());
        return dto;
    }

}
