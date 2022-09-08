package ru.kuzmina.exceptions;

public class ApplicationError {
    private int statusCode;
    private int message;

    public int getStatusCode() {
        return statusCode;
    }

    public int getMessage() {
        return message;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public ApplicationError(int stsCode, int message) {
        this.statusCode = stsCode;
        this.message = message;
    }
}
