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

import spock.lang.Specification
import spock.lang.Unroll

import static com.bzb.homebrew.generated.openapi.model.BreweryType.*

class BreweryTypeMapperSpec extends Specification {

  def sut = new BreweryTypeMapper()

  @Unroll
  def "toBreweryType maps a string brewery type to BreweryType enumeration"() {
    when:
    def result = sut.toBreweryType(breweryType)

    then:
    result == expectedBreweryType

    where:
    breweryType  || expectedBreweryType
    null         || UNKNOWN
    ""           || UNKNOWN
    "\t\n  "     || UNKNOWN
    "unknown"    || UNKNOWN
    "new type "  || UNKNOWN
    "Micro"      || MICRO
    "nano"       || NANO
    "regional"   || REGIONAL
    "Brewpub"    || BREWPUB
    "large"      || LARGE
    "Planning"   || PLANNING
    "BAR"        || BAR
    "contract"   || CONTRACT
    "proprietor" || PROPRIETOR
    "closed"     || CLOSED
  }
}
