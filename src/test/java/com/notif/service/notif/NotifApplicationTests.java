package com.notif.service.notif;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notif.service.notif.controllers.MessageController;
import com.notif.service.notif.exception.InvalidRequestException;
import com.notif.service.notif.models.MessageDtoModel;
import com.notif.service.notif.models.request.MessageRequestModel;
import com.notif.service.notif.services.MessageService;
import com.notif.service.notif.utils.enums.ErrorCodes;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MessageController.class)
@ContextConfiguration(classes=NotifApplication.class)
class NotifApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    MessageRequestModel testMsg1 = MessageRequestModel.builder().message("asdfghjk").phoneNumber("12344567890").build();

    MessageRequestModel testMsg2 = MessageRequestModel.builder().message("").phoneNumber("9110970052").build();

    MessageRequestModel testMsg3 = MessageRequestModel.builder().message("This is a test message").phoneNumber("9110970052").build();

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
        Mockito.when(messageService.sendMsg(testMsg1)).thenThrow(new InvalidRequestException("Invalid Phone Number", ErrorCodes.BAD_REQUEST_ERROR));
        Mockito.when(messageService.sendMsg(testMsg2)).thenThrow(new InvalidRequestException("Invalid Message", ErrorCodes.BAD_REQUEST_ERROR));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/v1/sms/send").accept(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(testMsg1))
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.out.println(response.getContentAsString());

        Assert.assertEquals(new InvalidRequestException("Invalid Phone Number", ErrorCodes.BAD_REQUEST_ERROR),response);
    }





    MessageDtoModel msg = MessageDtoModel.builder().id("abc")
            .phoneNumber("9876543678")
            .message("qwertyuioplkjhgfdsazxcvbnm").createdAt(new Date())
            .status(0).build();

    @Test
    public void getMessageTest() throws Exception {
        Mockito.when(messageService.getDetailsById("abc"))
                .thenReturn(Optional.ofNullable(msg));

        MvcResult result = mockMvc.perform(get("/v1/sms/{id}","abc"))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.out.println(" Response :"+response.getContentAsString());
        Assert.assertEquals(Optional.ofNullable(msg), response.getContentAsString());

    }
}
