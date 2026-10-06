import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FlightTicketFareCalculator {
    private static final Scanner sc = new Scanner(System.in);

    // Passenger & Booking Fields
    private static String name, gender, contact, idProof, categoryName;
    private static int age, categoryChoice;
    private static String destName, destCode, flightNo, className, pnr;
    private static boolean isIntl;
    private static double baseFare, classMult, freeBaggage, carriedBaggage;
    private static double excessBaggage, baggageFee, discountRate, discountAmount;
    private static double netFare, totalTax, grandTotal;

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("     SKYWINGS AIRLINES - FARE CALCULATOR");
        System.out.println("==================================================");

        // Execute the 5 required case study modules sequentially
        registerPassenger(); // Module 1: Passenger Registration
        selectFlight(); // Module 2: Flight Selection
        selectClass(); // Module 3: Class Selection
        calculateFare(); // Module 4: Fare Calculation
        generateTicketSummary();// Module 5: Ticket Summary

        sc.close();
    }

    // MODULE 1: PASSENGER REGISTRATION (Using Scanner & Conditions)
    public static void registerPassenger() {
        System.out.println("\n[MODULE 1: PASSENGER REGISTRATION]");
        System.out.print("Enter Passenger Name: ");
        name = sc.nextLine().trim();

        System.out.print("Enter Age: ");
        age = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Enter Gender (Male/Female/Other): ");
        gender = sc.nextLine().trim();

        System.out.print("Enter Contact Number: ");
        contact = sc.nextLine().trim();

        System.out.print("Enter Govt ID / Passport: ");
        idProof = sc.nextLine().trim();

        System.out.println("\nSelect Passenger Category for Discount Concession:");
        System.out.println("1. General Adult | 2. Student (10%) | 3. Senior Citizen 60+ (15%)");
        System.out.println("4. Child 2-11 yrs (20%) | 5. Armed Forces Personnel (25%)");
        System.out.print("Enter Category (1-5): ");
        categoryChoice = Integer.parseInt(sc.nextLine().trim());

        // Validate age against category with if-else conditions
        if (categoryChoice == 3 && age < 60) {
            System.out.println("-> Age under 60. Converted to General Category.");
            categoryChoice = 1;
        } else if (categoryChoice == 4 && (age < 2 || age > 11)) {
            System.out.println("-> Age not in 2-11 range. Converted to General Category.");
            categoryChoice = 1;
        }

        switch (categoryChoice) {
            case 2 -> categoryName = "Student";
            case 3 -> categoryName = "Senior Citizen";
            case 4 -> categoryName = "Child";
            case 5 -> categoryName = "Armed Forces";
            default -> categoryName = "General Adult";
        }
        System.out.println(">> Registered: " + name + " (" + categoryName + ")");
    }

    // MODULE 2: FLIGHT SELECTION (Using Switch Statements)
    public static void selectFlight() {
        System.out.println("\n[MODULE 2: FLIGHT SELECTION]");
        System.out.println("1. New Delhi (DEL)  - ₹5,200 (Domestic)");
        System.out.println("2. Bengaluru (BLR)  - ₹4,100 (Domestic)");
        System.out.println("3. Kolkata (CCU)    - ₹5,800 (Domestic)");
        System.out.println("4. Goa (GOI)        - ₹3,200 (Domestic)");
        System.out.println("5. Dubai (DXB)      - ₹16,500 (International)");
        System.out.println("6. Singapore (SIN)  - ₹21,000 (International)");
        System.out.println("7. London (LHR)     - ₹46,000 (International)");
        System.out.print("Select Destination (1-7): ");
        int choice = Integer.parseInt(sc.nextLine().trim());

        // Switch statement to set destination & base fare
        switch (choice) {
            case 1 -> {
                destName = "New Delhi";
                destCode = "DEL";
                flightNo = "SW-102";
                baseFare = 5200;
                isIntl = false;
            }
            case 2 -> {
                destName = "Bengaluru";
                destCode = "BLR";
                flightNo = "SW-204";
                baseFare = 4100;
                isIntl = false;
            }
            case 3 -> {
                destName = "Kolkata";
                destCode = "CCU";
                flightNo = "SW-308";
                baseFare = 5800;
                isIntl = false;
            }
            case 4 -> {
                destName = "Goa";
                destCode = "GOI";
                flightNo = "SW-412";
                baseFare = 3200;
                isIntl = false;
            }
            case 5 -> {
                destName = "Dubai";
                destCode = "DXB";
                flightNo = "SW-701";
                baseFare = 16500;
                isIntl = true;
            }
            case 6 -> {
                destName = "Singapore";
                destCode = "SIN";
                flightNo = "SW-805";
                baseFare = 21000;
                isIntl = true;
            }
            case 7 -> {
                destName = "London";
                destCode = "LHR";
                flightNo = "SW-910";
                baseFare = 46000;
                isIntl = true;
            }
            default -> {
                destName = "New Delhi";
                destCode = "DEL";
                flightNo = "SW-101";
                baseFare = 5000;
                isIntl = false;
            }
        }
        System.out.println(">> Route: BOM -> " + destCode + " (" + destName + ") | Base Fare: ₹" + baseFare);
    }

    // MODULE 3: CLASS SELECTION (Using Switch Statements)
    public static void selectClass() {
        System.out.println("\n[MODULE 3: TRAVEL CLASS SELECTION]");
        System.out.println("1. Economy (1.00x, 15kg) | 2. Premium Economy (1.30x, 20kg)");
        System.out.println("3. Business (1.75x, 30kg) | 4. First Class (2.40x, 40kg)");
        System.out.print("Select Class (1-4): ");
        int choice = Integer.parseInt(sc.nextLine().trim());

        // Switch statement to set travel class attributes
        switch (choice) {
            case 2 -> {
                className = "Premium Economy";
                classMult = 1.30;
                freeBaggage = 20.0;
            }
            case 3 -> {
                className = "Business Class";
                classMult = 1.75;
                freeBaggage = 30.0;
            }
            case 4 -> {
                className = "First Class";
                classMult = 2.40;
                freeBaggage = 40.0;
            }
            default -> {
                className = "Economy Class";
                classMult = 1.00;
                freeBaggage = 15.0;
            }
        }

        // Student perk: +5kg bonus allowance
        if (categoryChoice == 2) {
            freeBaggage += 5.0;
            System.out.println("-> Student Perk Applied: +5 kg baggage allowance bonus!");
        }

        System.out.printf("Free Baggage Allowance: %.0f kg. Enter carried weight (kg): ", freeBaggage);
        carriedBaggage = Double.parseDouble(sc.nextLine().trim());
    }

    // MODULE 4: FARE CALCULATION (Using Operators & If-Else)
    public static void calculateFare() {
        // 1. Base fare adjusted by class multiplier
        double classFare = baseFare * classMult;

        // 2. Discount percentage determined using if-else statements
        if (categoryChoice == 5) {
            discountRate = 25.0; // Armed forces: 25%
        } else if (categoryChoice == 4) {
            discountRate = 20.0; // Child: 20%
        } else if (categoryChoice == 3) {
            discountRate = 15.0; // Senior: 15%
        } else if (categoryChoice == 2) {
            discountRate = 10.0; // Student: 10%
        } else {
            discountRate = 0.0; // General: 0%
        }

        // 3. Discount and net fare arithmetic
        discountAmount = (classFare * discountRate) / 100.0;
        netFare = classFare - discountAmount;

        // 4. Excess baggage calculation with relational & arithmetic operators
        if (carriedBaggage > freeBaggage) {
            excessBaggage = carriedBaggage - freeBaggage;
            double excessRate = isIntl ? 850.0 : 450.0;
            baggageFee = excessBaggage * excessRate;
        } else {
            excessBaggage = 0.0;
            baggageFee = 0.0;
        }

        // 5. Statutory fees & GST (5% for Economy/Prem, 12% for Business/First)
        double gstRate = (classMult >= 1.75) ? 0.12 : 0.05;
        double airportFee = 350.0, fuelSurcharge = 450.0;
        totalTax = ((netFare + baggageFee) * gstRate) + airportFee + fuelSurcharge;

        // 6. Grand total
        grandTotal = netFare + baggageFee + totalTax;
        pnr = "SW" + (int) (Math.random() * 900000 + 100000);
    }

    // MODULE 5: TICKET SUMMARY (Formatted Output Receipt)
    public static void generateTicketSummary() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));

        System.out.println("\n" + "=".repeat(62));
        System.out.println("             SKYWINGS AIRWAYS - BOARDING PASS");
        System.out.println("=".repeat(62));
        System.out.printf(" PNR: %-16s DATE: %s\n", pnr, timestamp);
        System.out.printf(" PASSENGER : %-22s AGE/SEX: %d / %s\n", name, age, gender);
        System.out.printf(" CONTACT   : %-22s ID/PASS : %s\n", contact, idProof);
        System.out.printf(" CATEGORY  : %-22s DISCOUNT: %.0f%%\n", categoryName, discountRate);
        System.out.println("-".repeat(62));
        System.out.printf(" FLIGHT NO : %-22s CLASS   : %s\n", flightNo, className);
        System.out.printf(" ROUTE     : Mumbai (BOM) -> %s (%s)\n", destName, destCode);
        System.out.printf(" BAGGAGE   : %.1f kg (Free: %.0f kg | Excess: %.1f kg)\n", carriedBaggage, freeBaggage,
                excessBaggage);
        System.out.println("-".repeat(62));
        System.out.println(" FARE BREAKDOWN:");
        System.out.printf("   Base Ticket Fare (x%.2f)       : ₹ %10.2f\n", classMult, (baseFare * classMult));
        System.out.printf("   Category Discount (-%.0f%%)       : -₹ %9.2f\n", discountRate, discountAmount);
        System.out.printf("   Net Fare After Discount         : ₹ %10.2f\n", netFare);
        System.out.printf("   Excess Baggage Charges          : ₹ %10.2f\n", baggageFee);
        System.out.printf("   Taxes & Surcharges (GST+Fees)   : ₹ %10.2f\n", totalTax);
        System.out.println("   " + "-".repeat(46));
        System.out.printf("   TOTAL PAYABLE AMOUNT            : ₹ %10.2f\n", grandTotal);
        System.out.println("=".repeat(62));
        System.out.println(" Have a pleasant flight! Please carry valid government ID.");
        System.out.println("=".repeat(62));
    }
}
