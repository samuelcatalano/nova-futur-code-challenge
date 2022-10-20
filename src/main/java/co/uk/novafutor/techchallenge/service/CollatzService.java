package co.uk.novafutor.techchallenge.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Samuel Catalano
 * @since 20 March, 2020
 */

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CollatzService {

    private final StringBuilder builder = new StringBuilder();

    /**
     * Iterative version.
     * @param n the number
     */
    public String collatzIterative(Integer n) throws NumberFormatException {
        if (n <= 0)
            throw new NumberFormatException("Number must be greater than 0");

        String result = "";
        while (n != 1) {
            this.builder.append(n + " ");

            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 1 + (3 * n);
            }
        }

        this.builder.append(n);
        result = this.builder.toString();

        return result.trim();
    }

    /**
     * Recursive version.
     * @param n the number
     * @return
     */
    public String collatzRecursive(final Integer n) throws NumberFormatException {
        if (n <= 0)
            throw new NumberFormatException("Number must be greater than 0");

        String result = "";
        if (n == 1) {
            result = this.builder.toString() + " 1";
        } else
            if (n % 2 == 0) {
                this.builder.append(" " + n);
                return this.collatzRecursive(n / 2);
            } else {
                this.builder.append(" " + n);
                return this.collatzRecursive(1 + (3 * n));
            }

        return result.trim();
    }
}