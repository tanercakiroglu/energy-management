package com.etpa.energy.management.entity;

import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("meter")
@Data
public class Meter {

  @Id
  private String meterId;
  @Indexed(unique = true)
  private String profile;
  private List<MonthMeterValues> values;
}
