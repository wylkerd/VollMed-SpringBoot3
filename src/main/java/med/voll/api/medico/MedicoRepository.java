package med.voll.api.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repository
// Interface que extende Spring JPARepository, este recurso do Spring faz o mesmo papel do antigo DAO, porém com mais facilidade e simplicidade, tudo automaticamente.
// Herdará todos os métodos de JPARepository (save, get, delete e etc.)
// 1º parâmetro: Qual o tipo da Entidade
// 2º parâmetro: Qual o tipo do atributo da chave primária da Entidade escolhida no 1º parâmetro
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}
