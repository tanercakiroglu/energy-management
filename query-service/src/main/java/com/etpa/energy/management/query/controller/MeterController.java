package com.etpa.energy.management.query.controller;

import com.etpa.energy.management.config.response.WrapperCollectionResponse;
import com.etpa.energy.management.config.response.WrapperResponse;
import com.etpa.energy.management.model.MeterDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/meter")
public interface MeterController {

  @GetMapping("/{meterId}")
  WrapperResponse<MeterDto> getById(@PathVariable("meterId") String meterId);

  @GetMapping("/")
  WrapperCollectionResponse<MeterDto> getPaginatedList(
      @RequestParam(defaultValue = "5") Integer limit,
      @RequestParam(defaultValue = "0") Integer page);

  @GetMapping("/{meterId}/consumption")
  WrapperResponse<BigDecimal> getConsumptionByMeterIdAndMonth(
      @PathVariable("meterId") String meterId,
      @RequestParam(value = "month",defaultValue = "0") @Min(0) @Max(10) Integer month);
}
