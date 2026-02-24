package com.itqgroup.service.api;

public enum ApprovedStatus {
  SUCCESS("success"),
  CONFLICT("conflict"),
  NOT_FOUND("not found"),
  REGISTRATION_ERROR("registry registration error");

  private String statusName;

  ApprovedStatus(String statusName) {
    this.statusName = statusName;
  }

  public String getStatusName() {
    return this.statusName;
  }

  @Override
  public String toString() {
    return this.getStatusName();
  }

  public static ApprovedStatus getEnum(String value) {
    for(ApprovedStatus v : values()) {
      if(v.getStatusName().equalsIgnoreCase(value)) return v;
    }
    throw new IllegalArgumentException();
  }
}
