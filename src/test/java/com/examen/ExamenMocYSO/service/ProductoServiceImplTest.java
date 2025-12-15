package com.examen.ExamenMocYSO.service;


import com.examen.ExamenMocYSO.entity.Producto;
import com.examen.ExamenMocYSO.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    ProductoRepository productoRepository;

    @InjectMocks
    ProductoServiceImpl servicio;

    @Test
    void findAllProductosTest() {
        Producto p1 = new Producto(
                1L,
                "Producto1",
                "Descripcion1",
                "Categoria1",
                10f,
                LocalDate.now(),
                "Obs1",
                5
        );

        Producto p2 = new Producto(
                2L,
                "Producto2",
                "Descripcion2",
                "Categoria2",
                20f,
                LocalDate.now(),
                "Obs2",
                10
        );

        List<Producto> productosMock = new ArrayList<>();
        productosMock.add(p1);
        productosMock.add(p2);

        Mockito.when(productoRepository.findAll())
                .thenReturn(productosMock);


        List<Producto> resultado = servicio.findAllProductos();


        assertEquals(2, resultado.size());
        assertEquals("Producto1", resultado.get(0).getNombre());

        Mockito.verify(productoRepository, Mockito.times(1)).findAll();
    }

    @Test
    void findProductoTest() {
        Producto producto = new Producto(
                1L,
                "Producto1",
                "Descripcion1",
                "Categoria1",
                10f,
                LocalDate.now(),
                "Obs1",
                5
        );

        Mockito.when(productoRepository.findById(1L))
                .thenReturn(Optional.of(producto));


        Optional<Producto> resultado = servicio.findProducto(1L);


        assertTrue(resultado.isPresent());
        assertEquals("Producto1", resultado.get().getNombre());
        assertEquals(10f, resultado.get().getPrecio());

        Mockito.verify(productoRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void addProductoTest() {
        Producto producto = new Producto(
                null,
                "NuevoProducto",
                "Descripcion nueva",
                "Categoria1",
                15f,
                LocalDate.now(),
                "Observaciones",
                3
        );

        Producto productoGuardado = new Producto(
                1L,
                "NuevoProducto",
                "Descripcion nueva",
                "Categoria1",
                15f,
                LocalDate.now(),
                "Observaciones",
                3
        );

        Mockito.when(productoRepository.save(producto))
                .thenReturn(productoGuardado);


        Producto resultado = servicio.addProducto(producto);


        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("NuevoProducto", resultado.getNombre());

        Mockito.verify(productoRepository, Mockito.times(1)).save(producto);
    }

}