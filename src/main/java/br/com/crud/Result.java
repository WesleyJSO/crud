package br.com.crud;

import br.com.crud.entity.DomainEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result<T extends DomainEntity> {

  private final Map<String, Object> results;
  private boolean success;
  private String message;

  public Result() {
    success = false;
    results = new HashMap<>();
  }

  public boolean isError() {
    return !success;
  }

  public void setError(String message) {
    success = false;
    this.message = message;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(String message) {
    success = true;
    this.message = message;
  }

  public void setSuccess() {
    success = true;
  }

  public void setResults(String key, Object value) {
    results.put(key, value);
  }

  public String getMessage() {
    return message;
  }

  public T getResult(String key) {
    return (T) results.get(key);
  }

  public List<T> getResults(String key) {
    return (List<T>) results.get(key);
  }
}
