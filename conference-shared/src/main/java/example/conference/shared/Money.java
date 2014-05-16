package example.conference.shared;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

public class Money {

    private BigDecimal amount;

    private String currency;

    public Money(BigDecimal amount, String currency) {
        this.amount = requireNonNull(amount);
        this.currency = requireNonNull(currency);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        if (amount != null ? !amount.equals(money.amount) : money.amount != null) return false;
        if (currency != null ? !currency.equals(money.currency) : money.currency != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = amount != null ? amount.hashCode() : 0;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }

    protected Money() {
    }
}
