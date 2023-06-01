package co.verisoft.fw.errors;

public class SoftAssertionError extends Error {

    public SoftAssertionError(String message) {
        super(message);
    }
}