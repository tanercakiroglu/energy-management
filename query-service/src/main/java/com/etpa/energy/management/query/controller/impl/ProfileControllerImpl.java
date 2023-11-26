package com.etpa.energy.management.query.controller.impl;


import com.etpa.energy.management.config.Loggable;
import com.etpa.energy.management.config.response.WrapperCollectionResponse;
import com.etpa.energy.management.config.response.WrapperResponse;
import com.etpa.energy.management.model.ProfilePostDto;
import com.etpa.energy.management.query.controller.ProfileController;
import com.etpa.energy.management.query.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Validated
@Loggable
@RequiredArgsConstructor
@RestController
public class ProfileControllerImpl implements ProfileController {

  private final ProfileService profileService;

  @Override
  public WrapperResponse<ProfilePostDto> getById(String id) {
    return WrapperResponse.of(profileService.getById(id));
  }

  @Override
  public WrapperCollectionResponse<ProfilePostDto> getPaginatedList(Integer limit, Integer page) {
    return WrapperCollectionResponse.of(profileService.getAllByPaginated(limit,page));
  }
}
