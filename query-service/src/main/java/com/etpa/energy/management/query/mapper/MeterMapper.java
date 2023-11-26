package com.etpa.energy.management.query.mapper;

import com.etpa.energy.management.entity.Meter;
import com.etpa.energy.management.model.MeterDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeterMapper {
  MeterDto mapMeterToPostDto(Meter meters);
  List<MeterDto> mapMeterToPostDtoList(List<Meter> meters);
}
