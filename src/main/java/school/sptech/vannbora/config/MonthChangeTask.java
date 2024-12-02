package school.sptech.vannbora.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import school.sptech.vannbora.service.MonthChangeNotifier;

@Component
public class MonthChangeTask {
    private final MonthChangeNotifier notifier;

    public MonthChangeTask(MonthChangeNotifier notifier) {
        this.notifier = notifier;
    }

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void checkMonthChange() {
        notifier.checkMonthChange();
    }
}
