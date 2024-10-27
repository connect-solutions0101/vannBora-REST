package school.sptech.vannbora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.vannbora.entidade.Escola;

public interface EscolaRepository extends JpaRepository<Escola, Integer> {

    int countById(int id);
}
