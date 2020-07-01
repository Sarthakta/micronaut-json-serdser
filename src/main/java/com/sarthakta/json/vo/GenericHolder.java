package com.sarthakta.json.vo;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class GenericHolder {
  private Map<String, Object> properties = new HashMap<>();

  @Override
  public String toString() {
    return "{properties:" + properties + '}';
  }
}
