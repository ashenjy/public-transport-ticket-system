package ticket.ticket.models;

/**
 * Created by lahiru on 11/23/2017.
 */

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment_l")
public class PaymentL
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pid")
    private int pId;

    @Column(name = "category")
    private String category;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "amount")
    private double amount;

    //foreign key
    @Column(name = "nic")
    private String nic;

    @Column(name = "payment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    public int getpId()
    {
        return pId;
    }

    public void setpId(int pId)
    {
        this.pId = pId;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getPaymentType()
    {
        return paymentType;
    }

    public void setPaymentType(String paymentType)
    {
        this.paymentType = paymentType;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public String getNic()
    {
        return nic;
    }

    public void setNic(String nic)
    {
        this.nic = nic;
    }

    public Date getPaymentDate()
    {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate)
    {
        this.paymentDate = paymentDate;
    }
}
