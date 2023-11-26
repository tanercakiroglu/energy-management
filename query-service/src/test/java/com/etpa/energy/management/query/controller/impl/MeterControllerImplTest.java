package com.etpa.energy.management.query.controller.impl;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.etpa.energy.management.enumaration.Month;
import com.etpa.energy.management.model.MeterDto;
import com.etpa.energy.management.model.MonthMeterDto;
import com.etpa.energy.management.query.service.MeterService;
import com.etpa.test.AbstractControllerStandaloneTest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


class MeterControllerImplTest extends AbstractControllerStandaloneTest {

  @MockBean
  MeterService meterService;


  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  void getById_whenValidRequest_ReturnOk() throws Exception {

    when(meterService.getById(any())).thenReturn(getMeterDto());
    mockMvc.perform(MockMvcRequestBuilders.get("/meter/{meterId}", "000001")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
        .andExpect(MockMvcResultMatchers.content().string(containsString("200")))
        .andExpect(MockMvcResultMatchers.content().string(containsString("""
            "status":200,"data":{"meterId":"000001","profile":"A","values":[{"month":"JAN","meter":1}]}""")));
  }

  @Test
  void getPaginated_whenValidRequest_ReturnOk() throws Exception {

    when(meterService.getAllByPaginated(any(), any())).thenReturn(
        new PageImpl<>(List.of(getMeterDto())));
    mockMvc.perform(MockMvcRequestBuilders.get("/meter/?limit=5&page=0")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
        .andExpect(MockMvcResultMatchers.content().string(containsString("200")))
        .andExpect(MockMvcResultMatchers.content().string(containsString("""
            "status":200,"data":[{"meterId":"000001","profile":"A","values":[{"month":"JAN","meter":1}]}],"count":1,"page":0,"limit":1,"totalCount":1,"totalPage":1""")));
  }

  @Test
  void retrieveConsumption_whenValidRequest_ReturnOk() throws Exception {

    when(meterService.retrieveConsumption(any(), any())).thenReturn(BigDecimal.valueOf(100));
    mockMvc.perform(MockMvcRequestBuilders.get("/meter/0005/consumption?month=0")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
        .andExpect(MockMvcResultMatchers.content().string(containsString("200")))
        .andExpect(MockMvcResultMatchers.content().string(containsString("""
            "status":200,"data":100""")));
  }



  private static MeterDto getMeterDto() {
    MeterDto meterDto = new MeterDto();
    meterDto.setMeterId("000001");
    meterDto.setProfile("A");
    MonthMeterDto e1 = getMonthMeterDto();
    meterDto.setValues(Set.of(e1));
    return meterDto;
  }

  private static MonthMeterDto getMonthMeterDto() {
    MonthMeterDto e1 = new MonthMeterDto();
    e1.setMonth(Month.JAN);
    e1.setMeter(BigDecimal.ONE);
    return e1;
  }

}