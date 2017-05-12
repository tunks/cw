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
import com.att.cw.model.JobQuestion;
import com.att.cw.model.User;
import java.util.Set;
import static java.util.stream.Collectors.toSet;

/**
 *
 * @author ebrimatunkara
 */
public class JobApplicationDtoMapper {

//        public static List<JobDto> mapEntitiesIntoDTOs(List<Job> entities) {
//        return entities.stream()
//                .map(JobDtoMapper::mapEntityIntoDTO)
//                .collect(toList());
//    }
//
    public static JobApplicationEntryDto mapEntityIntoDTO(JobApplication entity) {
        Job job  = entity.getJob();
        User user = entity.getCandidate().getUser();
        JobApplicationEntryDto dto =  new JobApplicationEntryDto();
        dto.setId(entity.getId());
        dto.setJobId(job.getId());
        dto.setUserId(user.getId());
        Set<QuestionAnswerDto> items = entity.getQuestionAnswers().stream().map(x->{
          JobQuestion q =  x.getQuestion();
          
          QuestionAnswerDto qa = new QuestionAnswerDto();
          //question dto
          JobQuestionDto qDto = new JobQuestionDto();
          qDto.setId(q.getId());
          qa.setQuestion(qDto);
          //answer dto
          AnswerDto  aDto = new AnswerDto();
          aDto.setId(x.getId());
          //answer entry dto
          JobAnswerEntry ansEntry = x.getAnswerEntry();
          if(ansEntry != null){
          AnswerEntryDto entryDto = new AnswerEntryDto();
          entryDto.setId(ansEntry.getId());
          entryDto.setValue(ansEntry.getValue());
          //TODO -- answer options and type
          aDto.setEntry(entryDto);
          }
          qa.setAnswer(aDto);      
         return qa;
        }).collect(toSet());
        
        dto.setQuestionAnswers(items);
        return dto;
    }
//
//    public static JobDto mapFullEntityIntoDto(Job entity) {
//        JobDto dto = mapEntityIntoDTO(entity);
//        List<JobQuestionDto> questions = JobQuestionDtoMapper.mapEntitiesIntoDTOs(entity.getQuestions().stream().collect(toList()));
//        dto.setQuestions(questions.stream().collect(toSet()));
//        return dto;
//    }
//
//    public static Page<JobDto> mapEntityPageIntoDTOPage(Pageable page, Page<Job> source) {
//        List<JobDto> dtos = mapEntitiesIntoDTOs(source.getContent());
//        return new PageImpl<>(dtos, page, source.getTotalElements());
//    }
}
