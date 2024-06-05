package hu.acsgyorgy.tool.stock.master.domain.exceptions;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(String message) {
        super(message);
    }
}

