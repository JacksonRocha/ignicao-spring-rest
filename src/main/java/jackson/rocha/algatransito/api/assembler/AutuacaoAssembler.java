package jackson.rocha.algatransito.api.assembler;

import jackson.rocha.algatransito.api.model.AutuacaoRepresentationModel;
import jackson.rocha.algatransito.api.model.input.AutuacaoInput;
import jackson.rocha.algatransito.domain.model.Autuacao;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class AutuacaoAssembler {

    private final ModelMapper modelMapper;

    public Autuacao toEntity(AutuacaoInput autuacaoInput) {
        return modelMapper.map(autuacaoInput, Autuacao.class);
    }

    public AutuacaoRepresentationModel toModel(Autuacao autuacao) {
        return modelMapper.map(autuacao, AutuacaoRepresentationModel.class);
    }

    public List<AutuacaoRepresentationModel> toCollectionModel(List<Autuacao> autuacoes) {
        return autuacoes.stream()
                .map(this::toModel)
                .toList();
    }
}
