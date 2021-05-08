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

/**
 * Represents a state in the USA.
 */
public enum USState {

  AL("Alabama"), MT("Montana"), AK("Alaska"), NE("Nebraska"), AZ("Arizona"), NV("Nevada"), AR("Arkansas"),
  NH("New Hampshire"), CA("California"), NJ("New Jersey"), CO("Colorado"), NM("New Mexico"), CT("Connecticut"),
  NY("New York"), DE("Delaware"), NC("North Carolina"), FL("Florida"), ND("North Dakota"), GA("Georgia"),
  OH("Ohio"), HI("Hawaii"), OK("Oklahoma"), ID("Idaho"), OR("Oregon"), IL("Illinois"), PA("Pennsylvania"),
  IN("Indiana"), RI("Rhode Island"), IA("Iowa"), SC("South Carolina"), KS("Kansas"), SD("South Dakota"),
  KY("Kentucky"), TN("Tennessee"), LA("Louisiana"), TX("Texas"), ME("Maine"), UT("Utah"), MD("Maryland"),
  VT("Vermont"), MA("Massachusetts"), VA("Virginia"), MI("Michigan"), WA("Washington"), MN("Minnesota"),
  WV("West Virginia"), MS("Mississippi"), WI("Wisconsin"), MO("Missouri"), WY("Wyoming");

  private final String name;

  USState(String name) {
    this.name = name;
  }

  /**
   * Returns the name of state.
   *
   * @return The name of the state.
   */
  public String getName() {
    return name;
  }
}
