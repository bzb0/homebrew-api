package com.optimizely.challenge.csv

import com.bzb.homebrew.HomeBrewApp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

@SpringBootTest(classes = HomeBrewApp)
@AutoConfigureMockMvc
@EnableAutoConfiguration
class BreweryControllerIntegrationSpec extends Specification {

  @Autowired
  private MockMvc mvc

  def "GET /breweries returns a HTTP 200 with a list of breweries"() {
    expect:
    mvc.perform(MockMvcRequestBuilders.get("/breweries"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.content().json(readJsonFile("src/integration-test/resources/breweries.json")))
  }

  def "GET /breweries/{id} returns a HTTP 200 with the details of a brewery"() {
    expect:
    mvc.perform(MockMvcRequestBuilders.get("/breweries/11039"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.content().json(readJsonFile("src/integration-test/resources/brewery-11039.json")))
  }

  def "GET /breweries/{id} with an alphanumeric ID returns a HTTP 400 with a JSON Problem"() {
    expect:
    mvc.perform(MockMvcRequestBuilders.get("/breweries/11039abc"))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
  }

  def "GET on an unknown resource returns a HTTP 404 with a JSON Problem"() {
    expect:
    mvc.perform(MockMvcRequestBuilders.get("/unknown-resource"))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.content().json(
            "{\"title\":\"Not Found\",\"status\":404,\"detail\":\"No handler found for GET /unknown-resource\"}"))
  }

  private String readJsonFile(String filePath) {
    def file = new File(filePath)
    return file.text
  }
}


