package ar.unrn.tp.servicios;

import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.excepciones.ProductoEx;
import ar.unrn.tp.modelo.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class JPAProductoServices implements ProductoService {
    @PersistenceContext
    private final EntityManager em;

    public JPAProductoServices(EntityManager em) {
        this.em = em;
    }

    @Override
    public void crearProducto(String codigo, String descripcion, float precio, String IdCategoria) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Producto producto = new Producto(codigo, descripcion, IdCategoria, precio);
            em.persist(producto);
            tx.commit();

        } catch (Exception e) {
            throw new ProductoEx("Error al crear el producto: " + e.getMessage());
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

    }

    @Override
    public void modificarProducto(Long idProducto, String codigo, String descripcion, String categoria, double precio) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Producto producto = em.getReference(Producto.class, idProducto);
            producto.setCodigo("1234567");
            producto.setDescripcion("");
            producto.setPrecio(4);
            producto.setMiCategoria("a");
            tx.commit();
        } catch (NoResultException e) {
            throw new ProductoEx("No se encontró ningún producto.");

        } catch (Exception e) {
            throw new ProductoEx("Error al actualizar el producto: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override

    public List listarProductos() {
        return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();

    }

    @Override
    public List<Producto> buscarProductos(List<Long> idsProductos) {
        return em.createQuery("SELECT p FROM Producto p WHERE p.id IN :id", Producto.class)
                .setParameter("id", idsProductos)
                .getResultList();
    }
}
