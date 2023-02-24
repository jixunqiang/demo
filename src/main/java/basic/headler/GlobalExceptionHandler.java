package basic.headler;

import basic.model.ApiResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResponse<String> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception  {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(String.valueOf(e.getMessage()));
        apiResponse.setCode(ApiResponse.ERROR);
        return apiResponse;
    }
}
