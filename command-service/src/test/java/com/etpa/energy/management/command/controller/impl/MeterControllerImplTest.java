package com.etpa.energy.management.command.controller.impl;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.etpa.energy.management.command.repository.MeterRepository;
import com.etpa.energy.management.command.service.ProfileService;
import com.etpa.energy.management.entity.Meter;
import com.etpa.test.AbstractControllerStandaloneTest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class MeterControllerImplTest extends AbstractControllerStandaloneTest {

  @MockBean
  ProfileService profileService;
  @MockBean
  MeterRepository meterRepository;

  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  void create_whenValidRequest_ReturnOk() throws Exception {
    when(profileService.getExistingProfileByProfiles(any())).thenReturn(List.of("C"));
    List<Meter> inserted = new ObjectMapper().readValue(createResponse(),
        new TypeReference<>() {
        });
    when(meterRepository.saveAll(any())).thenReturn(inserted);
    mockMvc.perform(MockMvcRequestBuilders.post("/meter/")
            .content(createRequest())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
        .andExpect(MockMvcResultMatchers.content().string(containsString("200")))
        .andExpect(MockMvcResultMatchers.content().string(containsString("""
            {"status":200,"data":[{"meterId":"0005","profile":"C",""")));
  }

  @Test
  void update_whenValidRequest_ReturnOk() throws Exception {
    when(profileService.getExistingProfileByProfiles(any())).thenReturn(List.of("C"));
    List<Meter> inserted = new ObjectMapper().readValue(createResponse(),
        new TypeReference<>() {
        });
    when(meterRepository.findAllById(any())).thenReturn(inserted);
    when(meterRepository.saveAll(any())).thenReturn(inserted);
    mockMvc.perform(MockMvcRequestBuilders.patch("/meter/")
            .content(createRequest())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
        .andExpect(MockMvcResultMatchers.content().string(containsString("200")))
        .andExpect(MockMvcResultMatchers.content().string(containsString("""
            {"status":200,"data":[{"meterId":"0005","profile":"C",""")));
  }

  @Test
  void create_whenInvalidRequest_BadRequestInvalidMeterId() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.post("/meter/")
            .content(invalidCreateRequest())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
        .andExpect(MockMvcResultMatchers.content().string(containsString("""
            "errorMessage":["createMeter.request[].meterId Id should not be empty."]""")));
  }


  private String createRequest() {
    return """
        [
            {
                "meterId": "00010",
                "profile": "G",
                "values": [
                    {
                        "month": "JAN",
                        "meter": 1
                    },
                    {
                        "month": "FEB",
                        "meter": 2
                    },
                    {
                        "month": "MAR",
                        "meter": 3
                    },
                    {
                        "month": "APR",
                        "meter": 4
                    },
                    {
                        "month": "MAY",
                        "meter": 5
                    },
                    {
                        "month": "JUN",
                        "meter": 6
                    },
                    {
                        "month": "JUL",
                        "meter": 7
                    },
                    {
                        "month": "AUG",
                        "meter": 8
                    },
                    {
                        "month": "SEP",
                        "meter": 9
                    },
                    {
                        "month": "OCT",
                        "meter": 10
                    },
                    {
                        "month": "NOV",
                        "meter": 11
                    },
                    {
                        "month": "DEC",
                        "meter": 12
                    }
                ]
            },
            {
                "meterId": "0005",
                "profile": "C",
                "values": [
                    {
                        "month": "JAN",
                        "meter": 10
                    },
                    {
                        "month": "FEB",
                        "meter": 20
                    },
                    {
                        "month": "MAR",
                        "meter": 30
                    },
                    {
                        "month": "APR",
                        "meter": 40
                    },
                    {
                        "month": "MAY",
                        "meter": 44
                    },
                    {
                        "month": "JUN",
                        "meter": 50
                    },
                    {
                        "month": "JUL",
                        "meter": 55
                    },
                    {
                        "month": "AUG",
                        "meter": 57
                    },
                    {
                        "month": "SEP",
                        "meter": 60
                    },
                    {
                        "month": "OCT",
                        "meter": 70
                    },
                    {
                        "month": "NOV",
                        "meter": 80
                    },
                    {
                        "month": "DEC",
                        "meter": 90
                    }
                ]
            },
            {
                "meterId": "0002",
                "profile": "D",
                "values": [
                    {
                        "month": "JAN",
                        "meter": 2
                    },
                    {
                        "month": "FEB",
                        "meter": 3
                    },
                    {
                        "month": "MAR",
                        "meter": 1
                    },
                    {
                        "month": "APR",
                        "meter": 6
                    },
                    {
                        "month": "MAY",
                        "meter": 3
                    },
                    {
                        "month": "JUN",
                        "meter": 4
                    },
                    {
                        "month": "JUL",
                        "meter": 5
                    },
                    {
                        "month": "AUG",
                        "meter": 5
                    },
                    {
                        "month": "SEP",
                        "meter": 5
                    },
                    {
                        "month": "OCT",
                        "meter": 6
                    },
                    {
                        "month": "NOV",
                        "meter":8
                    },
                    {
                        "month": "DEC",
                        "meter": 9
                    }
                ]
            }
        ]
                
        """;
  }

  private String createResponse() {
    return """
             [
                     {
                         "meterId": "0005",
                         "profile": "C",
                         "values": [
                             {
                                 "month": "JUL",
                                 "meter": 55
                             },
                             {
                                 "month": "SEP",
                                 "meter": 60
                             },
                             {
                                 "month": "DEC",
                                 "meter": 90
                             },
                             {
                                 "month": "JUN",
                                 "meter": 50
                             },
                             {
                                 "month": "MAR",
                                 "meter": 30
                             },
                             {
                                 "month": "NOV",
                                 "meter": 80
                             },
                             {
                                 "month": "APR",
                                 "meter": 40
                             },
                             {
                                 "month": "OCT",
                                 "meter": 70
                             },
                             {
                                 "month": "FEB",
                                 "meter": 20
                             },
                             {
                                 "month": "AUG",
                                 "meter": 57
                             },
                             {
                                 "month": "JAN",
                                 "meter": 10
                             },
                             {
                                 "month": "MAY",
                                 "meter": 44
                             }
                         ]
                     }
                 ]
        """;
  }

  private String invalidCreateRequest() {
    return """
        [
        {
                
                "profile": "G",
                "values": [
                    {
                        "month": "JAN",
                        "meter": 1
                    },
                    {
                        "month": "FEB",
                        "meter": 2
                    },
                    {
                        "month": "MAR",
                        "meter": 3
                    },
                    {
                        "month": "APR",
                        "meter": 4
                    },
                    {
                        "month": "MAY",
                        "meter": 5
                    },
                    {
                        "month": "JUN",
                        "meter": 6
                    },
                    {
                        "month": "JUL",
                        "meter": 7
                    },
                    {
                        "month": "AUG",
                        "meter": 8
                    },
                    {
                        "month": "SEP",
                        "meter": 9
                    },
                    {
                        "month": "OCT",
                        "meter": 10
                    },
                    {
                        "month": "NOV",
                        "meter": 11
                    },
                    {
                        "month": "DEC",
                        "meter": 12
                    }
                ]
            }
            ]
        """;
  }
}