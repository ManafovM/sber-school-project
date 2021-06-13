package counterparties;

import com.fasterxml.jackson.databind.ObjectMapper;
import counterparties.controller.CounterpartyController;
import counterparties.entity.Counterparty;
import counterparties.service.CounterpartyService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CounterpartyController.class)
public class CounterpartyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CounterpartyService service;

    private static List<Counterparty> counterparties;

    @BeforeAll
    public static void addCounterparties() {
        counterparties = new ArrayList<>();
        counterparties.add(Counterparty.builder()
                .id(1L)
                .name("sber1")
                .tin("7706092528")
                .iec("770543003")
                .accountNumber("40817810680013379710")
                .bic("044525297")
                .build());
        counterparties.add(Counterparty.builder()
                .id(2L)
                .name("sber2")
                .tin("7704642346")
                .iec("770434563")
                .accountNumber("404442356438008765432")
                .bic("044532686")
                .build());
    }

    @Test
    public void getByName() throws Exception {
        when(service.getByName(counterparties.get(0).getName()))
                .thenReturn(counterparties.get(0));
        mockMvc.perform(get("/counterparties/by-name")
                .param("name", counterparties.get(0).getName())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(counterparties.get(0).getName())));
    }

    @Test
    public void getByAccountAndBic() throws Exception {
        when(service.getByAccountNumberAndBic(counterparties.get(1).getAccountNumber(),
                counterparties.get(1).getBic())).thenReturn(counterparties.get(1));
        mockMvc.perform(get("/counterparties/by-account-and-bic")
                .param("account", counterparties.get(1).getAccountNumber())
                .param("bic", counterparties.get(1).getBic())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber", is(counterparties.get(1).getAccountNumber())))
                .andExpect(jsonPath("$.bic", is(counterparties.get(1).getBic())));
    }

    @Test
    public void updateByID() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(counterparties.get(1));

        when(service.updateById(counterparties.get(0).getId(), counterparties.get(1)))
                .thenReturn(counterparties.get(1));
        mockMvc.perform(put("/counterparties/" + counterparties.get(0).getId())
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(counterparties.get(1).getName())));
    }
}
