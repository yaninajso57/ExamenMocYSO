package com.examen.ExamenMocYSO.controller;

import com.examen.ExamenMocYSO.entity.Producto;
import com.examen.ExamenMocYSO.service.ProductoService;
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
class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController controller;


    @Test
    void addProductoTest() {
        Producto producto = new Producto(
                1L, "Nuevo", "Desc", "Cat",
                20f, LocalDate.now(), null, 2
        );

        Mockito.when(productoService.addProducto(producto))
                .thenReturn(producto);

        Producto resultado = controller.addProducto(producto);

        assertNotNull(resultado);
        assertEquals("Nuevo", resultado.getNombre());

        Mockito.verify(productoService, Mockito.times(1)).addProducto(producto);
    }

    @Test
    void getProductosTest() {
        Producto p = new Producto(
                1L, "Producto1", "Desc", "Cat",
                10f, LocalDate.now(), null, 1
        );

        List<Producto> productos = new ArrayList<>();
        productos.add(p);

        Mockito.when(productoService.findAllProductos())
                .thenReturn(productos);

        List<Producto> resultado = controller.getProductos(0.0f, "");

        assertEquals(1, resultado.size());
        Mockito.verify(productoService, Mockito.times(1)).findAllProductos();
    }

    @Test
    void getProductoTest() {
        Producto producto = new Producto(
                1L, "Producto1", "Desc", "Cat",
                10f, LocalDate.now(), null, 1
        );


        Mockito.when(productoService.findProducto(1L))
                .thenReturn(Optional.of(producto));

        Optional<Producto> resultado = controller.getProducto(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Producto1", resultado.get().getNombre());

        Mockito.verify(productoService, Mockito.times(1)).findProducto(1L);
    }
}