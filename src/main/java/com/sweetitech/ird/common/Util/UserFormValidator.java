package com.sweetitech.ird.common.Util;
import com.sweetitech.ird.domain.User;
import com.sweetitech.ird.domain.dto.requestDto.UserRequestDto;
import com.sweetitech.ird.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
/**
 * @author Avijit Barua
 * @created_on 12/27/18 at 1:14 PM
 * @project ird
 */

@Component
public class UserFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> paramClass) {
        return UserRequestDto.class.isAssignableFrom(paramClass);
    }

    @Autowired
    UserService userService;

    @Override
    public void validate(Object target, Errors errors) {
        UserRequestDto obj = (UserRequestDto) target;


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Can't be Empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "Can't be Empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "Can't be Empty!");


        if(obj.getId()==null && userService.findByUserId(obj.getUserId())!=null)
        {
            errors.rejectValue("userId","1","Already Exists! Try another!");
        }
        if(obj.getId() !=null)
        {
            User user = userService.findById(obj.getId());
            if(obj.getUserId() != user.getUserId() && userService.findByUserId(obj.getUserId()) !=null)
            {
                errors.rejectValue("userId","2","Already Exists! Try another!");
            }
        }


    }
}
