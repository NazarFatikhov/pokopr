package de.helpernet.pokopr.exception;

public class TeamDriveClientException extends RuntimeException {

    public TeamDriveClientException() {
    }

    public TeamDriveClientException(String message) {
        super(message);
    }

    public TeamDriveClientException(Throwable cause) {
        super(cause);
    }
}
