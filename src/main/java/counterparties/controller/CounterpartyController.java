package counterparties.controller;

import counterparties.entity.Counterparty;
import counterparties.service.CounterpartyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class CounterpartyController {
    private final CounterpartyService counterpartyService;

    @GetMapping("/counterparties")
    public List<Counterparty> all() {
        return counterpartyService.findAll();
    }
}
