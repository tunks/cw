/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.support;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.flywaydb.core.api.migration.spring.BaseSpringJdbcMigration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author ebrimatunkara
 */
public class FlywayMigration extends Flyway {

    @Override
    public int migrate() throws FlywayException {
        this.repair();
        return super.migrate(); //To change body of generated methods, choose Tools | Templates.
    }

}
