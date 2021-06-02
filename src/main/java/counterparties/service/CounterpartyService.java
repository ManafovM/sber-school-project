package counterparties.service;

import counterparties.entity.Counterparty;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import counterparties.repository.CounterpartyRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class CounterpartyService {
    private final CounterpartyRepository counterpartyRepository;

    public List<Counterparty> findAll() {
        return counterpartyRepository.findAll();
    }

    public void save(Counterparty counterparty) {
        counterpartyRepository.save(counterparty);
    }
}
