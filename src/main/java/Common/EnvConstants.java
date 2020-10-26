package Common;

public class EnvConstants extends EnvData {

    // Base constants
    public static final String API_URL = getProperty("API_URL");
    public static final String TOKEN = getProperty("TOKEN");

    // Data constants
    public static final String VALID_TRIANGLE = getProperty("VALID_TRIANGLE");
    public static final String VALID_TRIANGLE_DASHES = getProperty("VALID_TRIANGLE_DASHES");
    public static final String INVALID_TRIANGLE_0_SIDE = getProperty("INVALID_TRIANGLE_0_SIDE");
    public static final String INVALID_TRIANGLE_NEGATIVE_SIDE = getProperty("INVALID_TRIANGLE_NEGATIVE_SIDE");
    public static final String INVALID_TRIANGLE_LONG_SIDE = getProperty("INVALID_TRIANGLE_LONG_SIDE");
    public static final String INVALID_TRIANGLE_2_SIDES = getProperty("INVALID_TRIANGLE_2_SIDES");

    // Statuses
    public static final int STATUS_OK = Integer.parseInt(getProperty("STATUS_OK"));
    public static final int STATUS_NOT_FOUND = Integer.parseInt(getProperty("STATUS_NOT_FOUND"));
    public static final int STATUS_UNAUTHORIZED = Integer.parseInt(getProperty("STATUS_UNAUTHORIZED"));
    public static final int STATUS_UNPROCESSIBLE = Integer.parseInt(getProperty("STATUS_UNPROCESSIBLE"));

}
