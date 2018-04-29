package kpardyka.error;

public class ErrorData {
    private String url;
    private final String message;

    public ErrorData(String message) {
        this.message = message;
    }

    public ErrorData(String url, String message) {
        this.url = url;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public String getMessage() {
        return message;
    }
}
