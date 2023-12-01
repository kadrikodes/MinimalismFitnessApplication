package com.health.minimalismfitnessapp.integrationtests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.health.minimalismfitnessapp.dataaccess.IUserRepository;
import com.health.minimalismfitnessapp.entities.UserData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings({"NewClassNamingConvention", "ResultOfMethodCallIgnored", "unused"})
@Sql("classpath:test-data-user.sql")
@SpringBootTest
@DirtiesContext(classMode=DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.sql.init.mode=never"})

public class UserIntegrationTestMockHTTP {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    IUserRepository iUserRepository;


    UserData existingUser;


    UserData newUser;

    private final ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    void setUp() {
        if (existingUser == null)
            existingUser = new UserData("Esra", 1L, 170.0, 60.0, LocalDate.of(1980, 6, 19), UserData.FEMALE);
        if (existingUser.getId() == null)
            this.iUserRepository.save(existingUser);
        newUser = new UserData("Karen", 1L, 170.0, 60.0, LocalDate.of(1980, 6, 18), UserData.FEMALE);
    }

    @Test
    void testGettingAllUserRecords() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        MvcResult result =
                (this.mockMvc.perform(MockMvcRequestBuilders.get("/users")))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        String contentAsJson = result.getResponse().getContentAsString();
        UserData[] actualUserData = mapper.readValue(contentAsJson, UserData[].class);
        assertEquals("Esra", actualUserData[0].getName());
        assertEquals("Divin", actualUserData[1].getName());
        assertEquals("Sam", actualUserData[2].getName());
        assertEquals("Kadri", actualUserData[3].getName());
        assertEquals("Rais", actualUserData[4].getName());

    }

    @Test
    public void testGetUserDataById() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        long userId = 1;
        MvcResult result =
                (this.mockMvc.perform(MockMvcRequestBuilders.get("/users/userId/" + userId)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();

        String contentAsJson = result.getResponse().getContentAsString();
        UserData actualUserId = mapper.readValue(contentAsJson, UserData.class);
        assertEquals("Esra", actualUserId.getName());
    }


    @Test
    void testAddingNewUserRecords() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        UserData newUserData = new UserData("mike", null, 170.0, 60.0, LocalDate.of(1980, 2, 19), UserData.MALE);
        String json = mapper.writeValueAsString(newUserData);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/users/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andExpect(status().isCreated())
                        .andReturn();

        String contentAsJson = result.getResponse().getContentAsString();
        UserData addedUser = mapper.readValue(contentAsJson, UserData.class);
        assertEquals("mike", addedUser.getName());
        assertEquals(UserData.MALE, addedUser.getGender());

}



    @Test
    void updateUserRecord() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        UserData updatedUserData = iUserRepository.findById(1L).orElse(null);
        assert updatedUserData != null;
        updatedUserData.setName("karen");
        String json = mapper.writeValueAsString(updatedUserData);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/users/updateUser/"+1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andExpect(status().isOk())
                        .andReturn();
        String contentAsJson = result.getResponse().getContentAsString();
        UserData updatedUserRecord = mapper.readValue(contentAsJson, UserData.class);
        assertEquals("karen", updatedUserRecord.getName());
    }

    @Test
    void deleteUserRecord() throws Exception {
        iUserRepository.findById(1L).orElse(null);
        mockMvc.perform(delete("/users/deleteUser/"+ 1L)
                        .contentType(MediaType.APPLICATION_JSON))

                        .andExpect(status().isOk())
                         .andReturn();

        UserData checkingUsersDataAfterDeletion = iUserRepository.findById(1L).orElse(null);
        assertNull(checkingUsersDataAfterDeletion, "User Record Deleted");
    }

}
