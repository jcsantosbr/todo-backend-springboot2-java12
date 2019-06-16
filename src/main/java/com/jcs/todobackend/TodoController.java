package com.jcs.todobackend;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
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

  private Map<UUID, Resource<Todo>> mapTodos = new HashMap<>();

  @RequestMapping(method = GET)
  public Collection<Resource<Todo>> listAll() {
    return mapTodos.values();
  }

  @RequestMapping(method = GET, value = "/{todo-id}")
  public HttpEntity<Resource<Todo>> get(@PathVariable("todo-id") UUID id) {
    return respond(id);
  }

  @RequestMapping(method = POST)
  public Resource<Todo> add(@RequestBody Todo todo) {
    var id = UUID.randomUUID();
    Resource<Todo> resource = new Resource<>(todo, getHref(id));
    mapTodos.put(id, resource);
    return resource;
  }

  @RequestMapping(method = DELETE)
  public void deleteAll() {
    mapTodos.clear();
  }

  @RequestMapping(method = DELETE, value = "/{todo-id}")
  public void delete(@PathVariable("todo-id") UUID id) {
    mapTodos.remove(id);
  }

  @RequestMapping(method = PATCH, value = "/{todo-id}")
  public HttpEntity<Resource<Todo>> update(@PathVariable("todo-id") UUID id,
                                           @RequestBody Todo updatedTodo) {
    mapTodos.computeIfPresent(id, (key, todoResource) ->
        new Resource<>(todoResource.getContent().merge(updatedTodo), getHref(key)));
    return respond(id);
  }

  private HttpEntity<Resource<Todo>> respond(UUID todoId) {
    return Optional.ofNullable(mapTodos.get(todoId))
        .map(todo -> new ResponseEntity<>(todo, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  private String getHref(UUID id) {
    return linkTo(methodOn(this.getClass()).get(id)).withSelfRel().getHref();
  }

}
