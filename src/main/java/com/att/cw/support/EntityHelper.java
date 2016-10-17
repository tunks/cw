/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.support;

import com.att.cw.exception.JobException;
import com.att.cw.model.JobVacancy;
import java.util.Calendar;
import java.util.Date;

/**
 * Entity Helper class
 *
 * @author ebrimatunkara
 */
public class EntityHelper {

    /**
     * Helper function to determine of job vacancy close date is overdue
     * @param vacancy
     * @return, true or false
     */
    public static boolean isJobDateOverdue(JobVacancy vacancy) {
        Calendar cal = Calendar.getInstance();
        Date currentTime = cal.getTime();
        return validateDate(vacancy.getCloseDate(),currentTime);
    }
    /**
     * Validate job vacancy object
     * @param vacancy 
     */
    public static void validateJobVacancy(JobVacancy vacancy) {
         //throw exception if job vacancy is null
        if (vacancy == null) {
            throw new JobException("Job object operation failed, vacancy is null!");
        }
        if(!validateDate(vacancy.getOpenDate(),vacancy.getCloseDate())){
            throw new JobException("Job object operation failed,close date must be greater than open date!");
        }
    }
    
    /**
     * Returns true if d2 is greater than d1 else return false
     */
    private static boolean validateDate(Date d1, Date d2){
      int value = d1.compareTo(d2);
        switch (value) {
            case -1:
                return true;
            case 0:
                return false;
            default:
                return false;
        }
    }
}
