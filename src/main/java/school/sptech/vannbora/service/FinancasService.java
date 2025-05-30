package school.sptech.vannbora.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.vannbora.dto.fatura.FaturaCsvDto;
import school.sptech.vannbora.entidade.RegistroFatura;
import school.sptech.vannbora.mapper.FaturaCsvMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancasService {

    private final RegistroFaturaService registroFaturaService;

    public byte[] getFaturasCsv(Integer id) throws IOException {
        YearMonth ultimoMes = YearMonth.now();
        LocalDate inicio = ultimoMes.atDay(1);
        LocalDate fim = ultimoMes.atEndOfMonth();

        List<RegistroFatura> faturas = registroFaturaService.listarPorProprietarioIdAndPeriodo(id, inicio, fim);
        List<FaturaCsvDto> faturaCsvDtos = faturas.stream()
                .map(FaturaCsvMapper::toCsvDto)
                .toList();

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {

            // Cabeçalho CSV
            writer.write("Nome Responsavel;Nome Dependente;Parentesco Responsavel;Data Pagamento;Valor Pagamento;Pago\n");

            escreverFaturasRecursivamente(faturaCsvDtos, writer, 0);

            writer.flush();
            return outputStream.toByteArray();
        }
    }

    private void escreverFaturasRecursivamente(List<FaturaCsvDto> faturaCsvDtos, OutputStreamWriter writer, int index) throws IOException {
        if (index >= faturaCsvDtos.size()) {
            return;
        }

        FaturaCsvDto dto = faturaCsvDtos.get(index);
        writer.write(String.format("%s;%s;%s;%s;%.2f;%s\n",
                dto.nomeResponsavel(),
                dto.nomeDependente(),
                dto.parentescoResponsavel(),
                dto.diaPagamento(),
                dto.valorPagamento(),
                dto.pago().getDescricao()
                )
        );

        escreverFaturasRecursivamente(faturaCsvDtos, writer, index + 1);
    }
}