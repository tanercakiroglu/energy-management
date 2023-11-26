package com.etpa.energy.management.command.mapper;

import com.etpa.energy.management.entity.Profile;
import com.etpa.energy.management.model.ProfilePatchDto;
import com.etpa.energy.management.model.ProfilePostDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

  @Mapping(target = "id", ignore = true)
  Profile mapPostDtoToEntity(ProfilePostDto profilePostDto);

  ProfilePostDto mapEntityToPostDto(Profile profile);

  Profile mapPatchDtoToEntity(ProfilePatchDto profilePatchDto);

  ProfilePatchDto mapEntityToPatchDto(Profile profile);

  List<ProfilePostDto> mapEntityToPostDtoList(List<Profile> profile);

  List<Profile> mapPatchDtoToEntityList(List<ProfilePatchDto> profilePatchDtoList);

  List<ProfilePatchDto> mapEntityToPatchDtoList(List<Profile> profiles);
}
