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
    @Size(min = 3,max = 20, groups = Inner.class)
    private String name;

    @Pattern(regexp = "[0-9]{10}", groups = Inner.class)
    @TinConstraint
    private String tin;

    @Pattern(regexp = "[0-9]{9}", groups = Inner.class)
    private String iec;

    @Pattern(regexp = "[0-9]{20}", groups = Inner.class)
    private String accountNumber;

    @Pattern(regexp = "[0-9]{9}", groups = Inner.class)
    private String bic;
}
