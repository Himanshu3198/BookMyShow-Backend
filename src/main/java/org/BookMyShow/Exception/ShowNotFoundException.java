package org.BookMyShow.Exception;

public class ShowNotFoundException extends RuntimeException {
  public ShowNotFoundException(String message) {
    super(message);
  }
}
