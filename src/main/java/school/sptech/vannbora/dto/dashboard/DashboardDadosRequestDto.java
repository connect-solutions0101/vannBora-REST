package school.sptech.vannbora.dto.dashboard;

import java.time.LocalDate;

public record DashboardDadosRequestDto(
    LocalDate dataInicio,
    LocalDate dataFim
) {}
