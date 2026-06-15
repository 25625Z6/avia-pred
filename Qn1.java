
package vu.qn1;
import java.util.*;
public class Qn1 {

    public static void main(String[] args) {
      

class Customer {
    // Encapsulated fields
    private final double consumption;
    private final String band;
    private final int ratePerM3;
    private final int fixedCharge;
    private final double totalBill;

    public Customer(double consumption, String band, int ratePerM3, int fixedCharge) {
        this.consumption = consumption;
        this.band = band;
        this.ratePerM3 = ratePerM3;
        this.fixedCharge = fixedCharge;
        this.totalBill = (consumption * ratePerM3) + fixedCharge;
    }

    public String getBand() { return band; }
    public double getTotalBill() { return totalBill; }

    public void displayBill() {
        System.out.println("Consumption: " + consumption + " m³ | Band: " + band + " | Total Bill: UGX " + totalBill);
    }
}


    
        Scanner sc = new Scanner(System.in);
        ArrayList<Customer> customers = new ArrayList<>();
        Map<String, Integer> bandCount = new HashMap<>();

        // (d i) Loop for 6 customers
        for (int i = 1; i <= 6; i++) {
            double consumption = -1;
            
           
            while (consumption < 0) {
                try {
                    System.out.print("Enter monthly consumption for customer " + i + " in m³: ");
                    consumption = sc.nextDouble();
                    if (consumption < 0) {
                        System.out.println("Consumption cannot be negative. Try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next(); 
                }
            }

            
            String band;
            int rate, fixedCharge;

            if (consumption >= 0 && consumption <= 5) {
                band = "LIFELINE";
                rate = 1000;
                fixedCharge = 2000;
            } else if (consumption <= 20) {
                band = "DOMESTIC LOW";
                rate = 2500;
                fixedCharge = 4000;
            } else if (consumption <= 50) {
                band = "DOMESTIC HIGH";
                rate = 3800;
                fixedCharge = 7500;
            } else if (consumption <= 100) {
                band = "COMMERCIAL";
                rate = 4500;
                fixedCharge = 15000;
            } else if (consumption <= 300) {
                band = "INDUSTRIAL";
                rate = 5200;
                fixedCharge = 40000;
            } else {
                band = "INSTITUTIONAL";
                rate = 6000;
                fixedCharge = 90000;
            }

            // (d iii) Create Customer object and store in ArrayList
            Customer c = new Customer(consumption, band, rate, fixedCharge);
            customers.add(c);
            bandCount.put(band, bandCount.getOrDefault(band, 0) + 1);

            // (c) Display individual bill
            System.out.print("Customer " + i + ": ");
            c.displayBill();
            System.out.println();
        }

        // (d ii) Billing summary after all entries
        double totalRevenue = 0;
        System.out.println("=== BILLING SUMMARY ===");
        
        // Iterate over ArrayList to produce summary
        for (Customer c : customers) {
            totalRevenue += c.getTotalBill();
        }

        System.out.println("Customers per band: " + bandCount);
        System.out.println("Total monthly revenue: UGX " + totalRevenue);
        System.out.println("Average bill per customer: UGX " + (totalRevenue / customers.size()));
        
        sc.close();
    }
}  
    

