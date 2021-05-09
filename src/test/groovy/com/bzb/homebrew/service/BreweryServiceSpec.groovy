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
package com.bzb.homebrew.service

import com.bzb.homebrew.generated.openapi.model.Brewery
import com.bzb.homebrew.generated.openapi.model.BreweryList
import com.bzb.homebrew.mapper.BreweryMapper
import com.bzb.homebrew.openbrewery.client.BreweryClient
import spock.lang.Specification

class BreweryServiceSpec extends Specification {

  def breweryClientMock = Mock(BreweryClient)
  def breweryMapperMock = Mock(BreweryMapper)
  def sut = new BreweryService(breweryClientMock, breweryMapperMock)

  def "BreweryService constructor initializes BreweryService correctly"() {
    when:
    def result = new BreweryService(breweryClientMock, breweryMapperMock)

    then:
    result.breweryClient == breweryClientMock
    result.breweryMapper == breweryMapperMock
  }

  def "getBreweries returns a list of breweries"() {
    given:
    def breweries = [com.bzb.homebrew.openbrewery.client.Brewery.builder().build(), com.bzb.homebrew.openbrewery.client.Brewery.builder().build()]
    def mappedBreweries = [new Brewery(), new Brewery()]
    def expectedBreweryList = new BreweryList().breweries(mappedBreweries)

    when:
    def result = sut.getBreweries(_ as String, _ as String, _ as String)

    then:
    1 * breweryClientMock.getBreweries(_ as String, _ as String, _ as String) >> breweries
    1 * breweryMapperMock.toBreweryList(breweries) >> mappedBreweries
    result == expectedBreweryList
  }

  def "getBreweryDetails returns a the details of a brewery"() {
    given:
    def breweryId = 1005
    def apiBrewery = com.bzb.homebrew.openbrewery.client.Brewery.builder().build()
    def expectedBrewery = new Brewery()

    when:
    def result = sut.getBreweryDetails(breweryId)

    then:
    1 * breweryClientMock.getBreweryDetails(breweryId) >> apiBrewery
    1 * breweryMapperMock.toBrewery(apiBrewery) >> expectedBrewery
    result == expectedBrewery
  }
}