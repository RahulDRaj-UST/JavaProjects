package com.wba.api.updatecustomer.constant;

/**
 * @author 165571
 * @href <mohammedanas.abdulrasheed@ust.com>
 * @createdon 2/16/2021
 */

public enum EapiErrorEnum {

  ERRROR500("Sorry! This com.wba.api.updatecustomer.service is temporarily unavailable. Please try again later."),
  ERRROR503("Kafka Service Temporarily down !!! Try again after some time..."),
  ERRROR400("Invalid/Bad Request.. Please ensure request body/params are in correct format.."),
  ERRROR401("Unauthorized request.. Please ensure your authentication details are correct..."),
  ERRROR405("Unauthorized request.. Invalid method call...");


  private String message;

  public String getMessage() {
    return message;
  }

  private EapiErrorEnum(String message) {
    this.message = message;
  }

}
