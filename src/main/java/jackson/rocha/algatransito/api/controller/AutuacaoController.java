package jackson.rocha.algatransito.api.controller;

import jackson.rocha.algatransito.api.assembler.AutuacaoAssembler;
import jackson.rocha.algatransito.api.model.AutuacaoRepresentationModel;
import jackson.rocha.algatransito.api.model.input.AutuacaoInput;
import jackson.rocha.algatransito.domain.model.Autuacao;
import jackson.rocha.algatransito.domain.service.RegistroAutuacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos/{veiculoId}/autuacoes")
public class AutuacaoController {

    private final AutuacaoAssembler autuacaoAssembler;
    private final RegistroAutuacaoService registroAutuacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoRepresentationModel registrar(@PathVariable Long veiculoId,
                                                 @Valid @RequestBody AutuacaoInput autuacaoInput) {

        Autuacao novaAutuacao = autuacaoAssembler.toEntity(autuacaoInput);
        Autuacao autuacaoRegistrada = registroAutuacaoService
                .registrar(veiculoId, autuacaoAssembler.toEntity(autuacaoInput));
        return autuacaoAssembler.toModel(autuacaoRegistrada);
    }

}
