package com.avijit.ird.service;

import com.avijit.ird.domain.AuditLog;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 3/13/19 at 5:17 PM
 * @project ird
 */
@Service
public interface LogService {

    List<AuditLog> getLogList();
}
