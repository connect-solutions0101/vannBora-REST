package school.sptech.vannbora.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import school.sptech.vannbora.entidade.Dependente;
import school.sptech.vannbora.enums.Pago;

@Repository
public interface DependenteRepository extends JpaRepository<Dependente, Integer> {

    List<Dependente> findAllByProprietarioServicoId(int id);

    List<Dependente> findAllByProprietarioServicoIdAndNomeContaining(int id, String nome);
    
    Integer countByProprietarioServicoId(int id);

    Integer countByProprietarioServicoIdAndResponsaveisFaturaRegistroFaturaPagoAndResponsaveisFaturaRegistroFaturaDataPagamentoBetween(int id, Pago pago, LocalDate dataInicio, LocalDate dataFim);
}
