package com.avijit.ird.repository;

import com.avijit.ird.domain.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 3/13/19 at 3:45 PM
 * @project ird
 */
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    @Query(value = "SELECT * FROM audit_log ORDER BY id DESC LIMIT 100", nativeQuery = true)
    List<AuditLog> getLogList();
}
