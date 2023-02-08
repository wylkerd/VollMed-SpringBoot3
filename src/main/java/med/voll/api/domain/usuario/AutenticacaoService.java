package med.voll.api.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
    Implementando a interface UserDetailsService, do Spring Security, para indicar
    automaticamente para o Spring Security que as regras de Autenticação virão desta Classe de Serviço.
    Com isso o Spring chama esta Classe e chama no momento da autenticação. Desde que tenha a anotação @Service e
    implemente UserDetailsService.
*/
@Service // Indica para o Spring que esta Classe de Serviço deve ser usada ao iniciar a API
public class AutenticacaoService implements UserDetailsService { // ALT + Enter implementar Metodos da interface

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username); // ALT + Enter Create method na interface Repository JPA
    }
}
