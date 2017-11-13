/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.app.services;

import com.example.app.configuration.DatabaseLoader;
import com.example.app.configuration.IProductoService;
import com.example.app.entities.Producto;
import com.example.app.products.ProductRepository;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Gonzalo
 */
@RunWith(SpringRunner.class)
public class ProductoServiceTests {


    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DatabaseLoader databaseLoader;

    @Before
    public void initTest() {
        List<Producto> productos = new ArrayList<>();
        
        Producto p1 = new Producto("hola", "hola", 11);
        Producto p2 = new Producto("aaa", "aaa", 12);
        Producto p3 = new Producto("bbb", "bbb", 13);
        Producto p4 = new Producto("ccc", "ccc", 14);

        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);
        when(productRepository.findAll()).thenReturn(productos);
    }

    @Test
    public void getProductsTest() {
        List<Producto> listado = databaseLoader.findAll();
        assertEquals(listado.size(), 4);
        verify(productRepository).findAll();
    }
    
    @Test
    public void getOneProductTest(){
        Producto product = databaseLoader.getProducto(4);
        assertEquals(product, databaseLoader.getProducto(4));
    }


    @Test
    public void deleteProductTest() {
        databaseLoader.delete(2);
        verify(productRepository, times(1)).delete(2);
    }

    @Test
    public void editProductTest() {
        Producto p3 = new Producto("ccc", "ccc", 70);
        databaseLoader.edit(p3);
        verify(productRepository).save(p3);
    }
    
    @Test
    public void saveProductTest(){
        Producto p5 = new Producto("ddd", "ddd", 15);
        databaseLoader.save(p5);
        verify(productRepository).save(p5);
    }
    
    @Test
    public void findAllTest() {
        ArrayList<Producto> productos = new ArrayList<>();

        for (Producto p : databaseLoader.findAll()) {
            System.out.println(p.getDescripcion());
        }
       
    }

}
