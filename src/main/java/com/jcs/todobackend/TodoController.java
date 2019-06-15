package com.jcs.todobackend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

  @RequestMapping("/")
  public String index() {
    return "ping";
  }

}
