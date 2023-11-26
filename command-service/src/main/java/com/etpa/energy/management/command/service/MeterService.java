package com.etpa.energy.management.command.service;

import com.etpa.energy.management.model.MeterDto;
import java.util.List;
import java.util.Set;

public interface MeterService {
  List<MeterDto> createMeter(Set<MeterDto> request);

  List<MeterDto> updateMeter(Set<MeterDto> request);

  String deleteProfile(String id);
}
