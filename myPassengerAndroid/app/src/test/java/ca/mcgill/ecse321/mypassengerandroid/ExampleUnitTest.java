package ca.mcgill.ecse321.mypassengerandroid;

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
import static org.junit.Assert.*;

import cz.msebera.android.httpclient.Header;

public class ExampleUnitTest {

    long id;

    @Before
    public void setup() {
        HttpUtils.post("signIn/" + "aaa" + "/" + "zzz", new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }

        });

        HttpUtils.post("signIn/" + "driver" + "/" + "zzz", new RequestParams(), new JsonHttpResponseHandler() {

        });

        HttpUtils.post("createRoute/4/Mtl/Tro/1-1-2018/lambo/driver/100", new RequestParams(), new JsonHttpResponseHandler() {

        });

    }

    @Test
    public void findRouteCreateRouteCheck() {

        HttpUtils.get("findRoute/" + "1-1-2018" + "/" + "Mtl" + "/" + "Tro", new RequestParams(), new JsonHttpResponseHandler() {

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
                    assertEquals(vehicle, "lambo");
                    assertEquals(price, "100");


                }

            }

        });


    }

    @Test
    public void joinRouteShowPassengersRouteCheck() {

        HttpUtils.get("joinRoute/" + id + "/" + "aaa", new RequestParams(), new JsonHttpResponseHandler() {

        });

        HttpUtils.get("showPassengersRoutes/" + "aaa", new RequestParams(), new JsonHttpResponseHandler() {

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

                assertEquals(foundID, id);

            }


        });

    }

    @Test
    public void leaveRouteCheck() {

        HttpUtils.get("joinRoute/" + id + "/" + "aaa", new RequestParams(), new JsonHttpResponseHandler() {

        });

        HttpUtils.get("showPassengersRoutes/" + "aaa", new RequestParams(), new JsonHttpResponseHandler() {

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
