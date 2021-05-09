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
package com.bzb.homebrew.errorhandling;

import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;

/**
 * Handles various exceptions by implementing the {@link ProblemHandling} advice trait and additionally provides exception handlers for {@link
 * FeignException}s.
 */
@ControllerAdvice
public class ExceptionHandling implements ProblemHandling {

  /**
   * Handles all {@link FeignException}s and converts them to JSON Problem responses.
   *
   * @param exception The exception thrown by the Feign client.
   * @param request   The original/native request.
   * @return A HTTP response with JSON Problem as body.
   */
  @ExceptionHandler
  ResponseEntity<Problem> handleFeignException(final FeignException exception, final NativeWebRequest request) {
    return create(Status.BAD_GATEWAY, exception, request);
  }

  /**
   * Handles {@link FeignException.NotFound}s and converts them to JSON Problem responses.
   *
   * @param exception The exception thrown by the Feign client.
   * @param request   The original/native request.
   * @return A HTTP response with JSON Problem as body.
   */
  @ExceptionHandler
  ResponseEntity<Problem> handleFeignNotFoundException(final FeignException.NotFound exception, final NativeWebRequest request) {
    return create(Status.NOT_FOUND, exception, request);
  }
}