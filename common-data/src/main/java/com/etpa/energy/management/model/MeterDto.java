package com.etpa.energy.management.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.Set;
import lombok.Data;

@Data
public class MeterDto {

  @NotBlank(message = "etpa.command-service.create-profile-request.id-should-not-be-empty")
  private String meterId;
  @NotBlank(message = "etpa.command-service.create-profile-request.profile-should-not-be-empty")
  private String profile;
  @NotEmpty(message = "etpa.command-service.create-profile-request.month-array-should-not-be-empty")
  @Valid
  private Set<MonthMeterDto> values;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MeterDto meterDto = (MeterDto) o;
    return Objects.equals(meterId, meterDto.meterId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(meterId);
  }
}
