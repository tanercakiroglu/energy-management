package com.etpa.energy.management.command.controller;

import com.etpa.energy.management.config.response.WrapperCollectionResponse;
import com.etpa.energy.management.config.response.WrapperResponse;
import com.etpa.energy.management.model.MeterDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/meter")
public interface MeterController {

  @PostMapping("/")
  WrapperCollectionResponse<MeterDto> createMeter(
      @NotEmpty @RequestBody Set<@Valid MeterDto> request);

  @PatchMapping("/")
  WrapperCollectionResponse<MeterDto> updateMeter(
      @NotEmpty @RequestBody Set<@Valid MeterDto> request);

  @DeleteMapping("/{id}")
  WrapperResponse<String> deleteProfile(@PathVariable("id") String id);
}
