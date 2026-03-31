package com.interswitch.erp.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeCreateRequestDTO {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    private String phoneNumber;
    private String position;

    @Positive
    private BigDecimal salary;

    @Past
    private LocalDate hireDate;

    @NotNull
    private Long departmentId;
}
