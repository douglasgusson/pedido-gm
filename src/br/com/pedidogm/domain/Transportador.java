package br.com.pedidogm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author douglas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Transportador extends Pessoa {

    private String placa;

    @Override
    public String toString() {
        return super.toString();
    }

}
