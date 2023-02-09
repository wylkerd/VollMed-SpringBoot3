package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
    Classe de Filtro que utilia os recursos do Spring, para interceptar requisições
    e fazer a validação do Token JWT antes de cair no Controller.

    Servelets tradicionais do Java devem implementar uma clsse chamada Filter (jakarta.servelet),
    Mas como utilizaremos o filter do Spring, herdaremos (extends) a classe OncePerRequestFilter do Spring, pois ela implementa a interface Filter.

    * Esta Classe OncePerRequestFilter que estamos herdando do Spring Filter garante uma unica execução para cada requisição

    O @Component é utilizado para que o Spring carregue uma classe/componente genérico (não é um Serviço, Interface,
    Repository, Classe de tratamento de erros ou Classe de configuração, é componente genérico) automaticamente ao rodar o projeto.
*/

@Component
public class SecurityFilter extends OncePerRequestFilter { // ALT + Enter implement methods de OncePerRequestFilter

    @Autowired
    private TokenService tokenService;

    /* Este é o método que o Spring vai chamar quando este filtro for executado */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request); // ALT + Enter create method, para recuperação do token aqui
        var subject = tokenService.getSubject(tokenJWT); // Fazendo a verificação do Token JWT no tokenService, se chegar o subject aqui, é por que o token está válido

        

        // * NECESSÁRIO PARA CHAMAR OS PRÓXIMOS FILTROS NA APLICAÇÃO, SE NÃO TIVER OUTRO ELE CHAMA O CONTROLLER CORRESPONDENTE
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        /* Passamos neste método a request, e ele nos devolve a String do token que está no cabeçalho
        Vai pegar o valor da váriavel Authorization que veio no Header da request */
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null) {
            throw new RuntimeException("Token JWT não enviado no cabeçalho Authorization!");
        }

        return authorizationHeader.replace("Bearer ", "");
    }
}
