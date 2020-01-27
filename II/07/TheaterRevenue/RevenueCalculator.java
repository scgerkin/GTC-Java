package theaterrevenue;

/**
 * RevenueCalculator holds info about theater tickets and calculates revenues
 */
public class RevenueCalculator {

    private int ticketsAdult;
    private double priceAdult;
    private int ticketsChild;
    private double priceChild;

    private final double PROFIT_MARGIN = .2;

    public RevenueCalculator(
        int adultTickets, double adultPrice,
        int childTickets, double childPrice)
    {
        this.ticketsAdult = adultTickets;
        this.priceAdult = adultPrice;
        this.ticketsChild = childTickets;
        this.priceChild = childPrice;
    }

    public double getGrossAdult() {
        return this.ticketsAdult * this.priceAdult;
    }

    public double getNetAdult() {
        return getGrossAdult() * this.PROFIT_MARGIN;
    }

    public double getGrossChild() {
        return this.ticketsChild * this.priceChild;
    }

    public double getNetChild() {
        return getGrossChild() * this.PROFIT_MARGIN;
    }

    public double getGrossTotal() {
        return getGrossAdult() + getGrossChild();
    }

    public double getNetTotal() {
        return getNetAdult() + getNetChild();
    }
}
