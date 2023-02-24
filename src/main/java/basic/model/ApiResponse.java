package basic.model;

public class ApiResponse <T>{
    public final static String ERROR = "-1";
    public static final String OK = "0";

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
