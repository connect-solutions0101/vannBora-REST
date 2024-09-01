package school.sptech.vannbora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.vannbora.entidade.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    Endereco findByCep(String cep);
}
