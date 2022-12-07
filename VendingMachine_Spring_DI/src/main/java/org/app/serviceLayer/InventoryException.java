package org.app.serviceLayer;

public class InventoryException extends Exception{
    public InventoryException(String message) {
        super(message);
    }

    public InventoryException(String message, Throwable cause) {
        super(message,cause);
    }
}
