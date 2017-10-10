package ua.training.bookshop.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Entity for the table in the DB.
 * @author Illya Hrytsak
 */
@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {

    @Id
    @Column(name = "ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Role role;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    transient private String confirmPassword;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "CARD")
    private Double card;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Orders> orders;

    public Account() {}

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getCard() {
        return card;
    }

    public void setCard(Double card) {
        this.card = card;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (!getAccountId().equals(account.getAccountId())) return false;
        if (!getRole().equals(account.getRole())) return false;
        if (!getEmail().equals(account.getEmail())) return false;
        if (!getPassword().equals(account.getPassword())) return false;
        if (getConfirmPassword() != null ? !getConfirmPassword().equals(account.getConfirmPassword()) : account.getConfirmPassword() != null)
            return false;
        if (!getFirstName().equals(account.getFirstName())) return false;
        if (!getLastName().equals(account.getLastName())) return false;
        return getPhoneNumber().equals(account.getPhoneNumber()) && getCard().equals(account.getCard());
    }

    @Override
    public int hashCode() {
        int result = getAccountId().hashCode();
        result = 31 * result + getRole().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + (getConfirmPassword() != null ? getConfirmPassword().hashCode() : 0);
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getPhoneNumber().hashCode();
        result = 31 * result + getCard().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", card=" + card +
                '}';
    }
}
