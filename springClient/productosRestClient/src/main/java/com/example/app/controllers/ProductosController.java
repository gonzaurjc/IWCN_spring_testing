/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.app.controllers;

/**
 *
 * @author Gonzalo
 */

import com.example.app.entities.Producto;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.access.annotation.Secured;

@Controller
public class ProductosController {

        @Autowired
    private static final String URL_API_PRODUCTOS = "http://localhost:8080";
    
    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

@RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping("/home")
    public ModelAndView home() {
        ModelAndView model = new ModelAndView("home");
        return model;
    }
	
	@Secured("ROLE_ADMIN")
    @RequestMapping("/root")
    public ModelAndView root() {
        return new ModelAndView("root");
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/insertar")
    public ModelAndView editar(Producto producto) {
        return new ModelAndView("insertar");
    }

    @PostMapping("/insertar")
    public String insertar(@Valid Producto producto) {
        RestTemplate restTemplate = new RestTemplate();
        String url_insertar = "http://localhost:8080/insertar";
        String response = restTemplate.postForObject(url_insertar, producto, String.class);
        return "redirect:http://localhost:1234/home";
    }
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping("/listado")
    public ModelAndView listado() {
        String url_listado = "http://localhost:8080/listado";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Producto[]> responseEntity = restTemplate.getForEntity(url_listado, Producto[].class);
        Producto[] productos = responseEntity.getBody();

        return new ModelAndView("listado").addObject("productos", productos);
    }
    
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping("/productoIndividual")
    public ModelAndView productoIndividual(@RequestParam int codigo) {
        RestTemplate restTemplate = new RestTemplate();
        //System.out.println(codigo);
        Producto producto = restTemplate.getForObject("http://localhost:8080/productoIndividual/"+codigo , Producto.class);
		
        return new ModelAndView("productoIndividual").addObject("producto", producto);
    }
    
	@Secured("ROLE_ADMIN")
    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam int codigo) {
        RestTemplate restTemplate = new RestTemplate();
        //System.out.println(codigo);
        Producto producto = restTemplate.getForObject("http://localhost:8080/delete/"+codigo , Producto.class);
        return new ModelAndView("listado").addObject("producto", producto);
    }
    
	@Secured("ROLE_ADMIN")
    @RequestMapping("/editar")
    public ModelAndView getProductoeditar(@RequestParam int codigo) {
        RestTemplate restTemplate = new RestTemplate();
        //System.out.println(codigo);
        Producto producto = restTemplate.getForObject("http://localhost:8080/productoIndividual/"+codigo , Producto.class);
		
        return new ModelAndView("editar").addObject("producto", producto);
    }
    
	@Secured("ROLE_ADMIN")
    @PostMapping("/editar")
    public String editar1(@Valid Producto producto) {
        RestTemplate restTemplate = new RestTemplate();
        String url_insertar = "http://localhost:8080/editar";
        String response = restTemplate.postForObject(url_insertar, producto, String.class);
        return "redirect:http://localhost:1234/listado";
    }
}
