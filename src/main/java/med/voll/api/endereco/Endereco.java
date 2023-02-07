package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Mapeamento de ENTIDADE JPA

@Embeddable
@Getter
@NoArgsConstructor // Exigencia do Data JPA
@AllArgsConstructor
public class Endereco {

    private String logadouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;
}
