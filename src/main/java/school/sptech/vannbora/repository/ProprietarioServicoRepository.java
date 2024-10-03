package school.sptech.vannbora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import school.sptech.vannbora.entidade.ProprietarioServico;

@Repository
public interface ProprietarioServicoRepository extends JpaRepository<ProprietarioServico, Integer> {
    UserDetails findByEmail(String email);
}
