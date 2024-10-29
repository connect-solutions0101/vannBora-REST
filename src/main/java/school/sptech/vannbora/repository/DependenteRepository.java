package school.sptech.vannbora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import school.sptech.vannbora.entidade.Dependente;

@Repository
public interface DependenteRepository extends JpaRepository<Dependente, Integer> {

    List<Dependente> findAllByProprietarioServicoId(int id);
    
}
