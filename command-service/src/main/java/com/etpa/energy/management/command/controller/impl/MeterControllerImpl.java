package com.etpa.energy.management.command.controller.impl;

import com.etpa.energy.management.command.controller.MeterController;
import com.etpa.energy.management.command.service.MeterService;
import com.etpa.energy.management.config.Loggable;
import com.etpa.energy.management.config.response.WrapperCollectionResponse;
import com.etpa.energy.management.config.response.WrapperResponse;
import com.etpa.energy.management.model.MeterDto;
import java.util.Set;
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
  public WrapperCollectionResponse<MeterDto> createMeter(Set<MeterDto> request) {
    return WrapperCollectionResponse.of(meterService.createMeter(request));
  }

  @Override
  public WrapperCollectionResponse<MeterDto> updateMeter(Set<MeterDto> request) {
    return WrapperCollectionResponse.of(meterService.updateMeter(request));
  }

  @Override
  public WrapperResponse<String> deleteProfile(String id) {
    return WrapperResponse.of(meterService.deleteProfile(id));
  }


}
