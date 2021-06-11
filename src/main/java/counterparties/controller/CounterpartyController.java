package counterparties.controller;

import counterparties.entity.Counterparty;
import counterparties.service.CounterpartyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@AllArgsConstructor
@RestController
public class CounterpartyController {
    private final CounterpartyService counterpartyService;

    @GetMapping("/counterparties")
    public List<Counterparty> getAll() {
        return counterpartyService.getAll();
    }

    @GetMapping("/counterparties/{id}")
    public Counterparty getById(@PathVariable Long id) {
        return counterpartyService.getById(id);
    }

    @PostMapping("/counterparties")
    public ResponseEntity<Counterparty> create(@RequestBody Counterparty counterparty) throws URISyntaxException {
        return counterpartyService.create(counterparty);
    }

    @PutMapping("/counterparties/{id}")
    public ResponseEntity<Counterparty> updateById(@PathVariable Long id, @RequestBody Counterparty counterparty) {
        return counterpartyService.updateById(id, counterparty);
    }

    @DeleteMapping("/counterparties/{id}")
    public ResponseEntity<Counterparty> deleteById(@PathVariable Long id) {
        return counterpartyService.deleteById(id);
    }
}
