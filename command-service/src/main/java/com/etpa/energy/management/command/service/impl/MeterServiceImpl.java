package com.etpa.energy.management.command.service.impl;

import static java.util.stream.Collectors.toMap;

import com.etpa.energy.management.command.mapper.MeterMapper;
import com.etpa.energy.management.command.repository.MeterRepository;
import com.etpa.energy.management.command.service.MeterService;
import com.etpa.energy.management.command.service.ProfileService;
import com.etpa.energy.management.entity.Meter;
import com.etpa.energy.management.model.MeterDto;
import com.etpa.energy.management.model.MonthMeterDto;
import jakarta.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MeterServiceImpl implements MeterService {

  private final MeterRepository meterRepository;
  private final MeterMapper meterMapper;
  private final ProfileService profileService;
  private static final LinkedList<Integer> linkedList = new LinkedList<>(
      List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));

  @Override
  public List<MeterDto> createMeter(Set<MeterDto> meterDtoList) {

    List<String> profileList = getExistingProfiles(meterDtoList);
    List<Meter> toBeInserted = meterDtoList
        .parallelStream()
        .filter(meterDto -> this.isValid(meterDto, profileList))
        .map(meterMapper::mapMeterPostDtoToEntity)
        .toList();
    return meterMapper.mapMeterToPostDtoList(meterRepository.saveAll(toBeInserted));
  }

  @Override
  public List<MeterDto> updateMeter(Set<MeterDto> meterDtoList) {
    List<String> profileList = getExistingProfiles(meterDtoList);
    Map<String, MeterDto> map = meterDtoList
        .parallelStream()
        .filter(meterDto -> isValid(meterDto, profileList))
        .collect(toMap(MeterDto::getMeterId, Function.identity(),
            (meterDto1, meterDto2) -> {
              log.warn("duplicate key found! {} {}", meterDto1, meterDto2);
              return meterDto1;
            }));

    List<Meter> toBeUpdated = meterRepository.findAllById(map.keySet())
        .parallelStream()
        .map(meter -> map.get(meter.getMeterId()))
        .map(meterMapper::mapMeterPostDtoToEntity)
        .toList();
    return meterMapper.mapMeterToPostDtoList(meterRepository.saveAll(toBeUpdated));
  }

  @Override
  public String deleteProfile(String id) {
    getById(id);
    meterRepository.deleteById(id);
    return id;
  }

  private void getById(String id) {
    meterRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id));
  }

  private List<String> getExistingProfiles(Set<MeterDto> meterDtoList) {
    return profileService.getExistingProfileByProfiles(
        meterDtoList.stream()
            .map(MeterDto::getProfile)
            .collect(Collectors.toSet()));
  }

  private boolean isValid(MeterDto meterDto, List<String> profileList) {
    LinkedList<Integer> orderedList = meterDto.getValues()
        .stream()
        .sorted(Comparator.comparing(MonthMeterDto::getMeter))
        .map(x -> x.getMonth().getOrder())
        .collect(Collectors.toCollection(LinkedList::new));
    return linkedList.equals(orderedList) && profileList.contains(meterDto.getProfile());
  }
}
