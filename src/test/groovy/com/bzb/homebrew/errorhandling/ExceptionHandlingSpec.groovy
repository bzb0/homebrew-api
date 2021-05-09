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
package com.bzb.homebrew.errorhandling

import feign.FeignException
import feign.Request
import feign.RequestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.context.request.NativeWebRequest
import org.zalando.problem.Problem
import org.zalando.problem.Status
import spock.lang.Specification

import java.nio.charset.Charset

class ExceptionHandlingSpec extends Specification {

  def sut = new ExceptionHandling()

  def "handleFeignException handles a FeignException and returns a ResponseEntity with ProblemDetails"() {
    given:
    def nativeRequestMock = Mock(NativeWebRequest)
    def feignException = new FeignException(500, "Internal Breweries API error.")
    def problemThrowable = Problem.builder()
        .withType(Problem.DEFAULT_TYPE)
        .withTitle("Bad Gateway")
        .withStatus(Status.BAD_GATEWAY)
        .withDetail(feignException.getMessage())
        .build()
    def expectedResponse = ResponseEntity.status(HttpStatus.BAD_GATEWAY)
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(problemThrowable)

    when:
    def result = sut.handleFeignException(feignException, nativeRequestMock)

    then:
    result.getHeaders().get("Content-Type") == [MediaType.APPLICATION_PROBLEM_JSON_VALUE]
    result.getStatusCode() == expectedResponse.getStatusCode()
    with(result.getBody()) {
      type == problemThrowable.getType()
      title == problemThrowable.getTitle()
      status == problemThrowable.getStatus()
      detail == problemThrowable.getDetail()
    }
  }

  def "handleFeignNotFoundException handles a FeignException.NotFound returns a ResponseEntity with ProblemDetails"() {
    given:
    def nativeRequestMock = Mock(NativeWebRequest)
    def request = Request.create(Request.HttpMethod.GET, "/breweries/id", Map.of(), Request.Body.create(""), new RequestTemplate())
    def feignException = new FeignException.NotFound("Resource not found.", request, new byte[0])
    def problemThrowable = Problem.builder()
        .withType(Problem.DEFAULT_TYPE)
        .withTitle("Not Found")
        .withStatus(Status.NOT_FOUND)
        .withDetail(feignException.getMessage())
        .build()
    def expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND)
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(problemThrowable)

    when:
    def result = sut.handleFeignNotFoundException(feignException, nativeRequestMock)

    then:
    result.getHeaders().get("Content-Type") == [MediaType.APPLICATION_PROBLEM_JSON_VALUE]
    result.getStatusCode() == expectedResponse.getStatusCode()
    with(result.getBody()) {
      type == problemThrowable.getType()
      title == problemThrowable.getTitle()
      status == problemThrowable.getStatus()
      detail == problemThrowable.getDetail()
    }
  }
}
