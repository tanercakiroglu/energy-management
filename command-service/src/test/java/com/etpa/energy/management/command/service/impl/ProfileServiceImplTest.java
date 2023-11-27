package com.etpa.energy.management.command.service.impl;

import static com.etpa.energy.management.enumaration.Month.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.etpa.energy.management.command.mapper.ProfileMapper;
import com.etpa.energy.management.command.repository.ProfileRepository;
import com.etpa.energy.management.config.exception.BusinessException;
import com.etpa.energy.management.enumaration.Month;
import com.etpa.energy.management.model.MonthRatioDto;
import com.etpa.energy.management.model.ProfilePostDto;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {

  @InjectMocks
  ProfileServiceImpl profileService;

  @Mock
  ProfileRepository profileRepository;

  @Spy
  ProfileMapper mapper;


  @Test
  void create_whenInvalidInput_thenMissingMonthException() {

    assertThrows(BusinessException.class,()->
        profileService.createProfile(List.of(getProfilePostDtoMissingMonth())));
    verify(profileRepository, times(0)).findById(any());
    verify(mapper, times(0)).mapEntityToPostDto(any());

  }

  @Test
  void create_whenInvalidInput_thenMissingValueException() {

    assertThrows(BusinessException.class,()->
        profileService.createProfile(List.of(getProfilePostDtoMissingValue())));
    verify(profileRepository, times(0)).findById(any());
    verify(mapper, times(0)).mapEntityToPostDto(any());

  }

  @Test
  void create_whenInvalidInput_thenSumOfValueNotEqualToOne() {

    assertThrows(BusinessException.class,()->
        profileService.createProfile(List.of(getProfilePostDtoSumOfValueNotEqualToOne())));
    verify(profileRepository, times(0)).findById(any());
    verify(mapper, times(0)).mapEntityToPostDto(any());

  }


  private static ProfilePostDto getProfilePostDtoMissingValue() {
    ProfilePostDto profilePostDto = new ProfilePostDto();
    profilePostDto.setId("abcd");
    profilePostDto.setProfile("A");
    profilePostDto.setValues(Set.of(
        getMonthRatioDto(JAN, BigDecimal.valueOf(1)),
        getMonthRatioDto(FEB, BigDecimal.valueOf(1)),
        getMonthRatioDto(MAR, BigDecimal.valueOf(1)),
        getMonthRatioDto(APR, BigDecimal.valueOf(1)),
        getMonthRatioDto(MAY, BigDecimal.valueOf(1)),
        getMonthRatioDto(JUN, BigDecimal.valueOf(1)),
        getMonthRatioDto(JUL, BigDecimal.valueOf(1)),
        getMonthRatioDto(AUG, BigDecimal.valueOf(1)),
        getMonthRatioDto(SEP, BigDecimal.valueOf(1)),
        getMonthRatioDto(OCT, BigDecimal.valueOf(1)),
        getMonthRatioDto(NOV, BigDecimal.valueOf(1)),
        getMonthRatioDto(DEC, null)
    ));
    return profilePostDto;
  }

  private static ProfilePostDto getProfilePostDtoSumOfValueNotEqualToOne() {
    ProfilePostDto profilePostDto = new ProfilePostDto();
    profilePostDto.setId("abcd");
    profilePostDto.setProfile("A");
    profilePostDto.setValues(Set.of(
        getMonthRatioDto(JAN, BigDecimal.valueOf(1)),
        getMonthRatioDto(FEB, BigDecimal.valueOf(1)),
        getMonthRatioDto(MAR, BigDecimal.valueOf(1)),
        getMonthRatioDto(APR, BigDecimal.valueOf(1)),
        getMonthRatioDto(MAY, BigDecimal.valueOf(1)),
        getMonthRatioDto(JUN, BigDecimal.valueOf(1)),
        getMonthRatioDto(JUL, BigDecimal.valueOf(1)),
        getMonthRatioDto(AUG, BigDecimal.valueOf(1)),
        getMonthRatioDto(SEP, BigDecimal.valueOf(1)),
        getMonthRatioDto(OCT, BigDecimal.valueOf(1)),
        getMonthRatioDto(NOV, BigDecimal.valueOf(1)),
        getMonthRatioDto(DEC, BigDecimal.valueOf(1))
    ));
    return profilePostDto;
  }
  private static ProfilePostDto getProfilePostDtoMissingMonth() {
    ProfilePostDto profilePostDto = new ProfilePostDto();
    profilePostDto.setId("abcd");
    profilePostDto.setProfile("A");
    profilePostDto.setValues(Set.of(getMonthRatioDto(JAN, BigDecimal.valueOf(1))));
    return profilePostDto;
  }


  private static MonthRatioDto getMonthRatioDto(Month month, BigDecimal ratio) {
    MonthRatioDto e1 = new MonthRatioDto();
    e1.setMonth(month);
    e1.setRatio(ratio);
    return e1;
  }
}
