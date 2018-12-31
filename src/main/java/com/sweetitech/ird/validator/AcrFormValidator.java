package com.sweetitech.ird.validator;

import com.sweetitech.ird.domain.dto.requestDto.AcrRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Avijit Barua
 * @created_on 12/28/18 at 3:31 PM
 * @project ird
 */
@Component
public class AcrFormValidator implements Validator {

 /*   @Override
    public boolean supports(Class<?> paramClass) {
        return AcrRequestDto.class.isAssignableFrom(paramClass);
    }*/

    @Override
    public boolean supports(Class<?> paramClass) {
        return AcrRequestDto.class.equals(paramClass);
    }


    @Override
    public void validate(Object o, Errors errors) {
        AcrRequestDto obj = (AcrRequestDto) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "govtId", "1","Can't be empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year", "1", "Can't be empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "assigned_from", "1", "Can't be empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "assigned_to", "1","Can't be empty!");
    }
}
