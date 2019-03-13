package com.avijit.ird.serviceImpl;

import com.avijit.ird.domain.AuditLog;
import com.avijit.ird.repository.AuditLogRepository;
import com.avijit.ird.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 3/13/19 at 5:17 PM
 * @project ird
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    AuditLogRepository logRepository;


    @Override
    public List<AuditLog> getLogList() {
        return  logRepository.getLogList();
    }
}
