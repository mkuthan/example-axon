package example.conference.management.impl.domain;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class SeatType {

    @Id
    private String id;

    @Basic(optional = false)
    private String type;

    @Basic(optional = false)
    private int quantity;

    @Basic(optional = false)
    private BigDecimal priceAmount;

    @Basic(optional = false)
    private String priceCurrency;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Conference conference;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(BigDecimal priceAmount) {
        this.priceAmount = priceAmount;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    protected SeatType() {
    }

}
