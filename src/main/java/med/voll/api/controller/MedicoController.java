package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos") // nomeando a URL
public class MedicoController {

    @Autowired // Annotation para Injeção de Dependencia, para criar este objeto e passar ao controller automaticamente
    private MedicoRepository respository;

    @PostMapping
    @Transactional // Como este é um método de escrita no BD, é necessário ter uma transação ativa com o Banco de Dados
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        // Pressionar ALT + Enter, para criar construtor de Medico que recebe DadosCadastroMedico

        respository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        // transformando um lista de Medicos em uma lista do Dto DadosListagemMedico
        // Lembrar de criar construtor no DTO
        // Spring JPA Repository do findAll() tem uma sobrecarga para receber um Pageable como parâmetro para paginação automatica

        // Já devolve um Page do DTO automaticamente
        // url da requisição exemplo http://localhost:8080/medicos?size=1&page=2&sort=nome,desc

        return respository.findAll(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional // Como este é um método de escrita no BD, é necessário ter uma transação ativa com o Banco de Dados
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {

        // 1- Carrega o registro atual no banco de dados pelo id
        // 2 - Sobrescreve os atributos baseados nos novos campos que chegaram no DTO e pronto!

        // Se carregar uma entidade do BD e mudar algum atributo...
        // a JPA já faz automaticamente o update dos atributos ao finalizar a Transaction

        var medico = respository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados); // ALT + Enter Create method, vai criar um método na Classe Medico

    }

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