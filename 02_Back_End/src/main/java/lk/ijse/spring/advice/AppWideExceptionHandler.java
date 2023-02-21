package lk.ijse.spring.advice;

import lk.ijse.spring.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@CrossOrigin
public class AppWideExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({RuntimeException.class})
    public ResponseUtil handleException(RuntimeException e){
        return new ResponseUtil(500,e.getMessage(),null);
    }
}
