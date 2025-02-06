package jackson.rocha.algatransito.api.controller;

import jackson.rocha.algatransito.domain.model.Proprietario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProprietarioController {

    @GetMapping("/proprietarios")
    public List<Proprietario> listar() {
        var proprietario1 = new Proprietario();
        proprietario1.setId(1L);
        proprietario1.setNome("Jackson Rocha");
        proprietario1.setEmail("jackson@hotmail.com");
        proprietario1.setTelefone("999999999");

        var proprietario2 = new Proprietario();
        proprietario2.setId(2L);
        proprietario2.setNome("Maria Silva");
        proprietario2.setEmail("Maria@gmail.com");
        proprietario2.setTelefone("888888888");

        return Arrays.asList(proprietario1, proprietario2);

    }
}
