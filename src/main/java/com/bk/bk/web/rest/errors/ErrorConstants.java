package com.bk.bk.web.rest.errors;

import java.net.URI;

public final class ErrorConstants {

  public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
  public static final String ERR_VALIDATION = "error.validation";
  public static final URI DEFAULT_TYPE = URI.create("about:blank");
  public static final String ERR_INTERNAL_SERVER = "error.internalServer";
  public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create("about:blank");
  public static final String ENTITY_NOT_FOUND_TYPE = "error.entityNotFound";

  private ErrorConstants() {
  }
}
