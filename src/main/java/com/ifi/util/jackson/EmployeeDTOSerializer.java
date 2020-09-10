package com.ifi.util.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ifi.dto.EmployeeDTO;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class EmployeeDTOSerializer extends JsonSerializer<EmployeeDTO> {

    @Override
    public void serialize(EmployeeDTO employeeDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("employee_name", employeeDTO.getName());
        jsonGenerator.writeStringField("employee_gender", employeeDTO.getGender().name());
        jsonGenerator.writeStringField("date_of_birth", employeeDTO.getDateOfBirth().format(formatter));
        jsonGenerator.writeStringField("joined_date", employeeDTO.getDateOfBirth().format(formatter));
        jsonGenerator.writeEndObject();
    }
}
