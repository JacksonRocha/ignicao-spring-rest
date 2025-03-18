package jackson.rocha.algatransito.api.assembler;

import jackson.rocha.algatransito.api.model.VeiculoRepresentationModel;
import jackson.rocha.algatransito.api.model.input.VeiculoInput;
import jackson.rocha.algatransito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class VeiculoAssembler {

    private final ModelMapper modelMapper;

    public Veiculo toEntity(VeiculoInput veiculoInput) {
        return modelMapper.map(veiculoInput, Veiculo.class);
    }

    public VeiculoRepresentationModel toModel(Veiculo veiculo) {
        return modelMapper.map(veiculo, VeiculoRepresentationModel.class);
    }

    public List<VeiculoRepresentationModel> toCollectionModel(List<Veiculo> veiculos) {
        return veiculos.stream()
                .map(this::toModel)
                .toList();
    }

}
