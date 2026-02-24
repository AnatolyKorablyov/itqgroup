package com.itqgroup.service.api;

public enum Status {
  DRAFT("draft"),
  SUBMITTED("submitted"),
  APPROVED("approved");

  private String statusName;

  Status(String statusName) {
    this.statusName = statusName;
  }

  public String getStatusName() {
    return this.statusName;
  }

  @Override
  public String toString() {
    return this.getStatusName();
  }

  public static Status getEnum(String value) {
    for(Status v : values()) {
      if(v.getStatusName().equalsIgnoreCase(value)) return v;
    }
    throw new IllegalArgumentException();
  }
}
