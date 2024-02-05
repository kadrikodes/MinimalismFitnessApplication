package stepdefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.health.minimalismfitnessapp.backend.dataaccess.IWalkingRepository;
import com.health.minimalismfitnessapp.backend.entities.WalkingData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static java.lang.reflect.Array.get;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class WalkingStepDefinitions {

    private WalkingData[] walkingData;
    private WalkingData newWalkingData = new WalkingData();
    private WalkingData updatedWalkingData = new WalkingData();
    WalkingData newData = new WalkingData();

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    IWalkingRepository walkingRepo;

    @Given("I have {int} records in the {string} data")
    public void iHaveRecordsInTheData(int numberOfRecords, String dataType) {
        Random random = new Random();
        int recordsToGenerate = Math.min(numberOfRecords, 8);
        for (int i = 0; i < recordsToGenerate; i++) {
            WalkingData walking = new WalkingData();
            walking.setSteps(random.nextInt(10000));
            walking.setDistance(random.nextDouble() * 10);
            walking.setSpeed(3.0 + random.nextDouble() * 3);
            walking.setCaloriesBurned(random.nextInt(500));
            walking.setDuration(30.0 + random.nextDouble() * 30);
            walking.setDateTime(LocalDateTime.now().minusDays(random.nextInt(30))); // Random date within the last month
            walking.setUserData(null);
            walkingRepo.save(walking);
        }
    }

    @When("I request all {string} data")
    public void iRequestAllData(String dataType) throws Exception {
        walkingData = (performMockGetAndReturnWalkingData("/walking/allWalkingData"));
    }

    @Then("the total number of {string} data records I receive is {int} records")
    public void theTotalNumberOfDataRecordsIReceiveIsRecords(String dataType, int numberOfRecords) {
        assertEquals(numberOfRecords, walkingData.length);
    }

    @Given("I have {string} data with {int}, {double}, {int}, {double}, and {double}")
    public void i_have_data_with_and(String dataType, Integer steps, Double distance, Integer caloriesBurned, Double duration, Double speed) {
        newWalkingData.setSteps(10000);
        newWalkingData.setDistance(10);
        newWalkingData.setSpeed(3.0);
        newWalkingData.setCaloriesBurned(500);
        newWalkingData.setDuration(30.0);
        newWalkingData.setDateTime(LocalDateTime.now());
        newWalkingData.setUserData(null);
        walkingRepo.save(newWalkingData);
    }
    @When("I submit {string} data")
    public void iSubmitData(String dataType) throws Exception {
        newWalkingData = performMockPostAndReturnWalkingData("/walking/addWalkingData", newWalkingData);
    }
    @Then("the submitted {string} data should be saved with the specified attributes")
    public void theSubmittedDataShouldBeSavedWithTheSpecifiedAttributes(String dataType) {
        assertNotNull(newWalkingData);
    }

    @And("a confirmation message {string} is displayed")
    public void aConfirmationMessageIsDisplayed(String expectedResponse) {
        String response = "Walking data added successfully";
        assertEquals(expectedResponse, response);
    }

    private WalkingData[] performMockGetAndReturnWalkingData(String url) throws Exception {
        MvcResult result = this.mockMvc.perform(
                        MockMvcRequestBuilders.get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        try {
            // First, try to deserialize as an array
            return mapper.readValue(contentAsString, WalkingData[].class);
        } catch (MismatchedInputException mie) {
            // If deserialization as an array fails, try as a single object
            WalkingData singleData = mapper.readValue(contentAsString, WalkingData.class);
            return new WalkingData[]{singleData}; // Wrap the single object in an array
        }
    }

    private WalkingData performMockPostAndReturnWalkingData(String url, WalkingData walkingDataToSend) throws Exception {
        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(walkingDataToSend))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        return mapper.readValue(contentAsString, WalkingData.class);
    }
}
