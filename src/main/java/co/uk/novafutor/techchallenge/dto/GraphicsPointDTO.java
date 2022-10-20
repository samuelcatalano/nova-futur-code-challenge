package co.uk.novafutor.techchallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Samuel Catalano
 * @since 20 March, 2020
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphicsPointDTO {

    private Integer n; // number of coordinates in a vector
    private Integer[] vectorA;
    private Integer[] vectorB;

}