package ticket.ticket.models;

import javax.persistence.*;

/**
 * Created by lahiru on 11/19/2017.
 */

@Entity
@Table(name = "route")
public class Route
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rid")
    private int rId;

    @Column(name = "source")
    private String source;

    @Column(name = "destination")
    private String destination;

    @Column(name = "distance")
    private double distance;

    public int getRid()
    {
        return rId;
    }

    public void setRid(int rId)
    {
        this.rId = rId;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public String getDestination()
    {
        return destination;
    }

    public void setDestination(String destination)
    {
        this.destination = destination;
    }

    public double getDistance()
    {
        return distance;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }
}
