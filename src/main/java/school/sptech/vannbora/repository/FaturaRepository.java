package school.sptech.vannbora.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.vannbora.entidade.Fatura;

public interface FaturaRepository extends JpaRepository<Fatura, Integer> {

    List<Fatura> findAllByResponsavelDependenteDependenteId(int dependenteId);

    List<Fatura> findAllByResponsavelDependenteResponsavelId(int responsavelId);

    @Query("SELECT f FROM Fatura f WHERE f.dataVencimento BETWEEN :inicio AND :fim")
    List<Fatura> findAllbyDataVencimento(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
   
}
