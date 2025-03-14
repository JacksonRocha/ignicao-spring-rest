package jackson.rocha.algatransito.domain.service;

import jackson.rocha.algatransito.domain.model.StatusVeiculo;
import jackson.rocha.algatransito.domain.model.Veiculo;
import jackson.rocha.algatransito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    private final VeiculoRepository veiculoRepository;

    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo) {
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(LocalDateTime.now());

        return veiculoRepository.save(novoVeiculo);
    }
}
