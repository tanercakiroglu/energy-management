package com.etpa.energy.management.query.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.etpa.energy.management.entity.MonthRatioValues;
import com.etpa.energy.management.entity.Profile;
import com.etpa.energy.management.enumaration.Month;
import com.etpa.energy.management.model.ProfilePostDto;
import com.etpa.energy.management.query.mapper.ProfileMapper;
import com.etpa.energy.management.query.repository.ProfileRepository;
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
class ProfileServiceImplTest {
  @InjectMocks
  ProfileServiceImpl profileService;

  @Mock
  ProfileRepository profileRepository;

  @Spy
  ProfileMapper mapper;

  @Test
  void getById_whenValidInput_thenReturnMeterDto() {

    when(profileRepository.findById(any())).thenReturn(Optional.of(getProfile()));

    ProfilePostDto result = profileService.getById("abc");

    verify(profileRepository, times(1)).findById(any());
    verify(mapper, times(1)).mapEntityToPostDto(any());
    assertEquals(result, mapper.mapEntityToPostDto(getProfile()));
  }

  @Test
  void getPaginated_whenValidInput_thenReturnPaginatedMeterDto() {

    PageImpl<Profile> repoResult = new PageImpl<>(List.of(getProfile()));
    PageRequest pageable = PageRequest.of(0, 5);
    when(profileRepository.findAll(pageable)).thenReturn(repoResult);

    Page<ProfilePostDto> result = profileService.getAllByPaginated(5, 0);

    verify(profileRepository, times(1)).findAll(pageable);
    verify(mapper, times(1)).mapEntityToPostDto(any());
    assertEquals(result, repoResult.map(mapper::mapEntityToPostDto));
  }

  @Test
  void getById_whenInvalidInput_thenThrowEntityNotFoundException() {

    when(profileRepository.findById(any())).thenThrow(EntityNotFoundException.class);

    assertThrows(EntityNotFoundException.class, () -> profileService.getById("abc"));

    verify(profileRepository, times(1)).findById(any());
    verify(mapper, times(0)).mapEntityToPostDto(any());
  }

  private Profile getProfile() {
    Profile profile = new Profile();
    profile.setId("abcd");
    profile.setProfile("A");
    profile.setValues(List.of(getMonthRatioValue(Month.JAN, BigDecimal.valueOf(0.5)),
        getMonthRatioValue(Month.FEB,BigDecimal.valueOf(0.3))));
    return profile;
  }

  private static MonthRatioValues getMonthRatioValue(Month month,BigDecimal ratio) {
    MonthRatioValues e1 = new MonthRatioValues();
    e1.setMonth(month);
    e1.setRatio(ratio);
    return e1;
  }
}