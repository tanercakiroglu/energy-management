package com.etpa.energy.management.model;

import com.etpa.energy.management.enumaration.Month;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class MonthMeterDto {

  @NotNull
  private Month month;
  @NotNull
  private BigDecimal meter;
}
