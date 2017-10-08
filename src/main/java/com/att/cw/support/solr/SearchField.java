/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.support.solr;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 *
 * @author ebrimatunkara
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface SearchField {
    /**
     * (Optional) Whether the field value should be nullable for search index
     * @return 
     */
    boolean nullable() default true;
}
