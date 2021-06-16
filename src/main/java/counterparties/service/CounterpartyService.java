package counterparties.service;

import counterparties.entity.Counterparty;
import counterparties.repository.CounterpartyRepository;
import counterparties.service.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

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

    public Counterparty create(Counterparty counterparty) throws BusinessException {
        try {
            return counterpartyRepository.save(counterparty);
        } catch (DataIntegrityViolationException ex) {
            throw new BusinessException(ex.getMostSpecificCause().getMessage(), ex);
        }
    }

    public Counterparty updateById(Long id, Counterparty counterparty) throws BusinessException {
        Counterparty currentCounterparty = counterpartyRepository.findById(id).orElseThrow(RuntimeException::new);
        currentCounterparty.setName(counterparty.getName());
        currentCounterparty.setTin(counterparty.getTin());
        currentCounterparty.setIec(counterparty.getIec());
        currentCounterparty.setAccountNumber(counterparty.getAccountNumber());
        currentCounterparty.setBic(counterparty.getBic());
        try {
            return counterpartyRepository.save(counterparty);
        } catch (TransactionSystemException ex) {
            throw new BusinessException(ex.getMostSpecificCause().getMessage(), ex);
        }
    }

    public void deleteById(Long id) {
        counterpartyRepository.deleteById(id);
    }

    public Counterparty getByName(String name) {
        return counterpartyRepository.findCounterpartyByName(name);
    }

    public Counterparty getByAccountNumberAndBic(String accountNumber, String bic) {
        return counterpartyRepository.findCounterpartyByAccountNumberAndBic(accountNumber, bic);
    }
}
