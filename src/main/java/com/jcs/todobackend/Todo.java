package com.jcs.todobackend;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Todo {

  private long id;
  private String title;
  private boolean completed;
  private Long order;

  @JsonCreator
  public Todo(@JsonProperty("title") String title,
              @JsonProperty(value = "completed") boolean completed,
              @JsonProperty(value = "order") long order) {
    this.title = title;
    this.completed = completed;
    this.order = order;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public boolean isCompleted() {
    return completed;
  }

  public long getOrder() {
    return order;
  }

  public Todo merge(Todo updatedTodo) {
    if (updatedTodo.title != null) {
      this.title = updatedTodo.title;
    }
    this.completed = updatedTodo.completed;
    if (updatedTodo.order != null) {
      this.order = updatedTodo.order;
    }
    return this;
  }
}
