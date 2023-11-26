package com.etpa.energy.management.command.controller.impl;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.etpa.test.AbstractControllerStandaloneTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class ProfileControllerImplTest extends AbstractControllerStandaloneTest {


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

}