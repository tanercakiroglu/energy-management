package com.etpa.energy.management.query.service.impl;

import com.etpa.energy.management.entity.Meter;
import com.etpa.energy.management.entity.MonthMeterValues;
import com.etpa.energy.management.enumaration.Month;
import com.etpa.energy.management.model.MeterDto;
import com.etpa.energy.management.query.mapper.MeterMapper;
import com.etpa.energy.management.query.repository.MeterRepository;
import com.etpa.energy.management.query.service.MeterService;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeterServiceImpl implements MeterService {

  private final MeterRepository meterRepository;
  private final MeterMapper meterMapper;

  @Override
  public MeterDto getById(String meterId) {
    Meter meter = meterRepository.findByMeterId(meterId)
        .orElseThrow(() -> new EntityNotFoundException(meterId));
    return meterMapper.mapMeterToPostDto(meter);
  }

  @Override
  public Page<MeterDto> getAllByPaginated(Integer limit, Integer page) {
    Pageable paging = PageRequest.of(page, limit);
    return meterRepository.findAll(paging).map(meterMapper::mapMeterToPostDto);
  }

  @Override
  public BigDecimal retrieveConsumption(String meterId, Integer orderJavaCalender) {
    Meter meter = meterRepository.findByMeterId(meterId)
        .orElseThrow(() -> new EntityNotFoundException(meterId));
    List<Month> requestedMonths = Month.findMonthToBeCalculatedByOrder(orderJavaCalender);
    return meter.getValues()
        .stream()
        .filter(monthMeterValues -> requestedMonths.contains(monthMeterValues.getMonth()))
        .map(MonthMeterValues::getMeter)
        .reduce((first, second) -> BigDecimal.valueOf(Math.abs(first.subtract(second).intValue())))
        .orElse(BigDecimal.ZERO);
  }
}
