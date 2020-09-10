package com.ifi.validator;

import com.ifi.constants.Gender;
import com.ifi.dto.EmployeeDTO;
import com.ifi.dto.EngineerDTO;
import com.ifi.dto.WorkerDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ValidatorWithDTOTest {
    private static Validator validator;

    @BeforeAll
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    UUID expectedId = UUID.randomUUID();
    String expectedName = "valid_name";
    Gender expectedGender = Gender.MALE;
    LocalDate expectedDob = LocalDate.of(1992, 9, 17);
    LocalDate expectedJoinedDate = LocalDate.now();

    @Test
    @DisplayName("Should validate Employee with Valid Data")
    void should_validate_employee_with_valid_data() {
        EmployeeDTO employeeDTO = new EmployeeDTO(
                expectedId,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate
        );
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(employeeDTO);
        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("Should validate Employee with Null id")
    void should_validate_employee_with_null_id() {
        EmployeeDTO employeeDTO = new EmployeeDTO(
                null,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate
        );
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(employeeDTO);
        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("Should validate Engineer with Valid data")
    void should_validate_engineer_with_valid_data() {
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
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(engineerDTO);
        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("Should validate Engineer with null id")
    void should_validate_engineer_with_null_id() {
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
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(engineerDTO);
        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("Should validate Engineer with null id and null wage")
    void should_validate_engineer_with_null_id_and_wage() {
        EngineerDTO engineerDTO = new EngineerDTO(
                null,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate,
                null,
                null
        );
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(engineerDTO);
        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("Should validate Worker with Valid data")
    void should_validate_worker_with_valid_data() {
        BigDecimal expectedHourlyRating = BigDecimal.valueOf(0.03);
        WorkerDTO workerDTO = new WorkerDTO(
                expectedId,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate,
                expectedHourlyRating
        );
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(workerDTO);
        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("Should validate Worker with null id")
    void should_validate_worker_with_null_id() {
        BigDecimal expectedHourlyRating = BigDecimal.valueOf(0.03);
        WorkerDTO workerDTO = new WorkerDTO(
                null,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate,
                expectedHourlyRating
        );
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(workerDTO);
        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("Should validate Worker with null id and null hourly rating")
    void should_validate_worker_with_null_id_and_rating() {
        WorkerDTO workerDTO = new WorkerDTO(
                null,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate,
                null
        );
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(workerDTO);
        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("Should Not validate Employee with null name")
    void should_not_validate_employee_null_name() {
        EmployeeDTO employeeDTO = new EmployeeDTO(
                expectedId,
                null,
                expectedGender,
                expectedDob,
                expectedJoinedDate);
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(employeeDTO);
        assertNotEquals(0, violations.size());
    }

    @Test
    @DisplayName("Should Not validate Employee with invalid name")
    void should_not_validate_employee_invalid_name() {
        EmployeeDTO employeeDTO = new EmployeeDTO(
                expectedId,
                "a",
                expectedGender,
                expectedDob,
                expectedJoinedDate
        );
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(employeeDTO);
        assertNotEquals(0, violations.size());
    }

    @Test
    @DisplayName("Should Not validate Employee with null gender")
    void should_not_validate_employee_null_gender() {
        EmployeeDTO employeeDTO = new EmployeeDTO(
                expectedId,
                expectedName,
                null,
                expectedDob,
                expectedJoinedDate
        );
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(employeeDTO);
        assertNotEquals(0, violations.size());
    }

    @Test
    @DisplayName("Should Not validate Employee with null joined date")
    void should_not_validate_employee_null_joinedDate() {
        EmployeeDTO employeeDTO = new EmployeeDTO(
                expectedId,
                expectedName,
                expectedGender,
                expectedDob,
                null
        );
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(employeeDTO);
        assertNotEquals(0, violations.size());
    }

    @Test
    @DisplayName("Should not validate Engineer with negative wage")
    void should_not_validate_engineer_negative_wage() {
        BigDecimal expectedMonthlyWage = BigDecimal.valueOf(-10.08);
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
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(engineerDTO);
        assertNotEquals(0, violations.size());
    }

    @Test
    @DisplayName("Should not validate Engineer with negative allowance")
    void should_not_validate_engineer_negative_allowance() {
        BigDecimal expectedMonthlyWage = BigDecimal.valueOf(0.08);
        BigDecimal expectedAllowance = BigDecimal.valueOf(-12.003);
        EngineerDTO engineerDTO = new EngineerDTO(
                expectedId,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate,
                expectedMonthlyWage,
                expectedAllowance
        );
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(engineerDTO);
        assertNotEquals(0, violations.size());
    }

    @Test
    @DisplayName("Should not validate Worker with negative rating")
    void should_not_validate_engineer_negative_rating() {
        BigDecimal expectedRating = BigDecimal.valueOf(-12.003);
        WorkerDTO workerDTO = new WorkerDTO(
                null,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate,
                expectedRating
        );
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(workerDTO);
        assertNotEquals(0, violations.size());
    }

    @Test
    @DisplayName("Should not validate Joined date with the date before DoB")
    void should_not_validate_Joined_date_with_the_date_before_DoB() {
        LocalDate expectedDob = LocalDate.of(1992, 9, 17);
        LocalDate expectedJoinedDate = LocalDate.of(1990, 9, 17);
        EmployeeDTO employeeDTO = new EmployeeDTO(
                expectedId,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate
        );
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(employeeDTO);
        assertNotEquals(0, violations.size());
    }

    @Test
    @DisplayName("Should not validate Joined date with the date before DoB")
    void should_not_validate_Joined_date_with_the_date_before_DoB_for_Worker() {
        BigDecimal expectedRating = BigDecimal.valueOf(0);
        LocalDate expectedDob = LocalDate.of(1992, 9, 17);
        LocalDate expectedJoinedDate = LocalDate.of(1990, 9, 17);
        WorkerDTO workerDTO = new WorkerDTO(
                expectedId,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate,
                expectedRating
        );
        Set<ConstraintViolation<WorkerDTO>> violations = validator.validate(workerDTO);
        assertNotEquals(0, violations.size());
    }

    @Test
    @DisplayName("Should not validate Joined date with the date before DoB")
    void should_not_validate_Joined_date_with_the_date_before_DoB_for_Engineer() {
        BigDecimal expectedMonthlyWage = BigDecimal.valueOf(0);
        BigDecimal expectedAllowance = BigDecimal.valueOf(0);
        LocalDate expectedDob = LocalDate.of(1992, 9, 17);
        LocalDate expectedJoinedDate = LocalDate.of(1990, 9, 17);
        EngineerDTO engineerDTO = new EngineerDTO(
                expectedId,
                expectedName,
                expectedGender,
                expectedDob,
                expectedJoinedDate,
                expectedMonthlyWage,
                expectedAllowance

        );
        Set<ConstraintViolation<EngineerDTO>> violations = validator.validate(engineerDTO);
        assertNotEquals(0, violations.size());
    }

    @Test
    @DisplayName("Should Not validate Employee with default constructor")
    void should_not_validate_employee() {
        Class<EmployeeDTO> clazz = EmployeeDTO.class;
        assertThrows(InstantiationException.class, () -> {
            EmployeeDTO employeeDTO = clazz.newInstance();
            assertNull(employeeDTO);
        });
    }

    @Test
    @DisplayName("Should Not validate Engineer with default constructor")
    void should_not_validate_engineer() {
        Class<EngineerDTO> clazz = EngineerDTO.class;
        assertThrows(InstantiationException.class, () -> {
            EngineerDTO engineerDTO = clazz.newInstance();
            assertNull(engineerDTO);
        });
    }

    @Test
    @DisplayName("Should Not validate Worker with default constructor")
    void should_not_validate_worker() {
        Class<WorkerDTO> clazz = WorkerDTO.class;
        assertThrows(InstantiationException.class, () -> {
            WorkerDTO workerDTO = clazz.newInstance();
            assertNull(workerDTO);
        });
    }

}
