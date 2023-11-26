package com.etpa.energy.management.query.controller.impl;


import com.etpa.energy.management.config.Loggable;
import com.etpa.energy.management.config.response.WrapperCollectionResponse;
import com.etpa.energy.management.config.response.WrapperResponse;
import com.etpa.energy.management.model.MeterDto;
import com.etpa.energy.management.query.controller.MeterController;
import com.etpa.energy.management.query.service.MeterService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Loggable
@RequiredArgsConstructor
@RestController
@Validated
public class MeterControllerImpl implements MeterController {

  private final MeterService meterService;


  @Override
  public WrapperResponse<MeterDto> getById(String meterId) {
    return WrapperResponse.of(meterService.getById(meterId));
  }

  @Override
  public WrapperCollectionResponse<MeterDto> getPaginatedList(Integer limit, Integer page) {
    return WrapperCollectionResponse.of(meterService.getAllByPaginated(limit,page));
  }

  @Override
  public WrapperResponse<BigDecimal> getConsumptionByMeterIdAndMonth(String meterId, Integer month) {
    return WrapperResponse.of(meterService.retrieveConsumption(meterId,month));
  }
}
