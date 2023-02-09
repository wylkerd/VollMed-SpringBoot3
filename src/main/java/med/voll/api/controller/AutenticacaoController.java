package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    /*
     * A Classe AuthenticationManager do próprio Spring dispara o processo de autenticação do usuário, por baixo dos panos,
     * chama a Classe AutenticacaoService que criamos, pois ela não pode ser chamada diretamente por ser uma Classe @Service.
    */

    // @Autowired: pedindo para o Spring injetar este atributo/parâmetro, sem precisar instanciar ele manualmente
    /*  ATENÇÃO ------------------------
        Indicar ao Spring, na Classe SecurityConfigurations @Configuration,
        que utilizaremos a instancia do AuthenticationManager (do próprio Spring) para ele criar o objeto
     */
    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) { // ALT + Enter create record

        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(token); // Devolve um objeto que representa um usuário logado no sistema, se houver (aqui Spring encontrou AutenticacaoService e chamou o UsuarioRepository).

        return ResponseEntity.ok().build();
    }
}
