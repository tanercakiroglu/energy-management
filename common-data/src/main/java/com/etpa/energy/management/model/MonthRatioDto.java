package com.etpa.energy.management.model;

import com.etpa.energy.management.enumaration.Month;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.Data;

@Data
public class MonthRatioDto {

  @NotNull
  private Month month;
  @NotNull
  private BigDecimal ratio;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MonthRatioDto that = (MonthRatioDto) o;
    return month == that.month;
  }

  @Override
  public int hashCode() {
    return Objects.hash(month);
  }
}
