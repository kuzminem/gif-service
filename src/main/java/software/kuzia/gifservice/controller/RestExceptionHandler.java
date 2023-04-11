package software.kuzia.gifservice.controller;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import software.kuzia.gifservice.controller.dto.ErrorData;
import software.kuzia.gifservice.controller.dto.Response;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public Response<ErrorData> feignClientExceptionHandler(FeignException e) {
        return new Response<>(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                new ErrorData(e.toString()));
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public Response<ErrorData> illegalStateExceptionHandler(IllegalStateException e) {
        return new Response<>(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                new ErrorData(e.toString()));
    }
}
