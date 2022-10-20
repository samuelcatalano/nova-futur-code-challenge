package co.uk.novafutor.techchallenge.controller;

import co.uk.novafutor.techchallenge.json.ErrorMessage;
import co.uk.novafutor.techchallenge.service.CollatzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Samuel Catalano
 * @since 20 March, 2020
 */

@RestController
@RequestMapping(value = "/collatz")
public class CollatzRESTController {

    @Autowired
    private CollatzService collatzService;

    /**
     * Returns the iterative version of collatz conjecture.
     * @param number the number to be inferred
     * @return the iterative version of collatz conjecture
     */
    @GetMapping(value = "/iterative")
    public ResponseEntity<?> collatzIterative(@RequestParam(value = "number") final Integer number) {
        try {
            final String result = this.collatzService.collatzIterative(number);
            return ResponseEntity.ok(result);
        } catch (final NumberFormatException e) {
            return ResponseEntity.badRequest().body(new ErrorMessage()
                    .code(400)
                    .status(HttpStatus.BAD_REQUEST.name())
                    .message(e.getMessage()));
        }
    }

    /**
     * Returns the recursive version of collatz conjecture.
     * @param number the number to be inferred
     * @return the recursive version of collatz conjecture
     */
    @GetMapping(value = "/recursive")
    public ResponseEntity<?> collatzRecursive(@RequestParam(value = "number") final Integer number) {
        try {
            final String result = this.collatzService.collatzRecursive(number);
            return ResponseEntity.ok(result);
        } catch (final NumberFormatException e) {
            return ResponseEntity.badRequest().body(new ErrorMessage()
                    .code(400)
                    .status(HttpStatus.BAD_REQUEST.name())
                    .message(e.getMessage()));
        }
    }
}