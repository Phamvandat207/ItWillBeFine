package com.ifi.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ifi.util.jackson.WrapperDTODeserializer;
import lombok.Value;

@JsonDeserialize(using = WrapperDTODeserializer.class)
@Value
public class WrapperDTO {
    EmployeeDTO employeeDTO;
}
