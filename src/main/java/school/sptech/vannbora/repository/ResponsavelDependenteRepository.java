package school.sptech.vannbora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.vannbora.entidade.ResponsavelDependente;
import school.sptech.vannbora.entidade.ResponsavelDependente.ResponsavelDependenteId;

public interface ResponsavelDependenteRepository extends JpaRepository<ResponsavelDependente, ResponsavelDependenteId> {
    
}
