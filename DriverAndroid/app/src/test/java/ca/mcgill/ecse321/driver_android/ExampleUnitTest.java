package ca.mcgill.ecse321.driver_android;

import android.util.Log;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.driver_android.HttpUtils;
import cz.msebera.android.httpclient.Header;


import static org.junit.Assert.*;

public class ExampleUnitTest {

    long id;

    @Before
    public void setup() {
        HttpUtils.post("signIn/" + "Matt" + "/" + "Zolt", new RequestParams(), new JsonHttpResponseHandler() {

        });

        HttpUtils.post("signIn/" + "theDriver" + "/" + "Zolt", new RequestParams(), new JsonHttpResponseHandler() {

        });

        HttpUtils.post("createRoute/3/Montreal/Toronto/1-1-2018/Maybach/theDriver/120", new RequestParams(), new JsonHttpResponseHandler() {

        });

    }

    @Test
    public void createRouteCheck() {

        HttpUtils.get("findRoute/" + "1-1-2018" + "/" + "Montreal" + "/" + "Toronto", new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                String vehicle = "";
                String price = "";
                String list = "";
                for (int i = 0; i < response.length(); i++) {

                    JSONObject jsonobject = null;
                    try {
                        jsonobject = response.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        vehicle = jsonobject.getString("vehicle");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        price = jsonobject.getString("price");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        id = jsonobject.getLong("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    assertNotNull(jsonobject);
                    assertEquals(vehicle, "Maybach");
                    assertEquals(price, "150");


                }

            }

        });


    }

    @Test
    public void showDriversRouteCheck() {



        HttpUtils.get("showDriversRoutes/" + "Matt", new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                String foundDriver = "";


                for (int i = 0; i < response.length(); i++) {
                    JSONObject jsonobject = null;
                    try {
                        jsonobject = response.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {

                        foundDriver = jsonobject.getString("driver");

                    } catch (JSONException e) {


                    }

                }

                assertEquals(foundDriver, "Matt");

            }


        });

    }

    @Test
    public void endRouteCheck() {

        HttpUtils.get("endRoute/" + id, new RequestParams(), new JsonHttpResponseHandler() {

        });

        HttpUtils.get("showDriverRoutes/" + "aaa", new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                long foundID = 0;


                for (int i = 0; i < response.length(); i++) {
                    JSONObject jsonobject = null;
                    try {
                        jsonobject = response.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {

                        foundID = jsonobject.getLong("id");

                    } catch (JSONException e) {


                    }

                }

                assertEquals(foundID,0);

            }


        });

    }

    @After
    public void cleanUp() {

        HttpUtils.get("endRoute/" + id, new RequestParams(), new JsonHttpResponseHandler() {

        });

    }

}

