package school.sptech.vannbora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.vannbora.entidade.TrajetoDependente;
import school.sptech.vannbora.entidade.TrajetoDependente.TrajetoDependenteId;

import java.util.List;

@Repository
public interface TrajetoDepenteRepository extends JpaRepository<TrajetoDependente, TrajetoDependenteId> {

    List<TrajetoDependente> findByResponsavelDependente_Responsavel_Id(Integer responsavelId);

    List<TrajetoDependente> findByResponsavelDependente_Dependente_Id(Integer dependenteId);

    List<TrajetoDependente> findByTrajeto_Id(Integer trajetoId);

}
