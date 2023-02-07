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
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded // Classes sepradas porém no banco fará parte da mesma tabela
    private Endereco endereco;

}
