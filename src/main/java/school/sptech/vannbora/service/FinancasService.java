package school.sptech.vannbora.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.vannbora.dto.fatura.FaturaCsvDto;
import school.sptech.vannbora.entidade.Fatura;
import school.sptech.vannbora.mapper.FaturaCsvMapper;
import school.sptech.vannbora.repository.FaturaRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancasService {
    private final FaturaRepository faturaRepository;

    public byte[] getFaturasCsv() throws IOException {
        YearMonth ultimoMes = YearMonth.now();
        LocalDate inicio = ultimoMes.atDay(1);
        LocalDate fim = ultimoMes.atEndOfMonth();

        List<Fatura> faturas = faturaRepository.findAllbyDataVencimento(inicio, fim);
        List<FaturaCsvDto> faturaCsvDtos = faturas.stream()
                .map(FaturaCsvMapper::toCsvDto)
                .toList();

        // Gera o CSV
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {

            // Cabeçalho CSV
            writer.write("Nome Responsavel;Nome Dependente;Parentesco Responsavel;Data Pagamento;Valor Pagamento;Pago\n");

            // Registros CSV
            for (FaturaCsvDto dto : faturaCsvDtos) {
                writer.write(String.format("%s;%s;%s;%s;%.2f;%s\n",
                        dto.nomeResponsavel(),
                        dto.nomeDependente(),
                        dto.parentescoResponsavel(),
                        dto.dataPagamento() != null ? dto.dataPagamento().toString() : "",
                        dto.valorPagamento(),
                        dto.pago() ? "Sim" : "Não"));
            }

            writer.flush();
            return outputStream.toByteArray();
        }
    }
}