package com.sarthakta.json.api;

import java.util.Optional;
import javax.inject.Inject;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sarthakta.json.vo.GenericHolder;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;

@MicronautTest
public class jsonSerDeserGenericAPITest {
  @Inject
  @Client("/")
  HttpClient client;

  @Inject
  ObjectMapper objectMapper;

  @Test
  void genericJsonAccpetTest() {

    String jsonRequestString = "{\"properties\":{" + "\"id\": \"0001\"," + "\"type\": \"donut\","
        + "\"name\": \"Cake\"," + "\"ppu\": 0.55," + "\"batters\":" + "{" + "\"batter\":" + "["
        + "{ \"id\": \"1001\", \"type\": \"Regular\" },"
        + "{ \"id\": \"1002\", \"type\": \"Chocolate\" },"
        + "{ \"id\": \"1003\", \"type\": \"Blueberry\" },"
        + "{ \"id\": \"1004\", \"type\": \"Devil's Food\" }" + "]" + "}," + "\"topping\":" + "["
        + "{ \"id\": \"5001\", \"type\": \"None\" },"
        + "{ \"id\": \"5002\", \"type\": \"Glazed\" },"
        + "{ \"id\": \"5005\", \"type\": \"Sugar\" },"
        + "{ \"id\": \"5007\", \"type\": \"Powdered Sugar\" },"
        + "{ \"id\": \"5006\", \"type\": \"Chocolate with Sprinkles\" },"
        + "{ \"id\": \"5003\", \"type\": \"Chocolate\" },"
        + "{ \"id\": \"5004\", \"type\": \"Maple\" }" + "]" + "}" + "}";

    HttpResponse<GenericHolder> response =
        client.toBlocking().exchange(HttpRequest.POST("/json/generic/accept", jsonRequestString)
            .contentType(MediaType.APPLICATION_JSON), GenericHolder.class);

    Assertions.assertEquals(response.getStatus(), HttpStatus.OK);
    Optional<GenericHolder> body = response.getBody(GenericHolder.class);
    GenericHolder genericHolder = body.get();
    try {
      String responseJSONString = objectMapper.writeValueAsString(genericHolder);
      JSONAssert.assertEquals(responseJSONString, jsonRequestString.trim(),
          JSONCompareMode.LENIENT);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    }


  }

}
