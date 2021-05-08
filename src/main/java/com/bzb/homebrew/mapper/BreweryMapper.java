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

import com.bzb.homebrew.openbrewery.client.Brewery;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Maps a {@link Brewery} object to a {@link com.bzb.homebrew.generated.openapi.model.Brewery} object.
 */
@Mapper(componentModel = "spring", uses = BreweryTypeMapper.class)
public interface BreweryMapper {

  @Mapping(target = "type", source = "brewery_type", qualifiedByName = BreweryTypeMapper.BREWERY_TYPE_MAPPER)
  @Mapping(target = "address.street", source = "street")
  @Mapping(target = "address.postcode", source = "postal_code")
  @Mapping(target = "address.city", source = "city")
  @Mapping(target = "address.state", source = "state")
  com.bzb.homebrew.generated.openapi.model.Brewery toBrewery(Brewery brewery);

  List<com.bzb.homebrew.generated.openapi.model.Brewery> toBreweryList(List<Brewery> breweryList);
}
