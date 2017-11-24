package ticket.ticket.models;

        import javax.persistence.*;
        import java.io.Serializable;

@Entity
@Table(name = "email")
public class Mail implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "mailFrom")
    private String mailFrom;

    @Column(name = "mailTo")
    private String mailTo;

    @Column(name = "mailSubject")
    private String mailSubject;

    @Column(name = "mailContent")
    private String mailContent;

    @Column(name = "mailDate")
    private String mailDate;

    public String getMailDate() {
        return mailDate;
    }

    public void setMailDate(String mailDate) {
        this.mailDate = mailDate;
    }

    public Mail() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public Mail(  String mailFrom , String mailTo,
                      String mailSubject, String mailContent,String mailDate) {

        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.mailSubject = mailSubject;
        this.mailContent = mailContent;
        this.mailDate = mailDate;

    }
}