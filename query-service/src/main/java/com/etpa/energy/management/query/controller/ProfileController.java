package com.etpa.energy.management.query.controller;

import com.etpa.energy.management.config.response.WrapperCollectionResponse;
import com.etpa.energy.management.config.response.WrapperResponse;
import com.etpa.energy.management.model.ProfilePostDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/profile")
public interface ProfileController {

  @GetMapping("/{id}")
  WrapperResponse<ProfilePostDto> getById(@PathVariable("id") String id);

  @GetMapping("/")
  WrapperCollectionResponse<ProfilePostDto> getPaginatedList(
      @RequestParam(defaultValue = "5") Integer limit,
      @RequestParam(defaultValue = "0") Integer page);
}
