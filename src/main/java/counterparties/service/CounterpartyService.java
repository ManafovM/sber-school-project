package counterparties.service;

import counterparties.entity.Counterparty;
import counterparties.repository.CounterpartyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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

    public ResponseEntity<Counterparty> create(Counterparty counterparty) throws URISyntaxException {
        Counterparty savedCounterparty = counterpartyRepository.save(counterparty);
        return ResponseEntity.created(new URI("/counterparties/" +
                savedCounterparty.getId())).body(savedCounterparty);
    }

    public ResponseEntity<Counterparty> updateById(Long id, Counterparty counterparty) {
        Counterparty currentCounterparty = counterpartyRepository.findById(id).orElseThrow(RuntimeException::new);
        currentCounterparty.setName(counterparty.getName());
        currentCounterparty.setTin(counterparty.getTin());
        currentCounterparty.setIec(counterparty.getIec());
        currentCounterparty.setAccountNumber(counterparty.getAccountNumber());
        currentCounterparty.setBic(counterparty.getBic());
        currentCounterparty = counterpartyRepository.save(counterparty);
        return ResponseEntity.ok(currentCounterparty);
    }

    public ResponseEntity<Counterparty> deleteById(Long id) {
        counterpartyRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
