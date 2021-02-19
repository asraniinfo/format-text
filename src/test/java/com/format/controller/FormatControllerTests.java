package com.format.controller;

import com.format.exception.UnknownAlignmentException;
import com.format.service.TextFormatterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class FormatControllerTests {

    private static final String URL = "/align/LEFT/length/10";
    private static final String URL_INVALID_TYPE = "/align/UNKNOWN/length/10";
    @MockBean
    TextFormatterService textFormatterService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FormatController controller;

    @Test
    void validateAlign() throws Exception {
        doReturn("This text \n" +
                "should be \n" +
                "left      \n" +
                "aligned   ").when(textFormatterService).alignText("This text should be left aligned ", "LEFT", 10);

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .content("This text should be left aligned ")
        ).andExpect(status().isOk());
    }

    @Test
    void validateAlignException() throws Exception {
        doThrow(new UnknownAlignmentException("invalid alignment type UNKNOWN")).when(textFormatterService).alignText("This text should be left aligned ", "UNKNOWN", 10);

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL_INVALID_TYPE)
                .content("This text should be left aligned ")
        ).andExpect(status().isBadRequest());
    }
}
