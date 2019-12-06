package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.BeforeClass;
import org.junit.Test;
import play.Application;
import play.api.test.FakeRequest;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

//Sorry for the basic tests but with the limited time it wasnt enough time to mock the ebean finder
//I would have written it but due the simplicity of the code it was hard to write it in testable chunks as most of the
//important logic is one line long. I didnt have time to mock the db calls so the tests actually use the live DB which
//is a big no no but since this is meant to be a "PoC" i took a few shortcuts
public class AuditControllerTest extends WithApplication {


    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }


    @Test
    public void testGetAudit() {

        Helpers.running(Helpers.fakeApplication(), () -> {
            Http.RequestBuilder request = new Http.RequestBuilder()
                    .method(GET)
                    .uri("/audit/1");

            Result result = route(app, request);
            assertEquals(OK, result.status());
            assertNotNull(result.body());
        });

    }

    @Test
    public void testPostAudit() {

        Helpers.running(Helpers.fakeApplication(), () -> {

            String json = "{\n" +
                    "\"request_time\":\"2019-12-06T11:58:08.398Z\",\n" +
                    "\"user_id\":1,\n" +
                    "\"request_body\":\"{\\\"data\\\":\\\"data\\\"}\",\n" +
                    "\"url\":\"http://www.dave.com\",\n" +
                    "\"http_status\":200\n" +
                    "}";

            Http.RequestBuilder request = new Http.RequestBuilder()
                    .method(POST)
                    .uri("/audit")
                    .bodyJson(Json.parse(json));

            Result result = route(app, request);
            assertEquals(OK, result.status());
            assertNotNull(result.body());
        });

    }

}