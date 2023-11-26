package com.etpa.energy.management.command.service.impl;


import static com.etpa.energy.management.command.util.Constant.ETPA_COMMAND_SERVICE_CREATE_PROFILE_MISSING_MONTH;
import static com.etpa.energy.management.command.util.Constant.ETPA_COMMAND_SERVICE_CREATE_PROFILE_REQUEST_SHOULD_CONTAIN_EVERY_MONTH_OF_YEAR;
import static com.etpa.energy.management.command.util.Constant.ETPA_COMMAND_SERVICE_CREATE_PROFILE_REQUEST_SHOULD_CONTAIN_VALUE_EVERY_MONTH_OF_YEAR;
import static com.etpa.energy.management.command.util.Constant.ETPA_COMMAND_SERVICE_CREATE_PROFILE_SUM_OF_RATIO_NOT_EQUAL_TO_ONE;
import static com.etpa.energy.management.enumaration.Month.APR;
import static com.etpa.energy.management.enumaration.Month.AUG;
import static com.etpa.energy.management.enumaration.Month.DEC;
import static com.etpa.energy.management.enumaration.Month.FEB;
import static com.etpa.energy.management.enumaration.Month.JAN;
import static com.etpa.energy.management.enumaration.Month.JUL;
import static com.etpa.energy.management.enumaration.Month.JUN;
import static com.etpa.energy.management.enumaration.Month.MAR;
import static com.etpa.energy.management.enumaration.Month.MAY;
import static com.etpa.energy.management.enumaration.Month.NOV;
import static com.etpa.energy.management.enumaration.Month.OCT;
import static com.etpa.energy.management.enumaration.Month.SEP;
import static java.util.stream.Collectors.toMap;

import com.etpa.energy.management.command.mapper.ProfileMapper;
import com.etpa.energy.management.command.repository.ProfileHolder;
import com.etpa.energy.management.command.repository.ProfileRepository;
import com.etpa.energy.management.command.service.ProfileService;
import com.etpa.energy.management.config.exception.BusinessException;
import com.etpa.energy.management.entity.Profile;
import com.etpa.energy.management.enumaration.Month;
import com.etpa.energy.management.model.MonthRatioDto;
import com.etpa.energy.management.model.ProfilePatchDto;
import com.etpa.energy.management.model.ProfilePostDto;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProfileServiceImpl implements ProfileService {

  private final ProfileRepository profileRepository;
  private final ProfileMapper profileMapper;

  private static final LinkedHashSet<Month> orderedSet = new LinkedHashSet<>() {{
    add(JAN);
    add(FEB);
    add(MAR);
    add(APR);
    add(MAY);
    add(JUN);
    add(JUL);
    add(AUG);
    add(SEP);
    add(OCT);
    add(NOV);
    add(DEC);
  }};

  @Override
  public List<ProfilePostDto> createProfile(List<ProfilePostDto> profilePostDtoList) {

    List<Profile> toBePersisted = profilePostDtoList
        .parallelStream()
        .filter(this::isValid)
        .map(profileMapper::mapPostDtoToEntity)
        .toList();

    return profileMapper.mapEntityToPostDtoList(profileRepository.saveAll(toBePersisted));

  }

  @Override
  public List<ProfilePatchDto> updateProfile(List<ProfilePatchDto> profilePatchDtoList) {
    Map<String, ProfilePatchDto> map = profilePatchDtoList
        .parallelStream()
        .filter(this::isValid)
        .collect(toMap(ProfilePatchDto::getId, Function.identity(),
            (profilePatch1, profilePatch2) -> {
              log.warn("duplicate key found! {} {}", profilePatch1, profilePatch2);
              return profilePatch1;
            }));

    List<Profile> toBeUpdated = profileRepository.findAllById(map.keySet())
        .parallelStream()
        .map(map::get)
        .map(profileMapper::mapPatchDtoToEntity)
        .toList();

    return profileMapper.mapEntityToPatchDtoList(profileRepository.saveAll(toBeUpdated));
  }

  @Override
  public String deleteProfile(String id) {
    getById(id);
    profileRepository.deleteById(id);
    return id;
  }

  @Override
  public List<String> getExistingProfileByProfiles(Set<String> profileList) {
    return profileRepository.findAllByProfileIn(profileList)
        .stream()
        .map(ProfileHolder::getProfile)
        .toList();
  }

  private void getById(String id) {
    profileRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id));
  }

  private boolean isValid(ProfilePostDto profilePostDto) {
    return isMonthValuePairValid(profilePostDto.getValues());
  }

  private boolean isValid(ProfilePatchDto profilePatchDto) {
    return isMonthValuePairValid(profilePatchDto.getValues());
  }

  private static boolean isMonthValuePairValid(Set<MonthRatioDto> monthRatioValues) {
    Set<Month> requestSetOfMonth = monthRatioValues.stream()
        .sorted(Comparator.comparing(x -> x.getMonth().getOrder()))
        .map(MonthRatioDto::getMonth)
        .collect(Collectors.toCollection(LinkedHashSet::new));
    if (requestSetOfMonth.size() < monthRatioValues.size()) {
      throw new BusinessException(ETPA_COMMAND_SERVICE_CREATE_PROFILE_MISSING_MONTH);
    }
    if (!requestSetOfMonth.equals(orderedSet)) {
      throw new BusinessException(
          ETPA_COMMAND_SERVICE_CREATE_PROFILE_REQUEST_SHOULD_CONTAIN_EVERY_MONTH_OF_YEAR);
    }
    if (monthRatioValues.stream()
        .map(MonthRatioDto::getRatio)
        .anyMatch(Objects::isNull)) {
      throw new BusinessException(
          ETPA_COMMAND_SERVICE_CREATE_PROFILE_REQUEST_SHOULD_CONTAIN_VALUE_EVERY_MONTH_OF_YEAR);
    }

    if (BigDecimal.ONE.compareTo(monthRatioValues.stream()
        .map(MonthRatioDto::getRatio)
        .reduce(BigDecimal.ZERO, BigDecimal::add)) != 0) {
      throw new BusinessException(
          ETPA_COMMAND_SERVICE_CREATE_PROFILE_SUM_OF_RATIO_NOT_EQUAL_TO_ONE);
    }
    return true;
  }


}
