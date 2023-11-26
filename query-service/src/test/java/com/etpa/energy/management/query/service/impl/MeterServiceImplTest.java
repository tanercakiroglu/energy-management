package com.etpa.energy.management.query.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.etpa.energy.management.entity.Meter;
import com.etpa.energy.management.entity.MonthMeterValues;
import com.etpa.energy.management.enumaration.Month;
import com.etpa.energy.management.model.MeterDto;
import com.etpa.energy.management.query.mapper.MeterMapperImpl;
import com.etpa.energy.management.query.repository.MeterRepository;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
class MeterServiceImplTest {

  @InjectMocks
  MeterServiceImpl meterService;

  @Mock
  MeterRepository meterRepository;

  @Spy
  MeterMapperImpl mapper;


  @Test
  void getById_whenValidInput_thenReturnMeterDto() {

    when(meterRepository.findByMeterId(any())).thenReturn(Optional.of(getMeter()));

    MeterDto result = meterService.getById("abc");

    verify(meterRepository, times(1)).findByMeterId(any());
    verify(mapper, times(1)).mapMeterToPostDto(any());
    assertEquals(result, mapper.mapMeterToPostDto(getMeter()));
  }

  @Test
  void getPaginated_whenValidInput_thenReturnPaginatedMeterDto() {

    PageImpl<Meter> repoResult = new PageImpl<>(List.of(getMeter()));
    PageRequest pageable = PageRequest.of(0, 5);
    when(meterRepository.findAll(pageable)).thenReturn(
        repoResult);

    Page<MeterDto> result = meterService.getAllByPaginated(5, 0);

    verify(meterRepository, times(1)).findAll(pageable);
    verify(mapper, times(1)).mapMeterToPostDto(any());
    assertEquals(result, repoResult.map(mapper::mapMeterToPostDto));
  }

  @Test
  void getById_whenInvalidInput_thenThrowEntityNotFoundException() {

    when(meterRepository.findByMeterId(any())).thenThrow(EntityNotFoundException.class);

    assertThrows(EntityNotFoundException.class, () -> meterService.getById("abc"));

    verify(meterRepository, times(1)).findByMeterId(any());
    verify(mapper, times(0)).mapMeterToPostDto(any());
  }

  @Test
  void retrieveConsumption_whenValidInput_thenReturnConsumption() {

    when(meterRepository.findByMeterId(any())).thenReturn(Optional.of(getMeter()));

    BigDecimal result = meterService.retrieveConsumption("0002", 0);

    verify(meterRepository, times(1)).findByMeterId(any());
    verify(mapper, times(0)).mapMeterToPostDto(any());
    assertEquals(result,BigDecimal.valueOf(100));
  }

  private Meter getMeter() {
    Meter meter = new Meter();
    meter.setMeterId("0002");
    meter.setProfile("A");
    meter.setValues(List.of(getMonthMeterValue(Month.JAN,BigDecimal.valueOf(100)),
          getMonthMeterValue(Month.FEB,BigDecimal.valueOf(200))));
    return meter;
  }

  private static MonthMeterValues getMonthMeterValue(Month month,BigDecimal meter) {
    MonthMeterValues e1 = new MonthMeterValues();
    e1.setMonth(month);
    e1.setMeter(meter);
    return e1;
  }
}