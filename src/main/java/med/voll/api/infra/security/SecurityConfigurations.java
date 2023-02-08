package med.voll.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Anotação do Spring para Classe de Configuração
@EnableWebSecurity // Anotação para indicar personalização nas configurações de segurança
public class SecurityConfigurations {

    @Bean // Serve para expor o retorno deste método ao Spring, para que devolva um objeto com esta configuração oo Spring automaticamente, Controller ou Classe Service
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*
        * 1 - Desabilitando proteção contra ataques Cross-Site Request Forgery (CSRF), pois iremos utilizar JWT Token, que faz a proteção deste ataque
        * 2 - sessionManagement() configura a autenticação para ser STATELESS (por padrão vem como stateful)
        * */
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().build();
    }

}

/*  ATENÇÃO ------------------------------------------------
    1 - Configuração para aplicar processo de autenticação STATELESS
    2 - Spring HttpSecurity prove métodos de configurações para o Security e
        permite criar um objeto SecurityFilterChain
    3 - throw Exception na assinatura do método, para lançar a Exception caso tenha uma.
*/
