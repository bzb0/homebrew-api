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
package com.bzb.homebrew.controller

import com.bzb.homebrew.generated.openapi.model.Brewery
import com.bzb.homebrew.generated.openapi.model.BreweryList
import com.bzb.homebrew.service.BreweryService
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class BreweryControllerSpec extends Specification {

  def breweryServiceMock = Mock(BreweryService)
  def sut = new BreweryController(breweryServiceMock)

  def "BreweryController constructor initializes BreweryController correctly"() {
    when:
    def result = new BreweryController(breweryServiceMock)

    then:
    result.breweryService == breweryServiceMock
  }

  def "getBreweries returns a HTTP 200 ResponseEntity with a BreweryList as body"() {
    given:
    def breweryList = new BreweryList().breweries([new Brewery(), new Brewery()])
    def expectedResponse = ResponseEntity.ok(breweryList)

    when:
    def result = sut.getBreweries(_ as String, _ as String, _ as String)

    then:
    1 * breweryServiceMock.getBreweries(_ as String, _ as String, _ as String) >> breweryList
    result == expectedResponse
  }

  def "getBreweryDetails returns a HTTP 200 ResponseEntity with brewery details"() {
    given:
    def breweryId = 1005
    def brewery = new Brewery()
    def expectedResponse = ResponseEntity.ok(brewery)

    when:
    def result = sut.getBreweryDetails(breweryId)

    then:
    1 * breweryServiceMock.getBreweryDetails(breweryId) >> brewery
    result == expectedResponse
  }
}