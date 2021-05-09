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
package com.bzb.homebrew.mapper

import com.bzb.homebrew.generated.openapi.model.Address
import com.bzb.homebrew.generated.openapi.model.Brewery
import com.bzb.homebrew.generated.openapi.model.BreweryType
import spock.lang.Specification

class BreweryMapperSpec extends Specification {

  def breweryTypeMapperMock = Mock(BreweryTypeMapper)
  def sut = new BreweryMapperImpl(breweryTypeMapper: breweryTypeMapperMock)

  def "toBrewery maps an Brewery object from the Open Brewery API to a Brewery DTO"() {
    given:
    def id = 1005
    def name = "Some Brewery"
    def street = "1 Street"
    def postalCode = "101 AB"
    def city = "San Francisco"
    def state = "California"
    def longitude = "51.3162018"
    def latitude = "15.6568681"
    def apiBrewery = com.bzb.homebrew.openbrewery.client.Brewery.builder()
        .id(id)
        .name(name)
        .street(street)
        .postal_code(postalCode)
        .brewery_type(" ")
        .city(city)
        .state(state)
        .longitude(longitude)
        .latitude(latitude)
        .build()
    def expectedAddress = new Address(street: street, city: city, state: state, postcode: postalCode)
    def expectedBrewery = new Brewery(id: id, name: name, type: BreweryType.UNKNOWN, address: expectedAddress,
        longitude: new BigDecimal(longitude), latitude: new BigDecimal(latitude))

    when:
    def result = sut.toBrewery(apiBrewery)

    then:
    1 * breweryTypeMapperMock.toBreweryType(_ as String) >> BreweryType.UNKNOWN
    result == expectedBrewery
  }

  def "toBreweryList maps a list of brewery objects from the Open Brewery API to a list of Brewery DTOs"() {
    given:
    def apiBreweryList = [com.bzb.homebrew.openbrewery.client.Brewery.builder().id(0).name("").street("").postal_code("")
                              .brewery_type("").city("").state("").longitude("0").latitude("0").build()]
    def expectedBreweries = [new Brewery(id: 0, name: "", address: new Address(street: "", city: "", state: "", postcode: ""),
        type: BreweryType.UNKNOWN, longitude: BigDecimal.ZERO, latitude: BigDecimal.ZERO)]

    when:
    def result = sut.toBreweryList(apiBreweryList)

    then:
    1 * breweryTypeMapperMock.toBreweryType(_ as String) >> BreweryType.UNKNOWN
    result == expectedBreweries
  }

  def "toBrewery handles null values"() {
    expect:
    sut.toBrewery(null) == null
  }

  def "toBreweryList handles null values"() {
    expect:
    sut.toBreweryList(null) == null
  }
}