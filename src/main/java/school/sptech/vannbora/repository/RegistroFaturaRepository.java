package school.sptech.vannbora.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import school.sptech.vannbora.entidade.RegistroFatura;
import school.sptech.vannbora.enums.Pago;

public interface RegistroFaturaRepository extends JpaRepository<RegistroFatura, Integer> {

    List<RegistroFatura> findAllByFaturaId(int faturaId);

    @Query("SELECT SUM(rf.fatura.valor) FROM RegistroFatura rf WHERE rf.pago = :pago AND rf.fatura.responsavelDependente.dependente.proprietarioServico.id = :id AND rf.dataPagamento BETWEEN :dataInicio AND :dataFim")
    BigDecimal findSumFaturaValorByPagoAndFaturaResponsavelDependenteDependenteProprietarioServicoIdAndDataPagamentoBetween(
        @Param("pago") Pago pago, 
        @Param("id") int id, 
        @Param("dataInicio") LocalDate dataInicio, 
        @Param("dataFim") LocalDate dataFim
    );

    @Query("SELECT SUM(rf.fatura.valor) FROM RegistroFatura rf WHERE rf.fatura.responsavelDependente.dependente.proprietarioServico.id = :id AND rf.dataPagamento BETWEEN :dataInicio AND :dataFim")
    BigDecimal findSumFaturaValorByFaturaResponsavelDependenteDependenteProprietarioServicoIdAndDataPagamentoBetween(
        @Param("id") int id, 
        @Param("dataInicio") LocalDate dataInicio, 
        @Param("dataFim") LocalDate dataFim
    );

    @Query("SELECT MONTH(rf.dataPagamento) AS mes, SUM(rf.fatura.valor) AS total FROM RegistroFatura rf WHERE rf.fatura.responsavelDependente.dependente.proprietarioServico.id = :id AND YEAR(rf.dataPagamento) = YEAR(CURRENT_DATE) AND rf.pago = :pago GROUP BY MONTH(rf.dataPagamento)")
    List<Object[]> findRecebimentoRealDoAnoAtualPorMes(
        @Param("id") int id,
        @Param("pago") Pago pago
    );

    @Query("SELECT MONTH(rf.dataPagamento) AS mes, SUM(rf.fatura.valor) AS total FROM RegistroFatura rf WHERE rf.fatura.responsavelDependente.dependente.proprietarioServico.id = :id AND YEAR(rf.dataPagamento) = YEAR(CURRENT_DATE) GROUP BY MONTH(rf.dataPagamento)")
    List<Object[]> findRecebimentoEsperadoDoAnoAtualPorMes(@Param("id") int id);

    @Query("SELECT DAY(rf.dataPagamento) AS dia, SUM(rf.fatura.valor) AS total FROM RegistroFatura rf WHERE rf.fatura.responsavelDependente.dependente.proprietarioServico.id = :id AND MONTH(rf.dataPagamento) = MONTH(CURRENT_DATE) AND rf.pago = :pago GROUP BY DAY(rf.dataPagamento)")
    List<Object[]> findRecebimentoRealDoMesAtualPorDia(
        @Param("id") int id,
        @Param("pago") Pago pago
    );

    @Query("SELECT DAY(rf.dataPagamento) AS dia, SUM(rf.fatura.valor) AS total FROM RegistroFatura rf WHERE rf.fatura.responsavelDependente.dependente.proprietarioServico.id = :id AND MONTH(rf.dataPagamento) = MONTH(CURRENT_DATE) GROUP BY DAY(rf.dataPagamento)")
    List<Object[]> findRecebimentoEsperadoDoMesAtualPorDia(@Param("id") int id);
}
