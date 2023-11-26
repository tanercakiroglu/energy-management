package com.etpa.energy.management.command.controller;

import com.etpa.energy.management.config.response.WrapperCollectionResponse;
import com.etpa.energy.management.config.response.WrapperResponse;
import com.etpa.energy.management.model.ProfilePatchDto;
import com.etpa.energy.management.model.ProfilePostDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/profile")
public interface ProfileController {

  @PostMapping("/")
  WrapperCollectionResponse<ProfilePostDto> createProfile(
      @RequestBody @NotEmpty List<@Valid ProfilePostDto> request);

  @PatchMapping("/")
  WrapperCollectionResponse<ProfilePatchDto> updateProfile(
       @RequestBody @NotEmpty List<@Valid ProfilePatchDto> request);

  @DeleteMapping("/{id}")
  WrapperResponse<String> deleteProfile(@PathVariable("id") String id);
}
