package com.ifi.util.validator.joindate;


import com.ifi.dto.EmployeeDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JoinDateValidator implements ConstraintValidator<JoinDate, EmployeeDTO> {

    public void initialize(JoinDate constraintAnnotation) {

    }

    public boolean isValid(EmployeeDTO employeeDTO, ConstraintValidatorContext constraintValidatorContext) {
        if(employeeDTO.getJoinedDate().compareTo(employeeDTO.getDateOfBirth()) > 0){
            return true;
        }else
        {
            return false;
        }

    }
}
