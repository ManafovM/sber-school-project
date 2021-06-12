package counterparties.service;

import counterparties.entity.Counterparty;
import counterparties.repository.CounterpartyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CounterpartyService {
    private final CounterpartyRepository counterpartyRepository;

    public List<Counterparty> getAll() {
        return counterpartyRepository.findAll();
    }

    public Counterparty getById(Long id) {
        return counterpartyRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Counterparty create(Counterparty counterparty) {
        return counterpartyRepository.save(counterparty);
    }

    public Counterparty updateById(Long id, Counterparty counterparty) {
        Counterparty currentCounterparty = counterpartyRepository.findById(id).orElseThrow(RuntimeException::new);
        currentCounterparty.setName(counterparty.getName());
        currentCounterparty.setTin(counterparty.getTin());
        currentCounterparty.setIec(counterparty.getIec());
        currentCounterparty.setAccountNumber(counterparty.getAccountNumber());
        currentCounterparty.setBic(counterparty.getBic());
        currentCounterparty = counterpartyRepository.save(counterparty);
        return currentCounterparty;
    }

    public void deleteById(Long id) {
        counterpartyRepository.deleteById(id);
    }

    public static List<Integer> stringToIntegers(String string) {
        return Arrays.stream(string.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
