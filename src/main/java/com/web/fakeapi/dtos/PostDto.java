package com.web.fakeapi.dtos;

public class PostDto {
  private long id;
  private Long userId;
  private String titulo;
  private String cuerpo;

  public PostDto(long id, Long userId, String title, String body) {
    this.id = id;
    this.userId = userId;
    this.titulo = title;
    this.cuerpo = body;
  }

  public long getId() {
    return id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
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
