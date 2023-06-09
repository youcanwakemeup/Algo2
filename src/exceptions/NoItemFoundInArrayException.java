package exceptions;

public class NoItemFoundInArrayException extends RuntimeException {
    public NoItemFoundInArrayException(String message) {
        super(message);
    }
}
