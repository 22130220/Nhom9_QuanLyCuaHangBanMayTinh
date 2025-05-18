package vn.hcmuaf.edu.vn.stockio_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "stock_in_item")
public class StockInItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int product_id;
    private Integer quantity;

    private BigDecimal unit_price;

    private String note;

    @ManyToOne
    @JoinColumn(name = "stock_in_id")
    @JsonIgnore
    private StockIn stockIn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
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

