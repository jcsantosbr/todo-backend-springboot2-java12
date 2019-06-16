package com.jcs.todobackend;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Resource<T> {

  private String url;
  private T content;

  public Resource(T content, String url) {
    this.content = content;
    this.url = url;
  }

  @JsonUnwrapped
  @XmlAnyElement
  public T getContent() {
    return content;
  }

  public String getUrl() {
    return url;
  }

  @Override
  public String toString() {
    return "Resource{" +
        "url='" + url + '\'' +
        ", content=" + content +
        '}';
  }
}
