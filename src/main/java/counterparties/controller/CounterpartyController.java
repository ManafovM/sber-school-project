package counterparties.controller;

import counterparties.entity.Counterparty;
import counterparties.service.CounterpartyService;
import counterparties.service.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<?> create(@RequestBody Counterparty counterparty) throws URISyntaxException {
        Counterparty createdCounterparty;
        try {
            createdCounterparty = counterpartyService.create(counterparty);
        } catch (BusinessException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.created(new URI("/counterparties/" +
                createdCounterparty.getId())).body(createdCounterparty);
    }

    @PutMapping("/counterparties/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody Counterparty counterparty) {
        return ResponseEntity.ok(counterpartyService.updateById(id, counterparty));
    }

    @DeleteMapping("/counterparties/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        counterpartyService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/counterparties/by-name")
    public Counterparty getByName(@RequestParam String name) {
        return counterpartyService.getByName(name);
    }

    @GetMapping("/counterparties/by-account-and-bic")
    public Counterparty getByAccountNumberAndBic(@RequestParam(name = "account") String accountNumber, @RequestParam String bic) {
        return counterpartyService.getByAccountNumberAndBic(accountNumber, bic);
    }
}
