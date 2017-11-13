/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.app.configuration;

/**
 *
 * @author Gonzalo
 */
import com.example.app.entities.Producto;
import com.example.app.products.ProductRepository;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;


import org.springframework.beans.factory.annotation.Autowired;


@Service
public class DatabaseLoader implements IProductoService {

	@Autowired
	private ProductRepository productoRepository;
	

        @Override
	public void save(Producto producto) {
                productoRepository.save(producto);
	}

        @Override
	public void delete(int codigo) {
		productoRepository.delete(codigo);
	}

        @Override
	public Producto getProducto(int codigo) {
		return productoRepository.findOne(codigo);
	}
	
        @Override
	public void edit(Producto producto) {
		productoRepository.save(producto);
	}
	
        @Override
	public List<Producto> findAll() {
		ArrayList<Producto> productos = new ArrayList<>();
	
		for (Producto p : productoRepository.findAll()) {
			productos.add(p);
		}		
		return productos;
	}	
}

