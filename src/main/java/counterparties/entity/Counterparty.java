package counterparties.entity;

import counterparties.service.constraint.TinConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Counterparty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "Введите наименование контрагента.")
    @Size(min = 3, max = 20, message = "Наименование должно быть длиной от {min} до {max} символов.")
    private String name;

    @NotNull(message = "Введите ИНН контрагента.")
    @Pattern(regexp="[0-9]{10}", message = "ИНН должен состоять из 10 цифр.")
    @TinConstraint
    private String tin;

    @NotNull(message = "Введите КПП контрагента.")
    @Pattern(regexp="[0-9]{9}", message = "КПП должен состоять из 9 цифр.")
    private String iec;

    @NotNull(message = "Введите номер счета контрагента.")
    @Pattern(regexp="[0-9]{20}", message = "Номер счета должен состоять из 20 цифр.")
    private String accountNumber;

    @NotNull(message = "Введите БИК банка контрагента")
    @Pattern(regexp="[0-9]{9}", message = "БИК должен состоять из 9 цифр.")
    private String bic;
}
