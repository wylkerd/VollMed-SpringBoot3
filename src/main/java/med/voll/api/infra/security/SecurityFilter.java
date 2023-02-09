package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    /* Este é o método que o Spring vai chamar quando este filtro for executado */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // NECESSÁRIO PARA CHAMAR OS PRÓXIMOS FILTROS NA APLICAÇÃO, SE NÃO TIVER OUTRO ELE CHAMA O CONTROLLER CORRESPONDENTE
        filterChain.doFilter(request, response);
    }
}
