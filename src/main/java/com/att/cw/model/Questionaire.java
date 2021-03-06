/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Questionaire Entity - contains a set of questions
 *
 * @author ebrimatunkara
 */
@Entity
@Table(name = "QUESTIONAIRE")
public class Questionaire extends Component {

    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true,mappedBy="questionaire")
    private Set<JobQuestion> questions = new HashSet();

        /**
     * Question Ranking
     */
    private int rank = 0; 
    
    public Set<JobQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<JobQuestion> questions) {
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
