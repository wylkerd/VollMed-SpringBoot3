package med.voll.api.domain.medico;

// DTO record, para retorno da listagem de médicos apenas com nome, email, crm e especialidade
public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

    // Contrutor de DadosListagemMedico com parâmetro do tipo Medico e chama o construtor principal com o this()
    // A partir de um Médico o construtor preenche as insformações do construtor do Record
    public DadosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
