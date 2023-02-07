package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

// DTO Dados de Medico - Record
// Utilizando annotation do Bean Validation
public record DadosCadastroMedico(

        @NotBlank // apenas para validar Strings
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}") // de 4 a 6 digitos para crm
        String crm,
        @NotNull
        Especialidade especialidade,
        // @Valid valida as anotações de outro DTO que está presente
        @NotNull @Valid DadosEndereco endereco) {
}

// ------------ COMENTÁRIOS ----------------- //
// Objeto do tipo Record é um classe imutável, por baixo dos panos vira uma Classe, e apenas com isso já temos estas propriedades como atributos, gerando também Contrutor, equals, hashCode, toString e Getters
// Spring 3 já dá suporte para criar objetos record automaticamente.
// Esse tipo de classe se encaixa perfeitamente para representar classes DTO, já que seu objetivo é apenas representar dados que serão recebidos ou devolvidos pela API, sem nenhum tipo de comportamento.

// alt + enter -> create enum 'Especialidade'. / ALT + ENTER -> Create record 'DadosEndereco'.

// @Valid para que a controler se integre com as validações do BeanValidation, presente nos DTO's
