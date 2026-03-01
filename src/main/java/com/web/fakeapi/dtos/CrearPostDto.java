package com.web.fakeapi.dtos;

public class CrearPostDto {
  private long userId;
  private String titulo;
  private String cuerpo;

  public CrearPostDto() {

  }

  public CrearPostDto(long userId, String titulo, String cuerpo) {
    this.userId = userId;
    this.titulo = titulo;
    this.cuerpo = cuerpo;
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
