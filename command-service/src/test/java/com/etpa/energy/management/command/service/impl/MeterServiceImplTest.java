package com.etpa.energy.management.command.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.etpa.energy.management.command.mapper.MeterMapperImpl;
import com.etpa.energy.management.command.repository.MeterRepository;
import com.etpa.energy.management.entity.Meter;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MeterServiceImplTest {

  @InjectMocks
  MeterServiceImpl meterService;

  @Mock
  MeterRepository meterRepository;

  @Spy
  MeterMapperImpl mapper;

  @Test
  void deleteById_whenInvalidInput_thenThrowEntityNotFoundException() {

    when(meterRepository.findById(any())).thenThrow(EntityNotFoundException.class);

    assertThrows(EntityNotFoundException.class, () -> meterService.deleteProfile("abc"));

    verify(meterRepository, times(0)).deleteById(any());

  }

  @Test
  void deleteById_whenValidInput_thenReturnOk() {

    Meter meter = new Meter();
    meter.setMeterId("abc");

    when(meterRepository.findById(any())).thenReturn(Optional.of(meter));

    String result =meterService.deleteProfile("abc");
    verify(meterRepository, times(1)).deleteById(any());
    assertEquals(result,"abc");
  }

}