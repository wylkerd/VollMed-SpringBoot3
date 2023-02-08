package med.voll.api.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Mapeamento da entidade JPA
@Table(name = "usuarios")
@Entity(name = "Usuario") // Nome da Entidade JPA: Mesmo nome da Classe
@Getter
@NoArgsConstructor // Exigencia do Data JPA
@AllArgsConstructor
@EqualsAndHashCode(of = "id") // gerar encima do ID
public class Usuario {

    // Atributos da Entidade JPA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;

}
