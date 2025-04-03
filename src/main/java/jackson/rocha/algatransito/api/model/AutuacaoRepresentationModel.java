package jackson.rocha.algatransito.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutuacaoRepresentationModel {

    private Long id;
    private String descricao;
    private String valorMulta;
    private String dataCadastro;
}
