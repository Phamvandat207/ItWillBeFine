package com.ifi.validator;

import com.ifi.constants.Gender;
import com.ifi.dto.EmployeeDTO;
import com.ifi.dto.EngineerDTO;
import com.ifi.dto.WorkerDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorWithDTOTest {
    UUID expectedId = UUID.randomUUID();
    String expectedName = "valid_name";
    Gender expectedGender = Gender.MALE;
    Date expectedDob = Date.from(LocalDate
            .of(1992, 9, 17)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant());
    Date expectedJoinedDate = Date.from(Instant.now());

    @Test
    @DisplayName("Should Create Employee with Valid Data")
    void should_create_employee_with_valid_data() {
        EmployeeDTO employeeDTO = new EmployeeDTO(
                expectedId,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate
        );

        assertNotNull(employeeDTO);
        assertEquals(expectedId, employeeDTO.getId());
        assertEquals(expectedName, employeeDTO.getName());
        assertEquals(expectedGender, employeeDTO.getGender());
        assertEquals(expectedDob, employeeDTO.getDateOfBirth());
        assertEquals(expectedJoinedDate, employeeDTO.getJoinedDate());
    }

    @Test
    @DisplayName("Should Create Employee with Null id")
    void should_create_employee_with_null_id() {
        EmployeeDTO employeeDTO = new EmployeeDTO(
                null,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate
        );

        assertNotNull(employeeDTO);
        assertNull(employeeDTO.getId());
        assertEquals(expectedName, employeeDTO.getName());
        assertEquals(expectedGender, employeeDTO.getGender());
        assertEquals(expectedDob, employeeDTO.getDateOfBirth());
        assertEquals(expectedJoinedDate, employeeDTO.getJoinedDate());
    }

    @Test
    @DisplayName("Should Create Engineer with Valid data")
    void should_create_engineer_with_valid_data() {
        BigDecimal expectedMonthlyWage = BigDecimal.valueOf(0.08);
        BigDecimal expectedAllowance = BigDecimal.valueOf(0.003);

        EngineerDTO engineerDTO = new EngineerDTO(
                expectedId,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate,
                expectedMonthlyWage,
                expectedAllowance
        );

        assertNotNull(engineerDTO);
        assertEquals(expectedId, engineerDTO.getId());
        assertEquals(expectedName, engineerDTO.getName());
        assertEquals(expectedGender, engineerDTO.getGender());
        assertEquals(expectedDob, engineerDTO.getDateOfBirth());
        assertEquals(expectedJoinedDate, engineerDTO.getJoinedDate());
        assertEquals(expectedMonthlyWage, engineerDTO.getEngineerMonthlyWage());
        assertEquals(expectedAllowance, engineerDTO.getEngineerAllowance());
    }

    @Test
    @DisplayName("Should Create Engineer with null id")
    void should_create_engineer_with_null_id() {
        BigDecimal expectedMonthlyWage = BigDecimal.valueOf(0.08);
        BigDecimal expectedAllowance = BigDecimal.valueOf(0.003);

        EngineerDTO engineerDTO = new EngineerDTO(
                null,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate,
                expectedMonthlyWage,
                expectedAllowance
        );

        assertNotNull(engineerDTO);
        assertNull(engineerDTO.getId());
        assertEquals(expectedName, engineerDTO.getName());
        assertEquals(expectedGender, engineerDTO.getGender());
        assertEquals(expectedDob, engineerDTO.getDateOfBirth());
        assertEquals(expectedJoinedDate, engineerDTO.getJoinedDate());
        assertEquals(expectedMonthlyWage, engineerDTO.getEngineerMonthlyWage());
        assertEquals(expectedAllowance, engineerDTO.getEngineerAllowance());
    }

    @Test
    @DisplayName("Should Create Engineer with null id and null wage")
    void should_create_engineer_with_null_id_and_wage() {
        EngineerDTO engineerDTO = new EngineerDTO(
                null,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate,
                null,
                null
        );

        assertNotNull(engineerDTO);
        assertNull(engineerDTO.getId());
        assertEquals(expectedName, engineerDTO.getName());
        assertEquals(expectedGender, engineerDTO.getGender());
        assertEquals(expectedDob, engineerDTO.getDateOfBirth());
        assertEquals(expectedJoinedDate, engineerDTO.getJoinedDate());
        assertNull(engineerDTO.getEngineerMonthlyWage());
        assertNull(engineerDTO.getEngineerAllowance());
    }

    @Test
    @DisplayName("Should Create Worker with Valid data")
    void should_create_worker_with_valid_data() {
        BigDecimal expectedHourlyRating = BigDecimal.valueOf(0.03);

        WorkerDTO workerDTO = new WorkerDTO(
                expectedId,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate,
                expectedHourlyRating
        );

        assertNotNull(workerDTO);
        assertEquals(expectedId, workerDTO.getId());
        assertEquals(expectedName, workerDTO.getName());
        assertEquals(expectedGender, workerDTO.getGender());
        assertEquals(expectedDob, workerDTO.getDateOfBirth());
        assertEquals(expectedJoinedDate, workerDTO.getJoinedDate());
        assertEquals(expectedHourlyRating, workerDTO.getWorkerHourlyRating());
    }

    @Test
    @DisplayName("Should Create Worker with null id")
    void should_create_worker_with_null_id() {
        BigDecimal expectedHourlyRating = BigDecimal.valueOf(0.03);

        WorkerDTO workerDTO = new WorkerDTO(
                null,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate,
                expectedHourlyRating
        );

        assertNotNull(workerDTO);
        assertNull(workerDTO.getId());
        assertEquals(expectedName, workerDTO.getName());
        assertEquals(expectedGender, workerDTO.getGender());
        assertEquals(expectedDob, workerDTO.getDateOfBirth());
        assertEquals(expectedJoinedDate, workerDTO.getJoinedDate());
        assertEquals(expectedHourlyRating, workerDTO.getWorkerHourlyRating());
    }

    @Test
    @DisplayName("Should Create Worker with null id and null hourly rating")
    void should_create_worker_with_null_id_and_rating() {
        WorkerDTO workerDTO = new WorkerDTO(
                null,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate,
                null
        );

        assertNotNull(workerDTO);
        assertNull(workerDTO.getId());
        assertEquals(expectedName, workerDTO.getName());
        assertEquals(expectedGender, workerDTO.getGender());
        assertEquals(expectedDob, workerDTO.getDateOfBirth());
        assertEquals(expectedJoinedDate, workerDTO.getJoinedDate());
        assertNull(workerDTO.getWorkerHourlyRating());
    }

    @Test
    @DisplayName("Should Not create Employee with default constructor")
    void should_not_create_employee() {
        Class<EmployeeDTO> clazz = EmployeeDTO.class;
        assertThrows(InstantiationException.class, () -> {
            EmployeeDTO employeeDTO = clazz.newInstance();
            assertNull(employeeDTO);
        });
    }

    @Test
    @DisplayName("Should Not create Engineer with default constructor")
    void should_not_create_engineer() {
        Class<EngineerDTO> clazz = EngineerDTO.class;
        assertThrows(InstantiationException.class, () -> {
            EngineerDTO engineerDTO = clazz.newInstance();
            assertNull(engineerDTO);
        });
    }

    @Test
    @DisplayName("Should Not create Worker with default constructor")
    void should_not_create_worker() {
        Class<WorkerDTO> clazz = WorkerDTO.class;
        assertThrows(InstantiationException.class, () -> {
            WorkerDTO workerDTO = clazz.newInstance();
            assertNull(workerDTO);
        });
    }

    @Test
    @DisplayName("Should Not create Employee with null name")
    void should_not_create_employee_null_name() {
        assertThrows(AssertionFailedError.class, () -> {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    expectedId,
                    null,
                    expectedGender,
                    expectedDob,
                    expectedJoinedDate
            );
            assertNull(employeeDTO);
        });
    }

    @Test
    @DisplayName("Should Not create Employee with invalid name")
    void should_not_create_employee_invalid_name() {
        assertThrows(AssertionFailedError.class, () -> {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    expectedId,
                    "a",
                    expectedGender,
                    expectedDob,
                    expectedJoinedDate
            );
            assertNull(employeeDTO);
        });
    }

    @Test
    @DisplayName("Should Not create Employee with null gender")
    void should_not_create_employee_null_gender() {
        assertThrows(AssertionFailedError.class, () -> {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    expectedId,
                    expectedName,
                    null,
                    expectedDob,
                    expectedJoinedDate
            );
            assertNull(employeeDTO);
        });
    }

    @Test
    @DisplayName("Should Not create Employee with null joined date")
    void should_not_create_employee_null_joinedDate() {
        assertThrows(AssertionFailedError.class, () -> {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    expectedId,
                    expectedName,
                    expectedGender,
                    expectedDob,
                    null
            );
            assertNull(employeeDTO);
        });
    }

    @Test
    @DisplayName("Should not create Engineer with negative wage")
    void should_not_create_engineer_negative_wage() {
        BigDecimal expectedMonthlyWage = BigDecimal.valueOf(-369547.08);
        BigDecimal expectedAllowance = BigDecimal.valueOf(0.003);

        if (expectedAllowance.doubleValue() > 0) {
            System.out.println("haha");
        }

        EngineerDTO engineerDTO1 = new EngineerDTO(
                expectedId,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate,
                expectedMonthlyWage,
                expectedAllowance
        );
        System.out.println(engineerDTO1);

        assertThrows(AssertionFailedError.class, () -> {
            EngineerDTO engineerDTO = new EngineerDTO(
                    expectedId,
                    expectedName,
                    expectedGender,
                    expectedDob,
                    expectedJoinedDate,
                    expectedMonthlyWage,
                    expectedAllowance
            );
            assertNull(engineerDTO);
        });

    }
}
