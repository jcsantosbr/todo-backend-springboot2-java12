package com.jcs.todobackend;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(
    methods = {POST, GET, OPTIONS, DELETE, PATCH},
    maxAge = 3600,
    allowedHeaders = {"x-requested-with", "origin", "content-type", "accept"},
    origins = "*"
)
@RestController
@RequestMapping("/todos")
public class TodoController {

  private List<Todo> todos = new ArrayList<>();

  @RequestMapping(method = GET)
  public Collection<Todo> listAll() {
    return todos;
  }

  @RequestMapping(method = GET, value = "/{todo-id}")
  public HttpEntity<Todo> get(@PathVariable("todo-id") long id) {
    ResponseEntity<Todo> entity = todos.stream()
        .filter(t -> t.getId() == id)
        .findFirst()
        .map(todo -> new ResponseEntity<>(todo, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    return entity;
  }

  @RequestMapping(method = POST)
  public Todo add(@RequestBody Todo todo) {
    todo.setId(todos.size());
    todo.setUrl(getHref(todo));
    todos.add(todo);
    return todo;
  }

  @RequestMapping(method = DELETE)
  public void deleteAll() {
    todos.clear();
  }

  private String getHref(Todo todo) {
    return linkTo(methodOn(this.getClass()).get(todo.getId())).withSelfRel().getHref();
  }

}
