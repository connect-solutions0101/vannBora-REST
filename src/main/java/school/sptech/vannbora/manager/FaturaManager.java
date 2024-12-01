package school.sptech.vannbora.manager;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.sptech.vannbora.controller.RegistroFaturaController;
import school.sptech.vannbora.dto.registroFatura.RegistroFaturaRequestDto;
import school.sptech.vannbora.entidade.Fatura;
import school.sptech.vannbora.interfaces.MonthChangeListener;
import school.sptech.vannbora.repository.FaturaRepository;
import school.sptech.vannbora.service.MonthChangeNotifier;

import java.time.LocalDate;
import java.util.List;

@Component
public class FaturaManager implements MonthChangeListener {
    @Autowired
    private FaturaRepository faturaRepository;

    @Autowired
    private RegistroFaturaController registroFaturaController;

    private final MonthChangeNotifier notifier;

    public FaturaManager(MonthChangeNotifier notifier) {
        this.notifier = notifier;
    }

    @PostConstruct
    public void init() {
        notifier.addListener(this);
    }

    @Override
    public void onMonthChange() {
        System.out.println("Mês mudou! Gerando faturas para dependentes...");
        gerarRegistroFaturas();
    }

    private void gerarRegistroFaturas() {
        List<Fatura> faturas = faturaRepository.findAll();

        for(Fatura fatura : faturas){
            RegistroFaturaRequestDto build = RegistroFaturaRequestDto.builder()
                    .faturaId(fatura.getId())
                    .pago(false)
                    .dataPagamento(LocalDate.now())
                    .build();

            registroFaturaController.salvar(build);
        }
    }
}
