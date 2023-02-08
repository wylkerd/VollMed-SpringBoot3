package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.Endereco;

// DTO de retorno da atualização, cadastro e Detalhamento do Médico, que devolve um médico completo com os valores atualizados
public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
