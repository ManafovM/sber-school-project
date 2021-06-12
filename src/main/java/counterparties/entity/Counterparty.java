package counterparties.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @Size(min = 10, max = 10, message = "ИНН должен быть длиной {min} символов.")
    private String tin;

    @NotNull(message = "Введите КПП контрагента.")
    @Size(min = 9, max = 9, message = "КПП должен быть длиной {min} символов.")
    private String iec;

    @NotNull(message = "Введите номер счета контрагента.")
    @Size(min = 20, max = 20, message = "Номер счета должен быть длиной {min} символов.")
    private String accountNumber;

    @NotNull(message = "Введите БИК банка контрагента")
    @Size(min = 9, max = 9, message = "БИК должен быть длиной {min} символов.")
    private String bic;
}
