package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

// Classe de Serviço do Token JWT, fará geração, validação e etc do token
// Classe de serviço @Service, no startup da aplicação o Spring vai carregar esta classe automaticamente
// O Token precisa identificar qual usuário esta usando a aplicação (fazendo a request)
@Service
public class TokenService {

    @Value("${api.security.token.secret}") // Indicando ao Spring para ele ler esta propriedade em application.properties
    private String secret; // Preenchimento deste atributo vem de application.properties, onde o secret da API é gerado

    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret); // Fazendo a assinatura digital do Token com algoritmo HMAC256
            return JWT.create()
                    .withIssuer("API Voll.med") // Issuer: Nome de quem está gerando o token
                    .withSubject(usuario.getLogin()) // Indica qual usuário está relacionado ao token
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
           throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }


    public String getSubject(String tokenJWT) {
        /*
            Recebe um String Token JWT, verifica se está valido, se está de acordo com o algoritmo e o issuer
            configurados na geração do token, e devolve o subject que está armazenado no token. Usado na validação!
        */
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API Voll.med")
                    .build()
                    .verify(tokenJWT) // Se der erro aqui, já é lançada uma exception e para a execução do método
                    .getSubject(); // se chegar aqui é por que está tudo ok, e o subject é retornado

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")); // Fuso horário do Brasil
    }

    /*
    * Poderiamos gerar o token passando mais informações por meio da adição de withClaim()
    * Por exemplo, podemos incluir o id do usuário no token:

        * return JWT.create()
            .withIssuer("API Voll.med")
            .withSubject(usuario.getLogin())

            .withClaim("id", usuario.getId())

            .withExpiresAt(dataExpiracao())
            .sign(algoritmo);

    * O método withClaim recebe dois parâmetros, sendo o primeiro uma String que identifica o
        nome do claim (propriedade armazenada no token), e o segundo a informação que se deseja armazenar.
    * withClaim() pode ser chamado várias vezes
    * */
}
