package school.sptech.vannbora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.vannbora.entidade.Fatura;

public interface FaturaRepository extends JpaRepository<Fatura, Integer> {

    List<Fatura> findAllByResponsavelDependenteDependenteId(int dependenteId);

    List<Fatura> findAllByResponsavelDependenteResponsavelId(int responsavelId);
   
}
