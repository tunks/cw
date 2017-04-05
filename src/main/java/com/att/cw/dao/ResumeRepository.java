/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.Resume;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Resume repository
 *
 * @author ebrimatunkara
 */
public interface ResumeRepository extends PagingAndSortingRepository<Resume, Long> {

}
