package counterparties.service;

import counterparties.entity.Counterparty;
import org.springframework.stereotype.Service;
import counterparties.repository.CounterpartyRepository;

import java.util.List;

@Service
public class CounterpartyService {
    private CounterpartyRepository counterpartyRepository;

    public List<Counterparty> findAll() {
        return counterpartyRepository.findAll();
    }

    public void save(Counterparty counterparty) {
        counterpartyRepository.save(counterparty);
    }
}
