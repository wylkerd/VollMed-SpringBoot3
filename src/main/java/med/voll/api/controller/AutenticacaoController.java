package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.DadosTokenJWT;
import med.voll.api.infra.security.TokenService;
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
    private AuthenticationManager manager; // Dispara o processo de autentiação, que chama a AutenticacaoService, que chama o Repository para acessar o banco, devolve 403 ou 200 caso exista

    @Autowired
    private TokenService tokenService; // Injetando o objeto da Classe de Serviço do Token que criamos

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) { // ALT + Enter create record
        var autenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()); // Criando DTO do Spring Security através do nosso DTO
        var authentication = manager.authenticate(autenticationToken); // Devolve um objeto que representa um usuário logado no sistema, se houver (aqui Spring encontrou AutenticacaoService e chamou o UsuarioRepository).

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal()); // getPrincipal() para pegar o usuário logado / ALT + Enter Cast Usuário pois estava devolvendo um object

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT)); // ALT + Enter Create Record, para devolver o token no padrão do DTO
    }
}
