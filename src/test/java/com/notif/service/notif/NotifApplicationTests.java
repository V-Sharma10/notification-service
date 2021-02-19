package com.notif.service.notif;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notif.service.notif.controllers.MessageController;
import com.notif.service.notif.models.request.MessageRequestModel;
import com.notif.service.notif.services.CustomUserDetailsService;
import com.notif.service.notif.services.MessageService;
import com.notif.service.notif.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MessageController.class)
@ContextConfiguration(classes=NotifApplication.class)
class NotifApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JwtUtil jwtUtil;
    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private MessageService messageService;

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void sendMessageTest() throws Exception {

        MessageRequestModel testMsg3 = MessageRequestModel.builder()
                .message("This is a test message").phoneNumber("9999234562").build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/v1/sms/send").accept(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(testMsg3))
                .contentType(MediaType.APPLICATION_JSON_VALUE);

         mockMvc.perform(requestBuilder).andExpect(status().isOk())
                 .andExpect(content().contentType("application/json"));

    }


    @Test
    public void getMessageTest() throws Exception {

       mockMvc.perform(get("/v1/sms/{id}","abnTest"))
                .andExpect(status().is5xxServerError())
                .andReturn();

    }
}
