/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.restricted.job;

import com.att.cw.controller.BaseController;
import com.att.cw.dto.QuestionTypeDto;
import com.att.cw.model.QuestionType;
import com.att.cw.service.QuestionTypeService;
import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * QuestionType controller
 *
 * @author ebrimatunkara
 */
@RestController
@RequestMapping("/restricted/questiontypes")
public class QuestionTypeController implements BaseController<QuestionType, Long> {

    @Autowired
    private QuestionTypeService questionTypeService;

    /**
     * Get all question types
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Set<QuestionTypeDto> findAllQuestionTypes() {
        return questionTypeService.findAll()
                .stream()
                .map(qt -> {
                    return new QuestionTypeDto(qt.getId(),
                            qt.getName(),
                            qt.getDescription(),
                            qt.getShowOptions(),
                            qt.getStyle()
                    );
                })
                .collect(toSet());
    }

    @Override
    public QuestionType find(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public QuestionType create(QuestionType object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public QuestionType update(QuestionType object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
