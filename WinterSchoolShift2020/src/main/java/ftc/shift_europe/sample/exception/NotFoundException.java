// package ftc.shift_europe.sample.exception;

// import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.ResponseStatus;

// /**
//  * Если при обработке пользовательского запроса будет выброшено это исключение,
//  * то пользователю вернётся ответ со статусом 404 (NOT_FOUND)
//  */
// @ResponseStatus(value = HttpStatus.NOT_FOUND)
// public class NotFoundException extends RuntimeException {
// }

// @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="There is no such user")
// public class ThereIsNoSuchUserException extends RuntimeException {
// }

// @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="There is no such route")
// public class ThereIsNoSuchRouteException extends RuntimeException {
// }

// @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="There is no such flag")
// public class ThereIsNoSuchFlagException extends RuntimeException {
// }

// @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Not enough information")
// public class NotFoundException extends RuntimeException {
// }


// //посмотреть все это
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// @ControllerAdvice
// public class AwesomeExceptionHandler extends ResponseEntityExceptionHandler {

//     @ExceptionHandler(NotFoundException.class)
//     protected ResponseEntity<AwesomeException> handleNotFoundException() {
//         return new ResponseEntity<>(new AwesomeException("SOME ERROR"), HttpStatus.NOT_FOUND);
//     }

//     @ExceptionHandler(ThereIsNoSuchUserException.class)
//     protected ResponseEntity<AwesomeException> handleThereIsNoSuchUserException() {
//         return new ResponseEntity<>(new AwesomeException("There is no such user"), HttpStatus.NOT_FOUND);
//     }

//     @ExceptionHandler(ThereIsNoSuchRoute.class)
//     protected ResponseEntity<AwesomeException> handleThereIsNoSuchRoute() {
//         return new ResponseEntity<>(new AwesomeException("There is no such route"), HttpStatus.NOT_FOUND);
//     }

//     @ExceptionHandler(ThereIsNoSuchFlag.class)
//     protected ResponseEntity<AwesomeException> handleThereIsNoSuchFlag() {
//         return new ResponseEntity<>(new AwesomeException("There is no such flag"), HttpStatus.NOT_FOUND);
//     }


// 	@ExceptionHandler(NotFoundInformationException.class)
//     protected ResponseEntity<AwesomeException> handleNotFoundInformationException() {
//         return new ResponseEntity<>(new AwesomeException("not enough information"), HttpStatus.InternalError);
//     }

//     @Data
//     @AllArgsConstructor
//     private static class AwesomeExceptionHandlern {
//         private String message;
//     }
// }
//  