package com.progresssoft.datawarehouse.fxdeals.model;

import com.progresssoft.datawarehouse.fxdeals.exception.ValidCurrencyCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fx_deal")
public class FXDeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="deal_id")
    @NotNull(message = "Number cannot be null")
    @PositiveOrZero(message = "Deal Id must be greater than or equal to zero")
    private Integer dealId;

    @Column(name="from_currency")
    @ValidCurrencyCode
    private String fromCurrency;

    @Column(name="to_currency")
    @ValidCurrencyCode
    private String toCurrency;

    @Column(name="date_of_deal")
    private final LocalDateTime dateOfDeal;

    @Column(name="amount_deal")
    @NotNull(message = "Deal Amount in ordering currency is required")
    @PositiveOrZero(message = "Deal Amount must be greater than or equal to zero")
    private Double amountDeal;

    public FXDeal() {
        this.dateOfDeal = LocalDateTime.now();
    }

    public FXDeal(int dealId, String fromCurrency, String toCurrency, double amountDeal) {
        this.dateOfDeal = LocalDateTime.now();
        this.dealId = dealId;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amountDeal = amountDeal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getDealId() {
        return dealId;
    }

    public void setDealId(Integer dealId) {
        this.dealId = dealId;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public LocalDateTime getDateOfDeal() {
        return dateOfDeal;
    }

    public Double getAmountDeal() {
        return amountDeal;
    }

    public void setAmountDeal (Double amountDeal) {
        this.amountDeal = amountDeal;
    }

    public static Set<String> getFields() {
        Set<String> fields = new HashSet<>();
        for (Field field : FXDeal.class.getDeclaredFields()) {
            fields.add(field.getName());
        }
        return fields;
    }
}
