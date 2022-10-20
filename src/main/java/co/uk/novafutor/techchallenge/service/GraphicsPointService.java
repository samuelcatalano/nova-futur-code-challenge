package co.uk.novafutor.techchallenge.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.stream.IntStream;

/**
 * @author Samuel Catalano
 * @since 20 March, 2020
 */

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GraphicsPointService {

    /**
     * Dot product between 2 vectos using map and reduce.
     * @param a the vector A
     * @param b the vector B
     * @return the product between them
     */
    public Integer dotProduct(final Integer n, final Integer[] a, final Integer[] b) {
        return IntStream.range(0, n)
                .parallel()
                .map(i -> a[i] * b[i])
                .reduce(0, Integer::sum);
    }
}