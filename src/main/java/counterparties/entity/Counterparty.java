package counterparties.entity;

import counterparties.service.constraint.AccountNumberAndBicConstraint;
import counterparties.service.constraint.TinConstraint;
import counterparties.service.validator.Inner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.GroupSequence;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@AccountNumberAndBicConstraint(
        accountNumber = "accountNumber",
        bic = "bic"
)
@GroupSequence({Inner.class, Counterparty.class})
public class Counterparty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 3, max = 20, message = "Наименование должно быть длиной от {min} до {max} символов.", groups = Inner.class)
    private String name;

    @Pattern(regexp = "[0-9]{10}", message = "ИНН должен состоять из 10 цифр.", groups = Inner.class)
    @TinConstraint
    private String tin;

    @Pattern(regexp = "[0-9]{9}", message = "КПП должен состоять из 9 цифр.", groups = Inner.class)
    private String iec;

    @Pattern(regexp = "[0-9]{20}", message = "Номер счета должен состоять из 20 цифр.", groups = Inner.class)
    private String accountNumber;

    @Pattern(regexp = "[0-9]{9}", message = "БИК должен состоять из 9 цифр.", groups = Inner.class)
    private String bic;
}
