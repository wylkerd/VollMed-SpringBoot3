package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Annotation para indicar ao Spring que esta Classe que tratará os erros automaticamente, pois é do tipo RestControllerAdvice
@RestControllerAdvice
public class TratadorDeErros {

    // Indicação de qual erro este método vai tratar, neste caso Entity not found, passando de 500 para 404 Not Found
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    // Para caso no cadastro ou atualização seja encontrato erro na validação das props do Body, tratando erro 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException exception) {
        var erros = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList()); // mapeamento de erros para DadosErroValidacao
    }

    // DTO de resposta para indicar campos e seus erros respectivos
    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            // passando para o Construtor padrão deste DTO
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
