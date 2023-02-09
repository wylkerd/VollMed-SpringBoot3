package med.voll.api.domain.usuario;

// DTO record, que representa o body que o frontend(cliente) envia para autenticação
public record DadosAutenticacao(String login, String senha) {
}
