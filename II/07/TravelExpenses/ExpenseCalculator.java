package travelexpenses;

/**
 * ExpenseCalculator class gets expenses and calculates reimbursement rates
 */
public class ExpenseCalculator {

    private int tripDays;
    private double airfare;
    private boolean carRented;
    private double carRentalFees;
    private double milesDriven;
    private double parkingFees;
    private double taxiFees;
    private double conferenceFees;
    private double lodgingPerNightFee;

    private final double MEAL_PER_DIEM = 37.00;
    private final double PARKING_PER_DIEM = 10.00;
    private final double TAXI_PER_DIEM = 20.00;
    private final double LODGING_PER_DIEM = 95.00;
    private final double MILE_REIMBURSMENT_RATE = 0.27;

    public ExpenseCalculator() {
        this.tripDays = 0;
        this.airfare = 0;
        this.carRentalFees = 0;
        this.carRented = false;
        this.milesDriven = 0;
        this.parkingFees = 0;
        this.taxiFees = 0;
        this.conferenceFees = 0;
        this.lodgingPerNightFee = 0;
    }

    public void setTripDays(int days) {
        this.tripDays = days;
    }

    public void setAirfare(double fee) {
        this.airfare = fee;
    }

    public void setCarRentalFee(double rentalFee) {
        this.carRented = true;
        this.carRentalFees = rentalFee;
    }

    public Boolean carRented() {
        return this.carRented;
    }

    public void setMilesDriven(double miles) {
        this.carRented = false;
        this.milesDriven = miles;
    }

    public void setParkingFees(double fees) {
        this.parkingFees = fees;
    }

    public void setTaxiCharges(double charges) {
        this.taxiFees = charges;
    }

    public void setConferenceFees(double fees) {
        this.conferenceFees = fees;
    }

    public void setLodgingCharges(double charges) {
        this.lodgingPerNightFee = charges;
    }

    public double getTotalExpenses() {
        double totalExpenses = 0;

        totalExpenses += this.airfare;
        totalExpenses += this.conferenceFees;
        totalExpenses += this.tripDays * this.MEAL_PER_DIEM;

        totalExpenses += (carRented) ?
            this.carRentalFees : this.milesDriven * this.MILE_REIMBURSMENT_RATE;

        totalExpenses += this.parkingFees;
        totalExpenses += this.taxiFees;
        totalExpenses += this.tripDays * this.lodgingPerNightFee;

        return totalExpenses;
    }

    public double getActualReimbursement() {
        double reimbursement = 0;

        // add always reimbursed amounts
        reimbursement += this.airfare;
        reimbursement += this.conferenceFees;
        reimbursement += this.tripDays * this.MEAL_PER_DIEM;

        // add conditional reimbursement amounts
        double maxParking = this.tripDays * this.PARKING_PER_DIEM;
        reimbursement += (this.parkingFees > maxParking) ?
                        maxParking : this.parkingFees;

        double maxTaxi = this.tripDays * this.TAXI_PER_DIEM;
        reimbursement += (this.taxiFees > maxTaxi) ? maxTaxi : this.taxiFees;

        reimbursement += (carRented) ?
            this.carRentalFees : this.milesDriven * this.MILE_REIMBURSMENT_RATE;

        double maxLodging = this.tripDays * this.LODGING_PER_DIEM;
        double totalLodging = this.lodgingPerNightFee * this.tripDays;
        reimbursement += (totalLodging > maxLodging) ?
                        maxLodging : totalLodging;

        return reimbursement;
    }

    public double getMaxReimbursement() {
        double maxReimbursement =  this.airfare + this.conferenceFees;
        maxReimbursement += tripDays * (
                                        this.MEAL_PER_DIEM
                                        + this.PARKING_PER_DIEM
                                        + this.TAXI_PER_DIEM
                                        + this.LODGING_PER_DIEM
                                        );

        maxReimbursement += (carRented) ?
            this.carRentalFees : this.milesDriven * this.MILE_REIMBURSMENT_RATE;

        return maxReimbursement;
    }
}
