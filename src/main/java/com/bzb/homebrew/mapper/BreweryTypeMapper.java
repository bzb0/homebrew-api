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
package com.bzb.homebrew.mapper;

import com.bzb.homebrew.generated.openapi.model.BreweryType;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

/**
 * Maps a string representation of a brewery type to a {@link BreweryType}.
 */
@Mapper(componentModel = "spring")
public class BreweryTypeMapper {

  static final String BREWERY_TYPE_MAPPER = "breweryType";

  @Named(BREWERY_TYPE_MAPPER)
  public BreweryType toBreweryType(String breweryType) {
    if(breweryType == null) {
      return BreweryType.UNKNOWN;
    }
    try {
      return BreweryType.fromValue(breweryType.trim().toUpperCase());
    } catch (IllegalArgumentException e) {
      return BreweryType.UNKNOWN;
    }
  }
}
