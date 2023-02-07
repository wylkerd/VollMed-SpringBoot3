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

    // Construtor que recebe parâmetro do tipo Dados Endereço
    public Endereco(DadosEndereco dados) {
        this.logadouro = dados.logadouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
    }
}
