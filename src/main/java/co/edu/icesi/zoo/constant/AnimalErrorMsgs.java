package co.edu.icesi.zoo.constant;

public class AnimalErrorMsgs {

    public final static String IMPOSSIBLE_DATE_MSG = "Impossible date: the arrival date cannot be later than the current date";
    public final static String WRONG_DATE_FORMAT_MSG = "Wrong date format: text cannot be parsed to a date, make sure you are using ISO 8601 yyyy-mm-ddThh:mm:ss format";
    public final static String WRONG_NAME_FORMAT_MSG = "Wrong name format: name cannot exceed 120 digits length and can only contain letters and spaces";
    public final static String WRONG_PYTHON_CHARACTERISTICS_MSG = String.format("Wrong specie characteristics: age must be between 0 up to %1$S (years), height between 0 and %2$S (meters) and weight between 0 and %3$S (kg) (0 is always excluded)", BurmesePython.MAX_AGE, BurmesePython.MAX_HEIGHT, BurmesePython.MAX_WEIGHT);
    public final static String NOT_UNIQUE_NAME_MSG = "Not unique name: the name of the animal must be unique";
    public final static String WRONG_MOTHER_SEX = "Wrong mother sex: mother has to be female (sex = 'F')";
    public final static String WRONG_FATHER_SEX = "Wrong father sex: father has to be male (sex='M')";
    public final static String INVALID_ID = "Invalid Id: you have entered an id that cannot be parsed to UUID";

}
