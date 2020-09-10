package com.ifi.util.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ifi.dto.EmployeeDTO;
import com.ifi.dto.EngineerDTO;
import com.ifi.dto.WorkerDTO;
import com.ifi.dto.WrapperDTO;

import java.io.IOException;

public class WrapperDTODeserializer extends JsonDeserializer<WrapperDTO> {

    static final String ENGINEER = "ENGINEER";

    @Override
    public WrapperDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        WrapperDTO wrapperDTO = new WrapperDTO();
        EmployeeDTO employeeDTO;
        ObjectMapper objectMapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String jsonValue;
        if (node.has("worker")) {
            jsonValue = reconstructWorkerJson(node);
            employeeDTO = objectMapper.readValue(jsonValue, WorkerDTO.class);
        } else if (node.has("engineer")) {
            jsonValue = reconstructEngineerJson(node);
            employeeDTO = objectMapper.readValue(jsonValue, EngineerDTO.class);
        } else {
            jsonValue = node.toString();
            employeeDTO = objectMapper.readValue(jsonValue, EmployeeDTO.class);
        }
        wrapperDTO.setEmployeeDTO(employeeDTO);
        return wrapperDTO;
    }

    private String reconstructEngineerJson(JsonNode rootNode) {
        JsonNode engineerNode = rootNode.get("engineer");
        String monthlyWage = "";
        String allowance = "";
        if (engineerNode.hasNonNull("monthly_wage")) {
            monthlyWage = engineerNode.get("monthly_wage").asText();
        }
        if (engineerNode.hasNonNull("allowance")) {
            allowance = engineerNode.get("allowance").asText();
        }
        ObjectNode objectNode = (ObjectNode) rootNode;
        objectNode
                .put("monthly_wage", monthlyWage)
                .put("allowance", allowance)
                .remove("engineer");
        return objectNode.toString();
    }

    private String reconstructWorkerJson(JsonNode rootNode) {
        JsonNode workerNode = rootNode.get("worker");
        String hourlyRating = "";
        if (workerNode.hasNonNull("hourly_rating")) {
            hourlyRating = workerNode.get("hourly_rating").asText();
        }
        ObjectNode objectNode = (ObjectNode) rootNode;
        objectNode
                .put("hourly_rating", hourlyRating)
                .remove("worker");
        return objectNode.toString();
    }
}
