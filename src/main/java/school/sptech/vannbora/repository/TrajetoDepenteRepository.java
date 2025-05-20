package school.sptech.vannbora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.vannbora.entidade.TrajetoDependente;

import java.util.List;

@Repository
public interface TrajetoDepenteRepository extends JpaRepository<TrajetoDependente, Integer> {
    List<TrajetoDependente> findByTrajetoId(Integer trajetoId);
}
