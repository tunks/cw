/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto;

/**
 *
 * @author ebrimatunkara
 */
public class QuestionTypeDto extends JobComponentDto {

    private String description;
    private Boolean showOptions;
    private String style;

    public QuestionTypeDto() {
    }

    public QuestionTypeDto(Long id, String name) {
        super(id, name);
    }

    public QuestionTypeDto(Long id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    public QuestionTypeDto(Long id, String name, String description, Boolean showOptions) {
        this(id, name, description);
        this.showOptions = showOptions;
    }

    public QuestionTypeDto(Long id, String name, String description, Boolean showOptions, String style) {
        this(id, name, description, showOptions);
        this.style = style;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getShowOptions() {
        return showOptions;
    }

    public void setShowOptions(Boolean showOptions) {
        this.showOptions = showOptions;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
