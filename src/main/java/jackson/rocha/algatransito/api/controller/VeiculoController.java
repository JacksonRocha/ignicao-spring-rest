package jackson.rocha.algatransito.api.controller;

import jackson.rocha.algatransito.api.model.VeiculoRepresentationModel;
import jackson.rocha.algatransito.domain.model.Veiculo;
import jackson.rocha.algatransito.domain.repository.VeiculoRepository;
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

    @GetMapping
    public List<Veiculo> listar() {
        return veiculoRepository.findAll();
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<VeiculoRepresentationModel> buscar(@PathVariable Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .map(veiculo -> {
                    var veiculoRepresentationModel = new VeiculoRepresentationModel();
                    veiculoRepresentationModel.setId(veiculo.getId());
                    veiculoRepresentationModel.setNomeProprietario(veiculo.getProprietario().getNome());
                    veiculoRepresentationModel.setMarca(veiculo.getMarca());
                    veiculoRepresentationModel.setModelo(veiculo.getModelo());
                    veiculoRepresentationModel.setPlaca(veiculo.getPlaca());
                    veiculoRepresentationModel.setStatus(veiculo.getStatus());
                    veiculoRepresentationModel.setDataCadastro(veiculo.getDataCadastro());
                    veiculoRepresentationModel.setDataApreensao(veiculo.getDataApreensao());
                    return veiculoRepresentationModel;
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo cadastrar(@Valid @RequestBody Veiculo novoVeiculo) {
        return registroVeiculoService.cadastrar(novoVeiculo);
    }

}
