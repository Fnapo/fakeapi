package com.web.fakeapi.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

import com.web.fakeapi.dtos.CrearPostDto;
import com.web.fakeapi.dtos.PostDto;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
public class ServicePosts {
  private final static String url = "https://jsonplaceholder.typicode.com/posts";

  // Crear cliente HTTP reutilizable
  private final static HttpClient clientePosts = HttpClient.newBuilder()
      .connectTimeout(Duration.ofSeconds(10)) // Tiempo máximo de conexión
      .build();

  private final static ObjectMapper mapper = new ObjectMapper();

  public List<PostDto> obtenerTodos() {
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(url))
          .timeout(Duration.ofSeconds(5)) // Tiempo máximo de espera de respuesta
          .GET()
          .build();

      HttpResponse<String> respuesta = clientePosts.send(request, HttpResponse.BodyHandlers.ofString());

      if (respuesta.statusCode() < 300) {
        List<PostDto> salida = mapper.readValue(respuesta.body(), new TypeReference<List<PostDto>>() {
        });

        return salida;
      }
    } catch (Exception e) {
      System.err.println(
          "Error: La petición GET ha lanzado una excepción -> " + e.getMessage() + " A las: "
              + LocalDateTime.now());
    }

    return null;
  }

  public PostDto buscarId(long id) {
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(url + "/" + id))
          .timeout(Duration.ofSeconds(5)) // Tiempo máximo de espera de respuesta
          .GET()
          .build();

      HttpResponse<String> respuesta = clientePosts.send(request, HttpResponse.BodyHandlers.ofString());

      if (respuesta.statusCode() < 300) {
        PostDto salida = mapper.readValue(respuesta.body(), PostDto.class);

        return salida;
      }
    } catch (Exception e) {
      System.err.println(
          "Error: La petición GET ha lanzado una excepción -> " + e.getMessage() + " A las: "
              + LocalDateTime.now());
    }

    return null;
  }

  public void guardar(PostDto modificado) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      String jsonBody = mapper.writeValueAsString(modificado);
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI(url + "/" + modificado.getId()))
          .header("Content-Type", "application/json")
          .method("PATCH", BodyPublishers.ofString(jsonBody))
          .build();

      HttpResponse<String> respuesta = clientePosts.send(request, HttpResponse.BodyHandlers.ofString());

      if (respuesta.statusCode() < 300) {
        System.out.println("Correcto");
      } else {
        System.out.println("Error");
      }
    } catch (Exception e) {
      System.err.println(
          "Error: La petición Post ha lanzado una excepción -> " + e.getMessage() + " A las: "
              + LocalDateTime.now());
    }
  }

  public void guardar(CrearPostDto nuevo) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      String jsonBody = mapper.writeValueAsString(nuevo);
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI(url))
          .header("Content-Type", "application/json")
          .POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8))
          .build();

      HttpResponse<String> respuesta = clientePosts.send(request, HttpResponse.BodyHandlers.ofString());

      if (respuesta.statusCode() < 300) {
        System.out.println("Correcto");
      } else {
        System.out.println("Error");
      }
    } catch (Exception e) {
      System.err.println(
          "Error: La petición Post ha lanzado una excepción -> " + e.getMessage() + " A las: "
              + LocalDateTime.now());
    }
  }

  public void borrar(long id) {
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI(url + "/" + id))
          .DELETE()
          .build();

      HttpResponse<String> respuesta = clientePosts.send(request, HttpResponse.BodyHandlers.ofString());

      if (respuesta.statusCode() < 300) {
        System.out.println("Correcto");
      } else {
        System.out.println("Error");
      }
    } catch (Exception e) {
      System.err.println(
          "Error: La petición Post ha lanzado una excepción -> " + e.getMessage() + " A las: "
              + LocalDateTime.now());
    }
  }
}
