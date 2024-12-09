package school.sptech.vannbora.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import school.sptech.vannbora.entidade.Fatura;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Integer> {

    List<Fatura> findAllByResponsavelDependenteDependenteId(int dependenteId);

    List<Fatura> findAllByResponsavelDependenteResponsavelId(int responsavelId);

    @Query("SELECT AVG(f.valor) FROM Fatura f WHERE f.responsavelDependente.dependente.proprietarioServico.id = :proprietarioServicoId")
    BigDecimal findAvgValorByResponsavelDependenteDependenteProprietarioServicoId(@Param("proprietarioServicoId") int proprietarioServicoId);

}
