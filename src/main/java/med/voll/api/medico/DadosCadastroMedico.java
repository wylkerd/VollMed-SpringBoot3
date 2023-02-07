package med.voll.api.medico;

import med.voll.api.endereco.DadosEndereco;

// DTO Dados de Medico - Record
public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {
}

// Objeto do tipo Record é um classe imutável, por baixo dos panos vira uma Classe, e apenas com isso já temos estas propriedades como atributos, gerando também Contrutor, equals, hashCode, toString e Getters
// Spring 3 já dá suporte para criar objetos record automaticamente.
// Esse tipo de classe se encaixa perfeitamente para representar classes DTO, já que seu objetivo é apenas representar dados que serão recebidos ou devolvidos pela API, sem nenhum tipo de comportamento.

// alt + enter -> create enum 'Especialidade'. / ALT + ENTER -> Create record 'DadosEndereco'.
