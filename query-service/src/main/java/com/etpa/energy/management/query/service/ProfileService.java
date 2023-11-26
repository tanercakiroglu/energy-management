package com.etpa.energy.management.query.service;

import com.etpa.energy.management.model.ProfilePostDto;
import org.springframework.data.domain.Page;

public interface ProfileService {

  ProfilePostDto getById(String id);

  Page<ProfilePostDto> getAllByPaginated(Integer limit, Integer page);
}
