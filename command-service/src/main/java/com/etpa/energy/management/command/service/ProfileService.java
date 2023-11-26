package com.etpa.energy.management.command.service;

import com.etpa.energy.management.model.ProfilePatchDto;
import com.etpa.energy.management.model.ProfilePostDto;
import java.util.List;
import java.util.Set;

public interface ProfileService {

  List<ProfilePostDto> createProfile(List<ProfilePostDto> profilePostDto);
  List<ProfilePatchDto> updateProfile(List<ProfilePatchDto> profilePatchDto);
  String deleteProfile(String id);
  List<String> getExistingProfileByProfiles(Set<String> profile);
}
