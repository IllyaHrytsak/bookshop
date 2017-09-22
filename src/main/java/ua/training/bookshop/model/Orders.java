package ua.training.bookshop.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Column(name = "AMOUNT")
    private Integer amount;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;

    @Column(name = "ACTIVE")
    private boolean active;

    @Column(name = "ORDER_DATE")
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;

    public Orders() {}

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;

        if (isActive() != orders.isActive()) return false;
        if (getOrderId() != null ? !getOrderId().equals(orders.getOrderId()) : orders.getOrderId() != null)
            return false;
        if (getAmount() != null ? !getAmount().equals(orders.getAmount()) : orders.getAmount() != null) return false;
        if (getPrice() != null ? !getPrice().equals(orders.getPrice()) : orders.getPrice() != null) return false;
        if (getTotalPrice() != null ? !getTotalPrice().equals(orders.getTotalPrice()) : orders.getTotalPrice() != null)
            return false;
        return getOrderDate() != null ? getOrderDate().equals(orders.getOrderDate()) : orders.getOrderDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getOrderId() != null ? getOrderId().hashCode() : 0;
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getTotalPrice() != null ? getTotalPrice().hashCode() : 0);
        result = 31 * result + (isActive() ? 1 : 0);
        result = 31 * result + (getOrderDate() != null ? getOrderDate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", amount=" + amount +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", active=" + active +
                ", orderDate=" + orderDate +
                '}';
    }
}
