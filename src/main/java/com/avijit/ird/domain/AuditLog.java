package com.avijit.ird.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Avijit Barua
 * @created_on 3/13/19 at 3:41 PM
 * @project ird
 */
@Entity
@Table(name = "audit_log")
public class AuditLog extends BaseEntity{

    private String log;

    public AuditLog() {
    }

    public AuditLog(String log) {
        this.log = log;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    @Override
    public String toString() {
        return "AuditLog{" +
                "log='" + log + '\'' +
                '}';
    }
}
