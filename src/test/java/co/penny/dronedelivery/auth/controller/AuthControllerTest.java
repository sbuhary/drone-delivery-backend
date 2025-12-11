package co.penny.dronedelivery.auth.controller;

import co.penny.dronedelivery.auth.api.TokenRequest;
import co.penny.dronedelivery.auth.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest {

    @Test
    void token_returnsToken() throws Exception {
        TokenService tokens = org.mockito.Mockito.mock(TokenService.class);
        org.mockito.Mockito.when(tokens.issueToken("alice", "drone")).thenReturn("tok");

        AuthController controller = new AuthController(tokens);
        org.springframework.test.web.servlet.MockMvc mvc = org.springframework.test.web.servlet.setup.MockMvcBuilders
                .standaloneSetup(controller)
                .build();

        mvc.perform(post("/auth/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"alice\",\"type\":\"drone\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("tok"));
    }
}
