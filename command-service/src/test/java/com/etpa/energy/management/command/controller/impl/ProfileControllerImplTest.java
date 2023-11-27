package com.etpa.energy.management.command.controller.impl;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.etpa.energy.management.command.repository.ProfileRepository;
import com.etpa.energy.management.entity.Profile;
import com.etpa.test.AbstractControllerStandaloneTest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class ProfileControllerImplTest extends AbstractControllerStandaloneTest {

  @SpyBean
  ProfileRepository profileRepository;

  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  void create_whenValidRequest_ReturnOk() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/profile/")
            .content(createRequest())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
        .andExpect(MockMvcResultMatchers.content().string(containsString("""
            {"status":200,"data":""")))
        .andExpect(MockMvcResultMatchers.content().string(containsString("""
            "profile":"A",""")))
        .andExpect(MockMvcResultMatchers.content().string(containsString("""
            "profile":"B",""")));

  }

  @Test
  void update_whenValidRequest_ReturnOk() throws Exception {
    List<Profile> profileList = new ObjectMapper().readValue(updateRequest(),
        new TypeReference<>() {
        });

    Set<String> collect = profileList.stream().map(Profile::getId)
        .collect(Collectors.toSet());
    when(profileRepository.findAllById(collect)).thenReturn(profileList);
    mockMvc.perform(MockMvcRequestBuilders.patch("/profile/")
            .content(updateRequest())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
        .andExpect(MockMvcResultMatchers.content().string(containsString("""
            {"status":200,"data":""")))
        .andExpect(MockMvcResultMatchers.content().string(containsString("""
            "id":"6561fcc15950a22cb76d178a","profile":"C""")))
        .andExpect(MockMvcResultMatchers.content().string(containsString("""
            "id":"6561fcc15951a22cb76d178a","profile":"D""")));

  }

  @Test
  void create_whenInvalidRequest_BadRequestInvalidMeterId() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.post("/profile/")
            .content(invalidCreateRequest())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
        .andExpect(MockMvcResultMatchers.content().string(containsString("""
            "errorMessage":["createProfile.request[0].profile Profile should not be empty.""")));
  }

  private String invalidCreateRequest() {
    return """
        [
            {
               
                "values": [
                    {
                        "month": "MAY",
                        "ratio": 0.1
                    },
                      {
                        "month": "FEB",
                        "ratio": 0.2
                    },
                    {
                        "month": "SEP",
                        "ratio": 0.1
                    },
                    {
                        "month": "JAN",
                        "ratio": 0.2
                    },
                    {
                        "month": "MAR",
                        "ratio": 0.2
                    },
                    {
                        "month": "APR",
                        "ratio": 0.2
                    },
                    {
                        "month": "JUN",
                        "ratio": 0.0
                    },
                    {
                        "month": "JUL",
                        "ratio": 0.0
                    },
                    {
                        "month": "AUG",
                        "ratio": 0.0
                    },
                    {
                        "month": "OCT",
                        "ratio": 0.0
                    },
                    {
                        "month": "NOV",
                        "ratio": 0.0
                    },
                    {
                        "month": "DEC",
                        "ratio": 0.0
                    }
                ]
            },
            {
                "profile": "A",
                "values": [
                    {
                        "month": "JAN",
                        "ratio": 0.15
                    },
                    {
                        "month": "FEB",
                        "ratio": 0.2
                    },
                    {
                        "month": "MAR",
                        "ratio": 0.2
                    },
                    {
                        "month": "APR",
                        "ratio": 0.2
                    },
                    {
                        "month": "MAY",
                        "ratio": 0.0
                    },
                    {
                        "month": "JUN",
                        "ratio": 0.05
                    },
                    {
                        "month": "JUL",
                        "ratio": 0.2
                    },
                    {
                        "month": "AUG",
                        "ratio": 0.0
                    },
                    {
                        "month": "SEP",
                        "ratio": 0.0
                    },
                    {
                        "month": "OCT",
                        "ratio": 0.0
                    },
                    {
                        "month": "NOV",
                        "ratio": 0.0
                    },
                    {
                        "month": "DEC",
                        "ratio": 0.0
                    }
                ]
            }
        ]
        """;
  }

  private String createRequest() {
    return """
        [
            {
                "profile": "B",
                "values": [
                    {
                        "month": "MAY",
                        "ratio": 0.1
                    },
                      {
                        "month": "FEB",
                        "ratio": 0.2
                    },
                    {
                        "month": "SEP",
                        "ratio": 0.1
                    },
                    {
                        "month": "JAN",
                        "ratio": 0.2
                    },
                    {
                        "month": "MAR",
                        "ratio": 0.2
                    },
                    {
                        "month": "APR",
                        "ratio": 0.2
                    },
                    {
                        "month": "JUN",
                        "ratio": 0.0
                    },
                    {
                        "month": "JUL",
                        "ratio": 0.0
                    },
                    {
                        "month": "AUG",
                        "ratio": 0.0
                    },
                    {
                        "month": "OCT",
                        "ratio": 0.0
                    },
                    {
                        "month": "NOV",
                        "ratio": 0.0
                    },
                    {
                        "month": "DEC",
                        "ratio": 0.0
                    }
                ]
            },
            {
                "profile": "A",
                "values": [
                    {
                        "month": "JAN",
                        "ratio": 0.15
                    },
                    {
                        "month": "FEB",
                        "ratio": 0.2
                    },
                    {
                        "month": "MAR",
                        "ratio": 0.2
                    },
                    {
                        "month": "APR",
                        "ratio": 0.2
                    },
                    {
                        "month": "MAY",
                        "ratio": 0.0
                    },
                    {
                        "month": "JUN",
                        "ratio": 0.05
                    },
                    {
                        "month": "JUL",
                        "ratio": 0.2
                    },
                    {
                        "month": "AUG",
                        "ratio": 0.0
                    },
                    {
                        "month": "SEP",
                        "ratio": 0.0
                    },
                    {
                        "month": "OCT",
                        "ratio": 0.0
                    },
                    {
                        "month": "NOV",
                        "ratio": 0.0
                    },
                    {
                        "month": "DEC",
                        "ratio": 0.0
                    }
                ]
            }
        ]
        """;
  }

    private String updateRequest() {
      return """
        [
            {
                "id":"6561fcc15950a22cb76d178a",
                "profile": "C",
                "values": [
                    {
                        "month": "MAY",
                        "ratio": 0.1
                    },
                      {
                        "month": "FEB",
                        "ratio": 0.2
                    },
                    {
                        "month": "SEP",
                        "ratio": 0.1
                    },
                    {
                        "month": "JAN",
                        "ratio": 0.2
                    },
                    {
                        "month": "MAR",
                        "ratio": 0.2
                    },
                    {
                        "month": "APR",
                        "ratio": 0.2
                    },
                    {
                        "month": "JUN",
                        "ratio": 0.0
                    },
                    {
                        "month": "JUL",
                        "ratio": 0.0
                    },
                    {
                        "month": "AUG",
                        "ratio": 0.0
                    },
                    {
                        "month": "OCT",
                        "ratio": 0.0
                    },
                    {
                        "month": "NOV",
                        "ratio": 0.0
                    },
                    {
                        "month": "DEC",
                        "ratio": 0.0
                    }
                ]
            },
            {
                "id":"6561fcc15951a22cb76d178a",
                "profile": "D",
                "values": [
                    {
                        "month": "JAN",
                        "ratio": 0.15
                    },
                    {
                        "month": "FEB",
                        "ratio": 0.2
                    },
                    {
                        "month": "MAR",
                        "ratio": 0.2
                    },
                    {
                        "month": "APR",
                        "ratio": 0.2
                    },
                    {
                        "month": "MAY",
                        "ratio": 0.0
                    },
                    {
                        "month": "JUN",
                        "ratio": 0.05
                    },
                    {
                        "month": "JUL",
                        "ratio": 0.2
                    },
                    {
                        "month": "AUG",
                        "ratio": 0.0
                    },
                    {
                        "month": "SEP",
                        "ratio": 0.0
                    },
                    {
                        "month": "OCT",
                        "ratio": 0.0
                    },
                    {
                        "month": "NOV",
                        "ratio": 0.0
                    },
                    {
                        "month": "DEC",
                        "ratio": 0.0
                    }
                ]
            }
        ]
        """;
  }



}