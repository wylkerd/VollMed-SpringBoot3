package med.voll.api.endereco;

// DTO Dados de Endereco - Record
public record DadosEndereco(String logadouro, String bairro, String cep, String cidade, String uf, String complemento, String numero) {
}

// Objeto do tipo Record é um classe imutável, por baixo dos panos vira uma Classe, e apenas com isso já temos estas propriedades como atributos, gerando Getters e Setters
// Spring 3 já dá suporte para criar objetos record automaticamente.