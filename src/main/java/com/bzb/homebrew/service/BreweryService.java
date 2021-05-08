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
package com.bzb.homebrew.service;

import com.bzb.homebrew.generated.openapi.model.Brewery;
import com.bzb.homebrew.generated.openapi.model.BreweryList;
import com.bzb.homebrew.mapper.BreweryMapper;
import com.bzb.homebrew.openbrewery.client.BreweryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides functionality for accessing the Open Brewery API.
 */
@Service
public class BreweryService {

  private static final Logger log = LoggerFactory.getLogger(BreweryService.class);

  private final BreweryClient breweryClient;
  private final BreweryMapper breweryMapper;

  @Autowired
  public BreweryService(BreweryClient breweryClient, BreweryMapper breweryMapper) {
    this.breweryClient = breweryClient;
    this.breweryMapper = breweryMapper;
  }

  /**
   * Fetches a list of breweries from the Open Brewery API and maps them to Brewery DTOs.
   *
   * @param name The name of breweries to be filtered.
   * @param city The city in which the breweries are located.
   * @param state The state in which the breweries are located.
   * @return List of breweries.
   */
  public BreweryList getBreweries(String name, String city, String state) {
    var breweries = breweryClient.getBreweries(state, city, name);
    log.info("Received {} breweries from the Brewery API.", breweries.size());
    return new BreweryList().breweries(breweryMapper.toBreweryList(breweries));
  }

  /**
   * Fetches the details of a brewery from the Open Brewery API and the result to a Brewery DTO.
   *
   * @param id The id of the brewery.
   * @return A brewery DTO.
   */
  public Brewery getBreweryDetails(Integer id) {
    return breweryMapper.toBrewery(breweryClient.getBreweryDetails(id));
  }
}