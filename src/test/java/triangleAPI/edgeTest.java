package triangleAPI;

import Common.EnvConstants;
import Common.TriangleAPI;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class edgeTest {
    TriangleAPI triangleAPI;

    @BeforeClass
    public void initAPI() {
        triangleAPI = new TriangleAPI();
    }

    @BeforeMethod
    public void removeExistingTriangles() throws Exception {

        JSONObject all = triangleAPI.getAll();
        for (int i=0; i<all.getJSONArray("data").length(); i++) {
            triangleAPI.delete(all.getJSONArray("data").getJSONObject(i).getString("id"));
        }
    }

    @Test(description="Add over 10 triangles")
    public void testAddOver10Triangles() throws Exception {

        // Add 10 triangles
        for (int i=0;i<10;i++) {
            triangleAPI.add(String.format("{\"input\":\"%s\"}",EnvConstants.VALID_TRIANGLE));
        }

        // Add the 11th triangle
        JSONObject response =triangleAPI.add(String.format("{\"input\":\"%s\"}",EnvConstants.VALID_TRIANGLE));

        Assert.assertEquals(response.getInt("status"),EnvConstants.STATUS_UNPROCESSIBLE);
        Assert.assertEquals(response.getJSONObject("data").getString("message"),"Limit exceeded");
    }

    @Test(description="Add a triangle with a long side")
    public void testAddLongSideTriangle() throws Exception {

        // Add a triangle
        JSONObject response =triangleAPI.add(String.format("{\"input\":\"%s\"}",EnvConstants.INVALID_TRIANGLE_LONG_SIDE));

        Assert.assertEquals(response.getInt("status"),EnvConstants.STATUS_UNPROCESSIBLE);
        Assert.assertEquals(response.getJSONObject("data").getString("error"),"Unprocessable Entity");
    }

    @Test(description="Add a triangle with a negative side")
    public void testAddTriangleWithNegativeSide() throws Exception {

        // Add a triangle
        JSONObject response =triangleAPI.add(String.format("{\"input\":\"%s\"}",EnvConstants.INVALID_TRIANGLE_NEGATIVE_SIDE));

        Assert.assertEquals(response.getInt("status"),EnvConstants.STATUS_UNPROCESSIBLE);
        Assert.assertEquals(response.getJSONObject("data").getString("error"),"Unprocessable Entity");
    }

    @Test(description="Add a triangle with zero-side")
    public void testAddTriangleWithZeroSide() throws Exception {

        // Add a triangle
        JSONObject response =triangleAPI.add(String.format("{\"input\":\"%s\"}",EnvConstants.INVALID_TRIANGLE_0_SIDE));

        Assert.assertEquals(response.getInt("status"),EnvConstants.STATUS_UNPROCESSIBLE);
        Assert.assertEquals(response.getJSONObject("data").getString("error"),"Unprocessable Entity");
    }

    @Test(description="Add a triangle with 2 sides")
    public void testAddTriangleWith2Sides() throws Exception {

        // Add a triangle
        JSONObject response =triangleAPI.add(String.format("{\"input\":\"%s\"}",EnvConstants.INVALID_TRIANGLE_2_SIDES));

        Assert.assertEquals(response.getInt("status"), EnvConstants.STATUS_UNPROCESSIBLE);
        Assert.assertEquals(response.getJSONObject("data").getString("error"),"Unprocessable Entity");
    }

    @Test(description="Get an unexisting triangle")
    public void testGetUnexistingTriangle() throws Exception {

        JSONObject response =triangleAPI.get("unexisting-id");
        Assert.assertEquals(response.getInt("status"), EnvConstants.STATUS_NOT_FOUND);
    }

    @Test(description="Perform a request using invalid token")
    public void testInvalidTokenCheck() throws Exception {

        // Create a local instance of triangle API with an invalid token
        TriangleAPI localTriangleAPI = new TriangleAPI(EnvConstants.API_URL,"INVALID_TOKEN");

        // Try to perform an operation
        JSONObject response = localTriangleAPI.add(String.format("{\"input\":\"%s\"}",EnvConstants.VALID_TRIANGLE));

        // Check the status code
        Assert.assertEquals(response.getInt("status"), EnvConstants.STATUS_UNAUTHORIZED);
    }
}
