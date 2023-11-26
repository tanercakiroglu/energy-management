package com.etpa.energy.management.query.controller.impl;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.etpa.energy.management.enumaration.Month;
import com.etpa.energy.management.model.MonthRatioDto;
import com.etpa.energy.management.model.ProfilePostDto;
import com.etpa.energy.management.query.service.ProfileService;
import com.etpa.test.AbstractControllerStandaloneTest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class ProfileControllerImplTest extends AbstractControllerStandaloneTest {

  @InjectMocks
  ProfileControllerImpl profileController;
  @MockBean
  ProfileService profileService;


  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  void getById_whenValidRequest_ReturnOk() throws Exception {

    when(profileService.getById(any())).thenReturn(getProfilePostDto());
    mockMvc.perform(MockMvcRequestBuilders.get("/profile/{id}", "abcd")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
        .andExpect(MockMvcResultMatchers.content().string(containsString("200")))
        .andExpect(MockMvcResultMatchers.content().string(containsString("""
            "status":200,"data":{"id":"abcd","profile":"A","values":[{"month":"JAN","ratio":1}]}""")));
  }

  @Test
  void getPaginated_whenValidRequest_ReturnOk() throws Exception {

    when(profileService.getAllByPaginated(any(), any())).thenReturn(
        new PageImpl<>(List.of(getProfilePostDto())));
    mockMvc.perform(MockMvcRequestBuilders.get("/profile/?limit=5&page=0")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
        .andExpect(MockMvcResultMatchers.content().string(containsString("200")))
        .andExpect(MockMvcResultMatchers.content().string(containsString("""
            "status":200,"data":[{"id":"abcd","profile":"A","values":[{"month":"JAN","ratio":1}]}],"count":1,"page":0,"limit":1,"totalCount":1,"totalPage":1""")));
  }


  private static ProfilePostDto getProfilePostDto() {
    ProfilePostDto profilePostDto = new ProfilePostDto();
    profilePostDto.setId("abcd");
    profilePostDto.setProfile("A");
    MonthRatioDto e1 = getMonthRatioDto();
    profilePostDto.setValues(Set.of(e1));
    return profilePostDto;
  }

  private static MonthRatioDto getMonthRatioDto() {
    MonthRatioDto e1 = new MonthRatioDto();
    e1.setMonth(Month.JAN);
    e1.setRatio(BigDecimal.ONE);
    return e1;
  }
}