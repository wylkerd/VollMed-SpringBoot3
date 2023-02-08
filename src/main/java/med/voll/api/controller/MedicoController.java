package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.DadosCadastroMedico;
import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos") // nomeando a URL
public class MedicoController {

    @Autowired // Annotation para Injeção de Dependencia, para criar este objeto e passar ao controller automaticamente
    private MedicoRepository respository;

    @PostMapping
    @Transactional // Como este é um método de escrita no BD, é necessário ter uma transação ativa com o Banco de Dados
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        // Pressionar ALT + Enter, para criar construtor de Medico que recebe DadosCadastroMedico
        var medico = new Medico(dados);
        respository.save(medico);

        // O próprio Spring já passa a uri sendo utilizada atuomaticamente ao inserir UriComponentsBuilder como parâmetro do método
        // Exemplo: http://localhost:8080
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        // HTTP 201: Devolve o DTO do corpo da requisição criado e cabeçalho com o Location de captura do registro que foi salvo, fica disponível no Header.
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    // Recebe PARÂMETROS de QUERY do Pageable, utilizados nos metódos GET do HTTP
    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        // transformando uma lista de Medicos em uma lista do Dto DadosListagemMedico
        // Lembrar de criar construtor no DTO que recebe um Medico
        // Spring JPA Repository do findAll() tem uma sobrecarga para receber um Pageable como parâmetro para paginação automatica

        // Já devolve um Page do DTO automaticamente
        // url da requisição exemplo http://localhost:8080/medicos?size=1&page=2&sort=nome,desc
        // return respository.findAll(paginacao).map(DadosListagemMedico::new);

        // De acordo com a nomenclatura do método o JPA já cria a implementação lógica dele automaticamente
        var page =  respository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new); // ALT + Enter Create method
        return ResponseEntity.ok(page); // HTTP 200 / Get
    }

    @PutMapping
    @Transactional // Como este é um método de escrita no BD, é necessário ter uma transação ativa com o Banco de Dados
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {

        // 1- Carrega o registro atual no banco de dados pelo id
        // 2 - Sobrescreve os atributos baseados nos novos campos que chegaram no DTO e pronto!

        // Se carregar uma entidade do BD e mudar algum atributo...
        // a JPA já faz automaticamente o update dos atributos ao finalizar a Transaction

        var medico = respository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados); // ALT + Enter Create method, vai criar um método na Classe Medico

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico)); // ALT + Enter create record, para criar o DTO
    }

    // Exclusão Lógica do registro na base
    @DeleteMapping("/{id}")
    @Transactional // Como este é um método de escrita no BD, é necessário ter uma transação ativa com o Banco de Dados
    public ResponseEntity excluir(@PathVariable Long id) {
        var medico = respository.getReferenceById(id);
        medico.excluir(); // ALT + Enter Create method

        return ResponseEntity.noContent().build(); // NoContent devolve o código 204, usado no HTTP para exclusões. build() constrói um objeto Response Entity.
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = respository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    /* // Exclusão Física padrão do registro na base

        // Recebe parâmetro da URL, como complemento dela, utilizado no método DELETE
        // @PathVariable para indicar que está pegando aquele mesmo parâmetro dinâmico da url

        @DeleteMapping("/{id}")
        @Transactional // Como este é um método de escrita no BD, é necessário ter uma transação ativa com o Banco de Dados
        public void excluirFisico(@PathVariable Long id) {
            respository.deleteById(id);
        }
    */


    /*  // Lista de Medicos sem paginação
        @GetMapping
        public List<DadosListagemMedico> listarSemPaginacao() {
            // transformando um lista de Medicos em uma lista do Dto DadosListagemMedico
            // Lembrar de criar construtor no DTO
            return respository.findAll().stream().map(DadosListagemMedico::new).toList();
        }
     */

}

// ------------ COMENTÁRIOS ----------------- //
// @RequestBody indica que o parâmetro virá do corpo da requisição, o Body no formato JSON.

// ALT + ENTER -> Create record 'DadosCadastroMedico'. Record é como uma Classe java, porém imutável, proporcionando um código menos verboso
// (sem necessidade de Getters, equals, hashCode e toString por exemplo) se comparado a uma Classe, é um recurso das versões mais recentes.

// Annotation RestController para que o Spring carregue esta Classe como uma controller, ao iniciar o projeto

// Injetando o Repository como sendo um atriburo do Controller, denominado repository com tipo MedicoRepository