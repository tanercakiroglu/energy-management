package com.etpa.energy.management.enumaration;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Month {

  JAN(1),
  FEB(2),
  MAR(3),
  APR(4),
  MAY(5),
  JUN(6),
  JUL(7),
  AUG(8),
  SEP(9),
  OCT(10),
  NOV(11),
  DEC(12);

  private final int order;

  public static List<Month> findMonthToBeCalculatedByOrder(final Integer orderJavaCalender){
    return Arrays.stream(values())
        .filter(value -> value.getOrder() == orderJavaCalender + 1 || value.getOrder() == orderJavaCalender + 2)
        .collect(Collectors.toList());
  }

}
