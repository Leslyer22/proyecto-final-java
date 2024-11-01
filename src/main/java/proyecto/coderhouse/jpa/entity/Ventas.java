package proyecto.coderhouse.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ventas")
public class Ventas {

    public Ventas() {}

    public Ventas(int cliente_id, int producto_id, int total, int stock) {
        this.cliente_id = cliente_id;
        this.producto_id = producto_id;
        this.total = total;
        this.stock = stock;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CLIENTE_ID")
    private int cliente_id;

    @Column(name = "PRODUCTO_ID")
    private int producto_id;

    @Column(name = "TOTAL")
    private int total;

    @Column(name = "STOCK") // Adiciona o atributo de stock
    private int stock;

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}

