package com.etpa.energy.management.query.service.impl;

import com.etpa.energy.management.entity.Profile;
import com.etpa.energy.management.model.ProfilePostDto;
import com.etpa.energy.management.query.mapper.ProfileMapper;
import com.etpa.energy.management.query.repository.ProfileRepository;
import com.etpa.energy.management.query.service.ProfileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

  private final ProfileRepository profileQueryRepository;
  private final ProfileMapper profileMapper;
  @Override
  public ProfilePostDto getById(String id) {
    Profile profile = profileQueryRepository.findById(id)
        .orElseThrow(()->new EntityNotFoundException(id));
    return profileMapper.mapEntityToPostDto(profile);
  }

  @Override
  public Page<ProfilePostDto> getAllByPaginated(Integer limit, Integer page) {
    Pageable paging = PageRequest.of(page, limit);
    return profileQueryRepository.findAll(paging).map(profileMapper::mapEntityToPostDto);
  }
}
