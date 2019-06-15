package com.jcs.todobackend;

public class Todo {

  private String url;
  private long id;
  private String title;
  private boolean completed;
  private long order;

  public Todo() {
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
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

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public long getOrder() {
    return order;
  }

  public void setOrder(long order) {
    this.order = order;
  }

  public Todo merge(Todo updatedTodo) {
    if (updatedTodo.title != null) {
      this.title = updatedTodo.title;
    }
    this.completed = updatedTodo.completed;
    return this;
  }
}
