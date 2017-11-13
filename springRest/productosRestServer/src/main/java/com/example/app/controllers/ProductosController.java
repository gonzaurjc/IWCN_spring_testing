/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.app.controllers;

import com.example.app.configuration.IProductoService;
import com.example.app.entities.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gonzalo
 */
@RestController
public class ProductosController {

    @Autowired
    private IProductoService productoService;

    //OBTENER TODOS LOS PRODUCTOS
    
    @RequestMapping(value = "/listado", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }

    //OBTENER UN SOLO PRODUCTO POR CODIGO
    @RequestMapping(value = "/productoIndividual/{codigo}", method = RequestMethod.GET)
    public ResponseEntity<Producto> getProducto(@PathVariable int codigo) {
        Producto producto = productoService.getProducto(codigo);
        ResponseEntity<Producto> response;
        if (producto == null) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity<>(producto, HttpStatus.OK);
        }
        return response;
    }

    //INSERTAR UN PRODUCTO
    @RequestMapping(value = "/insertar", method = RequestMethod.POST)
    public ResponseEntity<Boolean> insertar(@RequestBody Producto producto) {
        productoService.save(producto);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    //EDITAR UN PRODUCTO
    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public ResponseEntity<Boolean> editar(@RequestBody Producto producto) {
        productoService.edit(producto);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }
    
    //ELIMINAR UN PRODUCTO
    
    @RequestMapping(value = "/delete/{codigo}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int codigo) {
        productoService.delete(codigo);
    }

}
