package com.itqgroup.service.api;

public enum SubmittedStatus {
  SUCCESS("success"),
  CONFLICT("conflict"),
  NOT_FOUND("not found");

  private String statusName;

  SubmittedStatus(String statusName) {
    this.statusName = statusName;
  }

  public String getStatusName() {
    return this.statusName;
  }

  @Override
  public String toString() {
    return this.getStatusName();
  }

  public static SubmittedStatus getEnum(String value) {
    for(SubmittedStatus v : values()) {
      if(v.getStatusName().equalsIgnoreCase(value)) return v;
    }
    throw new IllegalArgumentException();
  }
}
