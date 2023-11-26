package com.etpa.energy.management.command.mapper;

import com.etpa.energy.management.entity.Meter;
import com.etpa.energy.management.model.MeterDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeterMapper {

  Meter mapMeterPostDtoToEntity(MeterDto meterDto);

  List<MeterDto> mapMeterToPostDtoList(List<Meter> meters);
}
