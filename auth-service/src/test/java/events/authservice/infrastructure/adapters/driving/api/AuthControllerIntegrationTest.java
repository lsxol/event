package events.authservice.infrastructure.adapters.driving.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import events.authservice.BaseIntegrationTest;
import events.authservice.domain.model.RoleUzytkownikowEnum;
import events.authservice.infrastracture.adapters.driving.api.dto.LogowanieRequest;
import events.authservice.infrastracture.adapters.driving.api.dto.RejestracjaRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

class AuthControllerIntegrationTest extends BaseIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void powinienZarejestrowacIZalogowacUzytkownika() throws Exception {
    var rejestracjaRequest = new RejestracjaRequest("test@test.pl", "testTest12!", RoleUzytkownikowEnum.KLIENT);
    ResultActions resultRej = mockMvc.perform(post("/api/auth/rejestruj")
        .contextPath("/api")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(rejestracjaRequest)));
    resultRej.andExpect(status().isCreated());
    var logowanieRequest = new LogowanieRequest("test@test.pl", "testTest12!");
    ResultActions resultLog = mockMvc.perform(post("/api/auth/zaloguj")
        .contextPath("/api")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(logowanieRequest)));
    resultLog.andExpect(status().isOk()).andExpect(jsonPath("$").isString()).andExpect(jsonPath("$").isNotEmpty());
  }

  @Test
  void powinienZarejestrowacAleNieZalogowacUzytkownika() throws Exception {
    var rejestracjaRequest = new RejestracjaRequest("test@test.pl", "TrudneHaslo12!", RoleUzytkownikowEnum.KLIENT);
    ResultActions resultRej = mockMvc.perform(post("/api/auth/rejestruj")
        .contextPath("/api")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(rejestracjaRequest)));
    resultRej.andExpect(status().isCreated());
    var logowanieRequest = new LogowanieRequest("test@test.pl", "InneTrudneHaslo12!");
    ResultActions resultLog = mockMvc.perform(post("/api/auth/zaloguj")
        .contextPath("/api")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(logowanieRequest)));
    resultLog.andExpect(status().is4xxClientError());
  }

}
