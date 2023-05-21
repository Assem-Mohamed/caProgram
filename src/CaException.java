public class CaException extends Exception {
    String message;
    CaException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
