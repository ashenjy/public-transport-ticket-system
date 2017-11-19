package ticket.ticket.models;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ticket_id;

    @Column(name = "card_number")
    private String card_number;

    @Column(name = "start_time")
    private String start_time;

    @Column(name = "start_point")
    private String start_point;

    @Column(name = "detination")
    private String detination;

    @Column(name = "distance")
    private String distance;

    @Column(name = "cost")
    private String cost;

    public long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStart_point() {
        return start_point;
    }

    public void setStart_point(String start_point) {
        this.start_point = start_point;
    }

    public String getDetination() {
        return detination;
    }

    public void setDetination(String detination) {
        this.detination = detination;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Ticket(String card_number, String start_time, String start_point, String detination, String distance, String cost) {
        this.card_number = card_number;
        this.start_time = start_time;
        this.start_point = start_point;
        this.detination = detination;
        this.distance = distance;
        this.cost = cost;
    }
}
