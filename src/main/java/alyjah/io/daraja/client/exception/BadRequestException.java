package alyjah.io.daraja.client.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
        System.out.println("Realm roles error " + cause.getMessage());
    }
}
