/*
 * Copyright 2017-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bzb.homebrew.openbrewery.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign client for accessing the Open Brewery API.
 */
@FeignClient(name = "BreweryClient", url = "https://api.openbrewerydb.org/")
public interface BreweryClient {

  /**
   * Returns a list of breweries from the Open Brewery API.
   *
   * @param state Filters the breweries by the state they are located in.
   * @param city  Filters the breweries by the city they are located in.
   * @param name  Filters the breweries by their name.
   * @return List of breweries.
   */
  @GetMapping("/breweries")
  List<Brewery> getBreweries(
      @RequestParam(required = false, name = "by_state") String state,
      @RequestParam(required = false, name = "by_city") String city,
      @RequestParam(required = false, name = "by_name") String name);

  /**
   * Returns the details of a brewery.
   *
   * @param id The id of the brewery.
   * @return The details of the brewery.
   */
  @GetMapping("/breweries/{id}")
  Brewery getBreweryDetails(@PathVariable Integer id);
}