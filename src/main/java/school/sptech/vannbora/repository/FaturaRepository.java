package school.sptech.vannbora.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.vannbora.entidade.Fatura;
import school.sptech.vannbora.enums.Pago;

public interface FaturaRepository extends JpaRepository<Fatura, Integer> {

    List<Fatura> findAllByResponsavelDependenteDependenteId(int dependenteId);

    List<Fatura> findAllByResponsavelDependenteResponsavelId(int responsavelId);

    int countByResponsavelDependenteDependenteIdAndPagoEqualsAndDataVencimentoBetween(int dependenteId, Pago pago,
            LocalDate comecoMes, LocalDate fimMes);
   
}
