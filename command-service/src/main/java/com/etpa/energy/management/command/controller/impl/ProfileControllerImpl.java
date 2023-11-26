package com.etpa.energy.management.command.controller.impl;

import com.etpa.energy.management.command.controller.ProfileController;
import com.etpa.energy.management.command.service.ProfileService;
import com.etpa.energy.management.config.Loggable;
import com.etpa.energy.management.config.response.WrapperCollectionResponse;
import com.etpa.energy.management.config.response.WrapperResponse;
import com.etpa.energy.management.model.ProfilePatchDto;
import com.etpa.energy.management.model.ProfilePostDto;
import java.util.List;
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
  public WrapperCollectionResponse<ProfilePostDto> createProfile(
      List<ProfilePostDto> request) {
    return WrapperCollectionResponse.of(profileService.createProfile(request));
  }

  @Override
  public WrapperCollectionResponse<ProfilePatchDto> updateProfile(
      List<ProfilePatchDto> request) {
    return WrapperCollectionResponse.of(profileService.updateProfile(request));
  }

  @Override
  public WrapperResponse<String> deleteProfile(String id) {
    return WrapperResponse.of(profileService.deleteProfile(id));
  }
}
