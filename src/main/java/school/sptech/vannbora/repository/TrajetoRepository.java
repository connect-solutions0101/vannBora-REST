package school.sptech.vannbora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.vannbora.entidade.Trajeto;

import java.util.List;

@Repository
public interface TrajetoRepository extends JpaRepository<Trajeto, Integer> {
    List<Trajeto> findByProprietarioServicoId(Integer id);
    List<Trajeto> findByProprietarioServicoIdAndNome(Integer id, String nome);
    List<Trajeto> findByProprietarioServicoIdAndPeriodo(Integer id, String periodo);
}
