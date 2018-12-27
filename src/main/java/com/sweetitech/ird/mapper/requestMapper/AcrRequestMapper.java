package com.sweetitech.ird.mapper.requestMapper;

import com.sweetitech.ird.common.Util.DateTimeUtils;
import com.sweetitech.ird.domain.ACR;
import com.sweetitech.ird.domain.User;
import com.sweetitech.ird.domain.dto.requestDto.AcrRequestDto;
import com.sweetitech.ird.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

/**
 * @author Avijit Barua
 * @created_on 12/27/18 at 10:30 AM
 * @project ird
 */
@Service
public class AcrRequestMapper {
    @Autowired
    UserService userService;

    public ACR map(AcrRequestDto dto) throws ParseException {
        ACR acr= new ACR();
        acr.setGovtId(dto.getGovtId());
        acr.setYear(dto.getYear());
        acr.setAssigned_from(DateTimeUtils.toDate(dto.getAssigned_from()));
        acr.setAssigned_to(DateTimeUtils.toDate(dto.getAssigned_to()));
        User user = userService.findByUserId("1201010m");
        acr.setUser(user);
        acr.setCreatedOn(new Date());
        return acr;
    }
}
