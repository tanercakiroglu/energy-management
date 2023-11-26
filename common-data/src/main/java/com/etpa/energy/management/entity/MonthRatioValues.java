package com.etpa.energy.management.entity;

import com.etpa.energy.management.enumaration.Month;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class MonthRatioValues {

  private Month month;
  private BigDecimal ratio;

}
