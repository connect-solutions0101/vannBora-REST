package school.sptech.vannbora.dto.dashboard;

import java.math.BigDecimal;

public record DashboardDadosResponseDto(
    Integer totalDependentes,
    Integer totalEscolas,
    Integer pagantesMes,
    Integer devedoresMes,
    BigDecimal recebimentoEsperadoMes,
    BigDecimal recebimentoRealizadoMes,
    BigDecimal rendaMesAnterior,
    BigDecimal rendaMediaPorDependente
) {}
