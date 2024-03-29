package counterparties.repository;

import counterparties.entity.Counterparty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterpartyRepository extends JpaRepository<Counterparty, Long> {
    Counterparty findCounterpartyByName(String name);

    Counterparty findCounterpartyByAccountNumberAndBic(String accountNumber, String bic);
}
