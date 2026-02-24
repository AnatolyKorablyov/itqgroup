package com.itqgroup.service.api;

public enum Action {
  SUBMIT("submit"),
  APPROVE("approve");

  private String statusName;

  Action(String statusName) {
    this.statusName = statusName;
  }

  public String getStatusName() {
    return this.statusName;
  }

  @Override
  public String toString() {
    return this.getStatusName();
  }

  public static Action getEnum(String value) {
    for(Action v : values()) {
      if(v.getStatusName().equalsIgnoreCase(value)) return v;
    }
    throw new IllegalArgumentException();
  }
}
