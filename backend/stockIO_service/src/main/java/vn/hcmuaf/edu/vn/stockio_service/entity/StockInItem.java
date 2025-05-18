package vn.hcmuaf.edu.vn.stockio_service.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "stock_in_item")
public class StockInItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int productId;

    private Integer quantity;

    private BigDecimal unitPrice;

    private String note;

    @ManyToOne
    @JoinColumn(name = "stock_in_id")
    private StockIn stockIn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public StockIn getStockIn() {
        return stockIn;
    }

    public void setStockIn(StockIn stockIn) {
        this.stockIn = stockIn;
    }
}

