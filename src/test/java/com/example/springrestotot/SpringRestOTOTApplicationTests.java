package com.example.springrestotot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringRestOTOTApplication.class)
@WebAppConfiguration
class SpringRestOTOTApplicationTests {

    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webContext)
                .build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void getQuotes() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/quotes")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Quote[] quoteList = mapFromJson(content, Quote[].class);
        assertTrue(quoteList.length > 0);

    }

    @Test
    public void createQuote() throws Exception {
        Quote quote = new Quote();
        quote.setContent("Change your thoughts and you change the world.");
        quote.setAuthor("Norman Vincent Peale");
        String inputJson = mapToJson(quote);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/quotes")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Quote newQuote = mapFromJson(content, Quote.class);
        assertEquals(quote.getContent(), newQuote.getContent());
        assertEquals(quote.getAuthor(), newQuote.getAuthor());

    }

    @Test
    public void getQuote() throws Exception {
        Quote quote = new Quote();
        quote.setContent("The way I see it, if you want the rainbow, you gotta put up with the rain");
        quote.setAuthor("Dolly Parton");

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/quotes/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Quote savedQuote = mapFromJson(content, Quote.class);
        assertEquals(quote.getContent(), savedQuote.getContent());
        assertEquals(quote.getAuthor(), savedQuote.getAuthor());

    }

    @Test
    public void updateQuote() throws Exception {
        Quote quote = new Quote();
        quote.setContent("Change your thoughts and you change the world.");
        quote.setAuthor("Norman Vincent Peale");
        String inputJson = mapToJson(quote);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.put("/quotes/2")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Quote newQuote = mapFromJson(content, Quote.class);
        assertEquals(quote.getContent(), newQuote.getContent());
        assertEquals(quote.getAuthor(), newQuote.getAuthor());

    }

    @Test
    public void deleteQuote() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/quotes/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }
}
