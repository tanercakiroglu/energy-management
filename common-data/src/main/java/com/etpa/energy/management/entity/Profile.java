package com.etpa.energy.management.entity;

import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class Profile {

  @Id
  private String id;
  @Indexed(unique = true)
  private String profile;
  private List<MonthRatioValues> values;

}
