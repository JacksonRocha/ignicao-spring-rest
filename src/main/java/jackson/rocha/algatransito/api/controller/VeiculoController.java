package jackson.rocha.algatransito.api.controller;

import jackson.rocha.algatransito.api.assembler.VeiculoAssembler;
import jackson.rocha.algatransito.api.model.VeiculoRepresentationModel;
import jackson.rocha.algatransito.api.model.input.VeiculoInput;
import jackson.rocha.algatransito.domain.model.Veiculo;
import jackson.rocha.algatransito.domain.repository.VeiculoRepository;
import jackson.rocha.algatransito.domain.service.ApreensaoVeiculoService;
import jackson.rocha.algatransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;
    private final RegistroVeiculoService registroVeiculoService;
    private final ApreensaoVeiculoService apreensaoVeiculoService;
    private final VeiculoAssembler veiculoAssembler;

    @GetMapping
    public List<VeiculoRepresentationModel> listar() {
        return veiculoAssembler.toCollectionModel(veiculoRepository.findAll());
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<VeiculoRepresentationModel> buscar(@PathVariable Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .map(veiculoAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoRepresentationModel cadastrar(@Valid @RequestBody VeiculoInput veiculoInput) {
        Veiculo novoVeiculo = veiculoAssembler.toEntity(veiculoInput);
        Veiculo veiculoCadastrado = registroVeiculoService.cadastrar(novoVeiculo);

        return veiculoAssembler.toModel(veiculoCadastrado);

    }

    @PutMapping("/{veiculoId}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apreender(@PathVariable Long veiculoId) {
        apreensaoVeiculoService.apreender(veiculoId);
    }

    @DeleteMapping("/{veiculoId}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeraApreensao(@PathVariable Long veiculoId) {
        apreensaoVeiculoService.removerApreensao(veiculoId);
    }

}
