package br.com.pedidogm.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author douglas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private Long id;
    private String nomeUsuario;
    private String senha;
    private String nomeCompleto;
    private String email;
    private LocalDateTime ultimoAcesso;
    private Boolean novaSenha;
    private Boolean ativo;
    private Boolean admin;

    @Override
    public String toString() {
        return this.getNomeUsuario();
    }

}
