package com.etpa.energy.management.model;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;
import lombok.Data;

@Data
public class ProfilePatchDto {

  @NotBlank(message = "etpa.command-service.create-profile-request.id-should-not-be-empty")
  private String id;
  @NotBlank(message = "etpa.command-service.create-profile-request.profile-should-not-be-empty")
  private String profile;
  @NotEmpty(message = "etpa.command-service.create-profile-request.month-array-should-not-be-empty")
  @Valid
  private Set<MonthRatioDto> values;
}
