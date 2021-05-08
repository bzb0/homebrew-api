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
package com.bzb.homebrew.controller;

import com.bzb.homebrew.generated.openapi.api.BreweryApi;
import com.bzb.homebrew.generated.openapi.model.Brewery;
import com.bzb.homebrew.generated.openapi.model.BreweryList;
import com.bzb.homebrew.service.BreweryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * A REST controller for the 'breweries' resource.
 */
@RestController
public class BreweryController implements BreweryApi {

  private final BreweryService breweryService;

  @Autowired
  public BreweryController(BreweryService breweryService) {
    this.breweryService = breweryService;
  }

  @Override
  public ResponseEntity<BreweryList> getBreweries(String name, String city, String state) {
    return ResponseEntity.ok(breweryService.getBreweries(name, city, state));
  }

  @Override
  public ResponseEntity<Brewery> getBreweryDetails(Integer id) {
    return ResponseEntity.ok(breweryService.getBreweryDetails(id));
  }
}