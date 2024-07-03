package com.progresssoft.datawarehouse.fxdeals.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

@Entity
@Table(name = "fx_deal")
public class FXDeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "from currency ISO code muse be specified")
    @Pattern(regexp = "[A-Z]{3}", message = "from currency ISO Code must be a 3-letter uppercase ISO code")
    private String fromCurrency;

    @NotEmpty(message = "to currency ISO code must not be empty")
    @Pattern(regexp = "[A-Z]{3}", message = "to currency ISO Code must be a 3-letter uppercase ISO code")
    private String toCurrency;

    private final LocalDateTime localDateTime;

    @NotNull(message = "Deal Amount in ordering currency is required")
    @PositiveOrZero(message = "Deal Amount must be greater than or equal to zero")
    private double amountDeal;

    public FXDeal() {
        this.localDateTime = LocalDateTime.now();
    }

    public FXDeal(String fromCurrency, String toCurrency, double amountDeal) {
        this.localDateTime = LocalDateTime.now();
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amountDeal = amountDeal;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public double getAmountDeal() {
        return amountDeal;
    }

    public void setAmountDeal(double amountDeal) {
        this.amountDeal = amountDeal;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
