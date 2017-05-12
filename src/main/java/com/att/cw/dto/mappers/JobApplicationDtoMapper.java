/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto.mappers;

import com.att.cw.dto.JobApplicationEntryDto;
import com.att.cw.dto.JobApplicationEntryDto.AnswerDto;
import com.att.cw.dto.JobApplicationEntryDto.AnswerEntryDto;
import com.att.cw.dto.JobApplicationEntryDto.QuestionAnswerDto;
import com.att.cw.dto.JobQuestionDto;
import com.att.cw.model.Job;
import com.att.cw.model.JobAnswerEntry;
import com.att.cw.model.JobApplication;
import com.att.cw.model.JobCandidate;
import com.att.cw.model.JobQuestion;
import com.att.cw.model.JobQuestionAnswer;
import com.att.cw.model.User;
import java.util.Set;
import static java.util.stream.Collectors.toSet;

/**
 *
 * @author ebrimatunkara
 */
public class JobApplicationDtoMapper {

    public static JobApplicationEntryDto mapEntityIntoDTO(JobApplication entity) {
        Job job = entity.getJob();
        return entityIntoDto(entity, job);
    }

    private static JobApplicationEntryDto entityIntoDto(JobApplication entity, Job job) {
        JobCandidate candidate = entity.getCandidate();
        JobApplicationEntryDto dto = new JobApplicationEntryDto();
        dto.setId(entity.getId());
        dto.setJobId(job.getId());
        if (candidate != null && candidate.getUser() != null) {
            dto.setUserId(candidate.getUser().getId());
        }
        Set<QuestionAnswerDto> items = entity.getQuestionAnswers().stream().map(x -> {
            JobQuestion q = x.getQuestion();
            return mapQuestionAnswer(q, x);
        }).collect(toSet());

        dto.setQuestionAnswers(items);
        return dto;
    }

    private static QuestionAnswerDto mapQuestionAnswer(JobQuestion q, JobQuestionAnswer x) {
        QuestionAnswerDto qa = mapJobQuestion(q);
        AnswerDto aDto = mapAnswer(x);
        qa.setAnswer(aDto);
        return qa;
    }

    private static AnswerDto mapAnswer(JobQuestionAnswer x) {
        //answer dto
        AnswerDto aDto = new AnswerDto();
        if (x != null) {
            aDto.setId(x.getId());
            //answer entry dto
            JobAnswerEntry ansEntry = x.getAnswerEntry();
            if (ansEntry != null) {
                AnswerEntryDto entryDto = new AnswerEntryDto();
                entryDto.setId(ansEntry.getId());
                entryDto.setValue(ansEntry.getValue());
                aDto.setEntry(entryDto);
            }
        }
        return aDto;
    }

    private static QuestionAnswerDto mapJobQuestion(JobQuestion q) {
        QuestionAnswerDto qa = new QuestionAnswerDto();
        //question dto
        JobQuestionDto qDto = new JobQuestionDto();
        qDto.setId(q.getId());
        qDto.setQuestion(q.getQuestion());
        qDto.setQuestionType(JobQuestionDtoMapper.mapQuestionType(q.getQuestionType()));
        qDto.setRequired(q.getRequired());
        qDto.setOptions(JobQuestionDtoMapper.mapQuestionOptions(q.getOptions()));
        qa.setQuestion(qDto);
        return qa;
    }

    public static JobApplicationEntryDto mapEntityIntoDTO(JobApplication entity, Job job) {
        JobApplicationEntryDto dto = new JobApplicationEntryDto();
        if (entity != null) {
            dto = entityIntoDto(entity, job);
        }

        Set<Long> questionsIds = dto.getQuestionAnswers().stream()
                .map(x -> {
                    return x.getQuestion().getId();
                }).collect(toSet());

        Set<QuestionAnswerDto> items = job.getQuestions().stream()
                .filter(q -> {
                    return !questionsIds.contains(q.getId());
                })
                .map(q -> {
                    return mapQuestionAnswer(q, null);
                }).collect(toSet());
        //add question answer items
        dto.getQuestionAnswers().addAll(items);
        return dto;
    }
}
