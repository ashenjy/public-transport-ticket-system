package ticket.ticket.models;

/**
 * Created by lahiru on 11/19/2017.
 */

import javax.persistence.*;

@Entity
@Table(name = "distance_cost")
public class DistanceCost
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dcid")
    private int dcId;

    @Column(name = "distance")
    private double distance;

    @Column(name = "cost")
    private double cost;

    public int getDcId()
    {
        return dcId;
    }

    public void setDcId(int dcId)
    {
        this.dcId = dcId;
    }

    public double getDistance()
    {
        return distance;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    public double getCost()
    {
        return cost;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }
}
