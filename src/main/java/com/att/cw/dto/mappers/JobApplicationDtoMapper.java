/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto.mappers;

import com.att.cw.dto.JobApplicationEntryDto;
import com.att.cw.dto.JobApplicationEntryDto.AnswerDto;
import com.att.cw.dto.JobApplicationEntryDto.AnswerEntryDto;
import com.att.cw.dto.JobApplicationEntryDto.AnswerEntryOption;
import com.att.cw.dto.JobApplicationEntryDto.FileDto;
import com.att.cw.dto.JobApplicationEntryDto.FileDtoBuilder;
import com.att.cw.dto.JobApplicationEntryDto.QuestionAnswerDto;
import com.att.cw.dto.JobQuestionDto;
import com.att.cw.model.FileDocument;
import com.att.cw.model.Job;
import com.att.cw.model.JobAnswerEntry;
import com.att.cw.model.JobApplication;
import com.att.cw.model.JobCandidate;
import com.att.cw.model.JobQuestion;
import com.att.cw.model.JobQuestionAnswer;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toSet;

/**
 *
 * @author ebrimatunkara
 */
public class JobApplicationDtoMapper implements BaseEntityMapper<JobApplicationEntryDto,JobApplication>{

    public static JobApplicationEntryDto mapEntityIntoDTO(JobApplication entity) {
        Job job = entity.getJob();
        return entityIntoDto(entity, job);
    }

    private static JobApplicationEntryDto entityIntoDto(JobApplication entity, Job job) {
        JobApplicationEntryDto dto = new JobApplicationEntryDto();
        dto.setJobId(job.getId());
        dto.setTitle(job.getTitle());
        if (entity != null) {
            dto.setId(entity.getId());
            dto.setSubmitted(entity.isSubmitted());
            JobCandidate candidate = entity.getCandidate();
            if (candidate != null && candidate.getUser() != null) {
                dto.setUserId(candidate.getUser().getId());
            }

            Set<QuestionAnswerDto> items = entity.getQuestionAnswers().stream().map(x -> {
                JobQuestion q = x.getQuestion();
                return mapQuestionAnswer(q, x);
            }).sorted(new QuestionRankComparator())              
             .collect(Collectors.toCollection(LinkedHashSet::new));

            dto.setQuestionAnswers(items);
        }
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
                entryDto.setOptions(ansEntry.getQuestionOptions().stream()
                                                                 .map(a->{ return new AnswerEntryOption(a.getId(), a.getValue());})
                                                                 .collect(toSet()));
                FileDocument document = ansEntry.getDocument();
                if(document != null){
                   FileDto file = new FileDtoBuilder()
                                         .setId(document.getId())
                                         .setName(document.getName())
                                         .setSize(document.getSize())
                                         .setLastModifiedDate(document.getLastModifiedDate())
                                         .setType(document.getContentType())
                                         .build();
                   entryDto.setFile(file);
                }
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
        qDto.setCategory(JobQuestionDtoMapper.mapCategory(q.getCategory()));
        qDto.setRank(q.getRank());
        qa.setQuestion(qDto);
        return qa;
    }

    public static JobApplicationEntryDto mapEntityIntoDTO(JobApplication entity, Job job) {
        JobApplicationEntryDto dto = entityIntoDto(entity, job);
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
                })
                //.sorted(new QuestionRankComparator())              
                .collect(Collectors.toCollection(LinkedHashSet::new));
        //add question answer items
        dto.getQuestionAnswers().addAll(items);
        dto.setQuestionAnswers(dto.getQuestionAnswers().stream()
                                  .sorted(new QuestionRankComparator())              
                                  .collect(Collectors.toCollection(LinkedHashSet::new)));

        return dto;
    }

    @Override
    public JobApplicationEntryDto map(JobApplication entity) {
        return JobApplicationDtoMapper.mapEntityIntoDTO(entity);
    }

    
      private static class QuestionRankComparator implements Comparator<QuestionAnswerDto> {
       
          public int compare(JobQuestionDto o1, JobQuestionDto o2) {
            Integer a = o1.getRank();
            Integer b = o2.getRank();
            return a.compareTo(b);
        }

        @Override
        public int compare(QuestionAnswerDto o1, QuestionAnswerDto o2) {
           return compare(o1.getQuestion(),o2.getQuestion());
        }

    }
}
