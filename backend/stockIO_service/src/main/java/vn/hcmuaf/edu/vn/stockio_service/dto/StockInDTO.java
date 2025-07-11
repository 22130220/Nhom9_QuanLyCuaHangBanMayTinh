package vn.hcmuaf.edu.vn.stockio_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class StockInDTO {
    @JsonProperty("createdBy")
    private int createrID;
    private Long supplierId;
    private LocalDate createdDate;
    private String note;
    @JsonProperty("products")
    private List<StockInItemDTO> items;
    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<StockInItemDTO> getItems() {
        return items;
    }

    public void setItems(List<StockInItemDTO> items) {
        this.items = items;
    }

    public int getCreaterID() {
        return createrID;
    }

    public void setCreaterID(int createrID) {
        this.createrID = createrID;
    }
}
