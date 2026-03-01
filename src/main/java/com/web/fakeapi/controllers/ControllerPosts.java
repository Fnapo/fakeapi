package com.web.fakeapi.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.web.fakeapi.dtos.CrearPostDto;
import com.web.fakeapi.dtos.PostDto;
import com.web.fakeapi.services.ServicePosts;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/posts")
public class ControllerPosts {
  private final ServicePosts servicio;

  public ControllerPosts(ServicePosts servicio) {
    this.servicio = servicio;
  }

  @GetMapping("/todos")
  public String obtenerTodos(Model model) {
    List<PostDto> lista = servicio.obtenerTodos();

    model.addAttribute("listado", lista);

    return "posts/listado";
  }

  @GetMapping("/crear")
  public String crear(Model model) {
    model.addAttribute("nuevo", new CrearPostDto());

    return "posts/crear";
  }

  @PostMapping("/crear")
  public String guardar(@ModelAttribute CrearPostDto nuevo) {
    servicio.guardar(nuevo);

    return "redirect:/posts/todos";
  }

  @GetMapping("/detalles/{id}")
  public String detalles(@PathVariable long id, Model model) {
    PostDto registro = servicio.buscarId(id);

    if (registro == null) {
      return "redirect:/posts/todos";
    }
    model.addAttribute("registro", registro);

    return "posts/detalles";
  }

  @GetMapping("/actualizar/{id}")
  public String actualizar(@PathVariable long id, Model model) {
    PostDto registro = servicio.buscarId(id);

    if (registro == null) {
      return "redirect:/posts/todos";
    }
    model.addAttribute("modificado", registro);

    return "posts/actualizar";
  }

  @PostMapping("/actualizar")
  public String guardar(@ModelAttribute PostDto modificado) {
    servicio.guardar(modificado);

    return "redirect:/posts/todos";
  }

  @DeleteMapping("/borrar/{id}")
  public String borrar(@PathVariable long id, Model model) {
    PostDto registro = servicio.buscarId(id);

    if (registro == null) {
      return "redirect:/posts/todos";
    }
    model.addAttribute("borrado", registro);

    return "/posts/borrar";
  }

}
