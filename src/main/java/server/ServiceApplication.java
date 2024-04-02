package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@SpringBootApplication
public class ServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(ServiceApplication.class, args);

  }
}

@RestController
class SampleController {

  @GetMapping("/test")
  Mono<Map<String, Object>> testEndpoint() {
    if(true){
      throw new IllegalArgumentException("test ex");
    }
    return Mono.just(Map.of());
  }

}

@ControllerAdvice
class CustomExceptionHandler {
  @ExceptionHandler(IllegalArgumentException.class)
  void handleCannotViewContentException(IllegalArgumentException exception, ServerHttpRequest request) {
    throw new ResponseStatusException(BAD_REQUEST, exception.getMessage());
  }
}