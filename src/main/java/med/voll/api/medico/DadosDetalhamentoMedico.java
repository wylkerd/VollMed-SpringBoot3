package med.voll.api.medico;

import med.voll.api.endereco.Endereco;

// DTO de retorno da atualização e cadastro do Médico, que devolve um médico completo com os valores atualizados
public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
