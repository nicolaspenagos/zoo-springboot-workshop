package co.edu.icesi.zoo.controller;

import co.edu.icesi.zoo.constant.AnimalErrorCode;
import co.edu.icesi.zoo.constant.AnimalErrorMsgs;
import co.edu.icesi.zoo.constant.BurmesePython;
import co.edu.icesi.zoo.dto.AnimalDTO;
import co.edu.icesi.zoo.error.exception.AnimalException;
import co.edu.icesi.zoo.mapper.AnimalMapper;
import co.edu.icesi.zoo.mapper.AnimalMapperImpl;
import co.edu.icesi.zoo.service.AnimalService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class AnimalControllerTest {

    private static final String ANIMAL_TEST_UUID = "5b666754-e217-4775-9c95-352ebb0673cb";
    private static final String ANIMAL_TEST_NAME = "Asmodeus";
    private static final String ANIMAL_TEST_ARRIVAL_DATE = "2020-09-24T23:37:42";
    private AnimalDTO dummyAnimal;

    private AnimalController animalController;
    private AnimalMapper animalMapper;
    private AnimalService animalService;

    public void setupScenary1() {
        //A random python with valid average characteristics and no parents
        dummyAnimal = new AnimalDTO(ANIMAL_TEST_UUID, ANIMAL_TEST_NAME, BurmesePython.MALE, BurmesePython.MAX_WEIGHT / 2, BurmesePython.MAX_AGE / 2, BurmesePython.MAX_HEIGHT / 2, ANIMAL_TEST_ARRIVAL_DATE, null, null);
    }


    @BeforeEach
    public void init() {

        animalMapper = new AnimalMapperImpl();
        animalService = mock(AnimalService.class);
        animalController = new AnimalController(animalService, animalMapper);

    }

    @Test
    public void wrongNameLengthsTest() {

        int[] nameLengths = {0, 121};

        for (int nameLength : nameLengths) {

            setupScenary1();
            dummyAnimal.setName(generateRandomOnlyLettersString(nameLength));

            try {
                animalController.createAnimal(dummyAnimal);
                fail();
            } catch (AnimalException exception) {
                verifyAnimalException(AnimalErrorMsgs.WRONG_NAME_FORMAT_MSG, AnimalErrorCode.CODE_01, exception);
            }

        }

    }

    @Test
    public void invalidNameCharactersTest() {

        //Testing invalid characters
        String[] invalidNames = {"-Burmese", "_Burmese", ".Burmese"};

        for (String invalidName : invalidNames) {

            setupScenary1();
            dummyAnimal.setName(generateRandomOnlyLettersString(20) + invalidName);

            try {
                animalController.createAnimal(dummyAnimal);
                fail();
            } catch (AnimalException exception) {
                verifyAnimalException(AnimalErrorMsgs.WRONG_NAME_FORMAT_MSG, AnimalErrorCode.CODE_01, exception);
            }

        }

    }

    @Test
    public void futureArrivalDateTest(){

        //Second, minute, hour, day, month and year in ms
        long[] times = {1000L, 60000L, 3600000L, 86400000L, 2628000000L, 31540000000L};
        long currentTime = System.currentTimeMillis();
        ZoneId zoneId = ZoneId.systemDefault();

        for(long time : times){

            setupScenary1();
            long futureArrivalTime = currentTime + time;
            LocalDateTime arrivalDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(futureArrivalTime), zoneId);
            dummyAnimal.setArrivalDate(arrivalDate.toString());

            try {
                animalController.createAnimal(dummyAnimal);
                fail();
            } catch (AnimalException exception) {
                verifyAnimalException(AnimalErrorMsgs.IMPOSSIBLE_DATE_MSG, AnimalErrorCode.CODE_02, exception);
            }
            
        }
    }

    /*
     * Utils
     */

    public void verifyAnimalException(String correctMSG, AnimalErrorCode correctCode, AnimalException exception) {

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertNotNull(exception.getError());
        assertEquals(correctMSG, exception.getError().getMessage());
        assertEquals(correctCode, exception.getError().getCode());

    }

    public String generateRandomOnlyLettersString(int length) {

        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);

    }


}
