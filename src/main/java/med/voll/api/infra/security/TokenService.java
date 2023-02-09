package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.domain.usuario.Usuario;
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

    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256("12345678"); // Fazendo a assinatura digital do Token com algoritmo HMAC256
            return JWT.create()
                    .withIssuer("API Voll.med") // Issuer: Nome de quem está gerando o token
                    .withSubject(usuario.getLogin()) // Indica qual usuário está relacionado ao token
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
           throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")); // Fuso horário do Brasil
    }

    /*
    * Poderiamos gerar o token passando mais informações por meio da adição de withClaim()
    * withClaim() pode ser chamado várias vezes para adicionar informações ao token chave:valor
    * .withClaim("id", usuario.getId()) por exemplo
    * */
}