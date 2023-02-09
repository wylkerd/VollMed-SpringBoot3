package med.voll.api.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// Mapeamento da entidade JPA
/* Classe que representa o usuário deve implementar a interface UserDetails do spring Security,
avisando o Spring Security sobre a nossa classe de Usuario, para ele utilizar na hora do login na chamada da API */
@Table(name = "usuarios")
@Entity(name = "Usuario") // Nome da Entidade JPA: Mesmo nome da Classe
@Getter
@NoArgsConstructor // Exigencia do Data JPA
@AllArgsConstructor
@EqualsAndHashCode(of = "id") // gerar encima do ID
public class Usuario implements UserDetails { // ALT + Enter Implement Methods

    // Atributos da Entidade JPA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;

    /* Trocar retornos do métodos Boolean para true, a não ser que
    queira controlar a data de expiração da conta do usuário, por exemplo
    então teriamos que ter os atributos correspondes na classe também */

    /*
    SQL utilizado para criar usuário na base MySQL, já passando a senha (neste exemplo: 123456) com algoritimo Bcrypt:
        insert into usuarios values(1, 'user.login@voll.med', '$2a$12$G0F0kHUjQUSVBIzFuP5wauIFogVsRZAfZFOQbQqufv4pCbCqEDVZm');
    */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /* Este método serve para o controle de perfis de acesso. Nesta aplicação não
        teremos este controle, então vamos simular uma Coleção de perfis, pois o Spring nos obriga a isso.

        * Indicando ao Spring que o perfil deste Usuario é apenas um, ele é fixo e se chama ROLE_USER   */

        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha; // Indicando que o atributo senha da classe representa Password
    }

    @Override
    public String getUsername() {
        return login; // Indicando que o atributo login da classe representa Username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
