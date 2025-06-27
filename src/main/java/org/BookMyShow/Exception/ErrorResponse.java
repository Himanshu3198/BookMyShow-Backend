package org.BookMyShow.Exception;

public class ErrorResponse {

    private String error;
    private String message;
    private String details;

    public ErrorResponse() {}

    public ErrorResponse(String error, String message, String details){
        this.error = error;
        this.message = message;
        this.details = details;
    }
// Getter and Setter
    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public ErrorResponse setError(String error) {
        this.error = error;
        return this;
    }

    public ErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public ErrorResponse setDetails(String details) {
        this.details = details;
        return this;
    }
}
