package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

// Mapeamento de ENTIDADE JPA
// utiliza JPA como ferramenta de mapeamento obejto-relacional para representar uma tabela do DB, que é a camada de Domnio para fazer a persistencia.

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor // Exigencia do Data JPA
@AllArgsConstructor
@EqualsAndHashCode(of = "id") // gerar encima do ID
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded  // Classes sepradas porém no banco serão integradas na mesma tabela. Indica que a Classe Endereco vem de outro lugar
    private Endereco endereco;

    // Construtor que recebe parâmetro do tipo Dados DadosCadastroMedico
    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }

        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco()); // ALT + Enter Create method, vai criar um método na Classe Endereco
        }

    }
}
