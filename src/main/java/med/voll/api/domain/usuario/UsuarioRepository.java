package med.voll.api.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

// Repository JPA
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método findByLogin fará a consulta do usuário no Banco de Dados, na tabela usuário
    // Usada nomenclatura para consulta dinâmica do Spring Data
    UserDetails findByLogin(String login);

}
