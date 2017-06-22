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
public class Cliente extends Pessoa {

    private String email;
    private String contato;

    @Override
    public String toString() {
        return super.toString();
    }

}
