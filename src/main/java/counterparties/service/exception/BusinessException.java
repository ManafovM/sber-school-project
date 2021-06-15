package counterparties.service.exception;

public class BusinessException extends Exception {
    public BusinessException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
