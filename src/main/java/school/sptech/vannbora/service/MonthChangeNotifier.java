package school.sptech.vannbora.service;

import org.springframework.stereotype.Component;
import school.sptech.vannbora.interfaces.MonthChangeListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MonthChangeNotifier {
    private final List<MonthChangeListener> listeners = new ArrayList<>();
    private LocalDate lastCheckedDate = LocalDate.now();

    public void addListener(MonthChangeListener listener) {
        listeners.add(listener);
    }

    public void notifyListeners() {
        for(MonthChangeListener listener : listeners) {
            listener.onMonthChange();
        }
    }

    public void checkMonthChange() {
        LocalDate currentDate = LocalDate.now();
        if(currentDate.getMonth() != lastCheckedDate.getMonth()) {
            lastCheckedDate = currentDate;
            notifyListeners();
        }
    }
}
