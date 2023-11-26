package com.etpa.energy.management.query.service;

import com.etpa.energy.management.model.MeterDto;
import java.math.BigDecimal;
import org.springframework.data.domain.Page;

public interface MeterService {

  MeterDto getById(String meterId);

  Page<MeterDto> getAllByPaginated(Integer limit, Integer page);

  BigDecimal retrieveConsumption(String meterId, Integer month);
}
