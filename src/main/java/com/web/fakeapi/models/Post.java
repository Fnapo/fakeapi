package com.web.fakeapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Post {
  private long id;
  private long userId;
  @JsonProperty("title")
  private String titulo;
  @JsonProperty("body")
  private String cuerpo;

  public Post() {

  }

  public Post(long id, long userId, String title, String body) {
    this.id = id;
    this.userId = userId;
    this.titulo = title;
    this.cuerpo = body;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getCuerpo() {
    return cuerpo;
  }

  public void setCuerpo(String cuerpo) {
    this.cuerpo = cuerpo;
  }
}
