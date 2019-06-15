package com.jcs.todobackend;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.web.bind.annotation.CrossOrigin;
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

  @RequestMapping(method = GET)
  public String index() {
    return "ping";
  }

  @RequestMapping(method = POST)
  public Todo add(@RequestBody Todo todo) {
    return todo;
  }

  @RequestMapping(method = DELETE)
  public void deleteAll() {}

}
