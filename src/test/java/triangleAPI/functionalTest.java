package triangleAPI;

import Common.EnvConstants;
import Common.TriangleAPI;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

public class functionalTest {

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

    @Test(description="Add a triangle")
    public void testAdd() throws Exception {

        // Add a triangle
        JSONObject response = triangleAPI.add(String.format("{\"input\":\"%s\"}",EnvConstants.VALID_TRIANGLE));

        // Get and check fields
        String id = response.getJSONObject("data").getString("id");
        int firstSide = response.getJSONObject("data").getInt("firstSide");
        int secondSide = response.getJSONObject("data").getInt("secondSide");
        int thirdSide = response.getJSONObject("data").getInt("thirdSide");

        Assert.assertEquals(response.getInt("status"), EnvConstants.STATUS_OK);
        Assert.assertNotNull(id);
        Assert.assertEquals(firstSide,3);
        Assert.assertEquals(secondSide,4);
        Assert.assertEquals(thirdSide,5);
    }

    @Test(description="Add a triangle with another separator")
    public void testAddWithSeparator() throws Exception {

        // Add a triangle
        JSONObject response = triangleAPI.add(String.format("{\"separator\":\"-\",\"input\":\"%s\"}",EnvConstants.VALID_TRIANGLE_DASHES));

        // Get and check fields
        String id = response.getJSONObject("data").getString("id");
        int firstSide = response.getJSONObject("data").getInt("firstSide");
        int secondSide = response.getJSONObject("data").getInt("secondSide");
        int thirdSide = response.getJSONObject("data").getInt("thirdSide");

        Assert.assertEquals(response.getInt("status"), EnvConstants.STATUS_OK);
        Assert.assertNotNull(id);
        Assert.assertEquals(firstSide,3);
        Assert.assertEquals(secondSide,4);
        Assert.assertEquals(thirdSide,5);
    }

    @Test(description="Delete a triangle")
    public void testDelete() throws Exception {

        // Add a triangle
        JSONObject response = triangleAPI.add(String.format("{\"input\":\"%s\"}",EnvConstants.VALID_TRIANGLE));

        // Get all triangles
        JSONObject all = triangleAPI.getAll();

        // Get id and remove a triangle
        String id = response.getJSONObject("data").getString("id");
        JSONObject responceDelete = triangleAPI.delete(id);
        Assert.assertEquals(responceDelete.getInt("status"), EnvConstants.STATUS_OK);

        // Check triangles cound
        JSONObject allNew = triangleAPI.getAll();
        Assert.assertEquals(allNew.getJSONArray("data").length(), all.getJSONArray("data").length()-1);
    }

    @Test(description="Get all triangles")
    public void testGetAll() throws Exception {

        // Get all triangles
        JSONObject response = triangleAPI.getAll();
        int size = response.getJSONArray("data").length();

        // Add 3 triangles
        triangleAPI.add(String.format("{\"input\":\"%s\"}",EnvConstants.VALID_TRIANGLE));
        triangleAPI.add(String.format("{\"input\":\"%s\"}",EnvConstants.VALID_TRIANGLE));
        triangleAPI.add(String.format("{\"input\":\"%s\"}",EnvConstants.VALID_TRIANGLE));

        // Get all triangles again
        response = triangleAPI.getAll();
        Assert.assertEquals(response.getInt("status"), EnvConstants.STATUS_OK);
        Assert.assertEquals(response.getJSONArray("data").length(),size+3);
    }

    @Test(description="Get a triangle")
    public void testGet() throws Exception {

        // Add a triangle
        JSONObject response = triangleAPI.add(String.format("{\"input\":\"%s\"}",EnvConstants.VALID_TRIANGLE));

        // Get id and check responses
        String id = response.getJSONObject("data").getString("id");
        JSONObject getResponse = triangleAPI.get(id);
        Assert.assertEquals(getResponse.getInt("status"), EnvConstants.STATUS_OK);
        Assert.assertEquals(response.toString(), getResponse.toString());
    }

    @Test(description="Get perimeter")
    public void testGetPerimeter() throws Exception {

        // Add a triangle
        JSONObject response = triangleAPI.add(String.format("{\"input\":\"%s\"}",EnvConstants.VALID_TRIANGLE));

        // Get id and check responses
        String id = response.getJSONObject("data").getString("id");
        JSONObject perimeterResponse = triangleAPI.getPerimeter(id);
        Assert.assertEquals(perimeterResponse.getInt("status"), EnvConstants.STATUS_OK);
        Assert.assertEquals(perimeterResponse.getJSONObject("data").getInt("result"), 12);
    }

    @Test(description="Get area")
    public void testGetArea() throws Exception {

        // Add a triangle
        JSONObject response = triangleAPI.add(String.format("{\"input\":\"%s\"}",EnvConstants.VALID_TRIANGLE));

        // Get id and check responses
        String id = response.getJSONObject("data").getString("id");
        JSONObject areaResponse = triangleAPI.getArea(id);
        Assert.assertEquals(areaResponse.getInt("status"), EnvConstants.STATUS_OK);
        Assert.assertEquals(areaResponse.getJSONObject("data").getInt("result"), 6);
    }
}

