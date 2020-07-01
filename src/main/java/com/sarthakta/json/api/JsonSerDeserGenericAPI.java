package com.sarthakta.json.api;

import com.sarthakta.json.vo.GenericHolder;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller
public class JsonSerDeserGenericAPI {

  @Post("/json/generic/accept")
  public HttpResponse<GenericHolder> genericJsonAcceptor(@Body GenericHolder genericHolder) {
    return HttpResponse.ok(genericHolder);
  }
}
