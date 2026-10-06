# Case Study 177: Flight Ticket Fare Calculator

**Program:** B.Tech Computer Science & Engineering (2025–2029)  
**Course:** Java Programming | Semester III  
**Institution:** School of Future Tech, ITM Skills University  

---

## 📌 1. Project Overview

The **Flight Ticket Fare Calculator** is a console-based airline booking and fare computation application developed in Java. The system calculates domestic and international flight ticket fares dynamically by evaluating:
1. **Destination route** and base sector fares.
2. **Travel class tier** (Economy, Premium Economy, Business, First Class).
3. **Passenger profile concessions** (General, Student, Senior Citizen, Child, Armed Forces).
4. **Baggage allowance and excess weight penalties**.
5. **Aviation surcharges, airport development fees, and statutory GST**.

The system generates an itemized, ASCII-formatted **Electronic Boarding Pass & Tax Invoice** upon completion of booking.

---

## 🎯 2. Case Study Objectives & Alignment

| Case Study Requirement | Implementation Detail in Code |
| :--- | :--- |
| **Accept passenger details** | Handled in `registerPassenger()` using `Scanner` for name, age, gender, mobile number, ID/Passport, and category. |
| **Select destination and class** | Implemented using **`switch` statements** in `selectFlight()` and `selectClass()`. |
| **Calculate base fare** | Base fare mapped per route and scaled with class multipliers using arithmetic operators (`*`, `+`). |
| **Calculate baggage charges** | Free baggage threshold verified using relational operators (`>`); excess baggage charged per kg using arithmetic operators. |
| **Apply passenger discounts** | Evaluated via **`if-else` decision ladders** based on passenger age, category, and eligibility in `calculateFare()`. |
| **Methods & Modularity** | Separated into 5 dedicated static methods corresponding directly to the required modules. |

---

## 🧱 3. System Architecture & Modules

The system is structured into five core modules executed in sequence:

```
+-------------------------------------------------------+
|        SkyWings Airlines Fare Calculator System       |
+-------------------------------------------------------+
                           |
       +-------------------+-------------------+
       |                   |                   |
       v                   v                   v
[1. Registration]   [2. Flight Select]   [3. Class Select]
 - Name, Age, ID     - Routes 1 to 7      - Economy (1.00x)
 - Phone, Gender     - Base Fare (₹)      - Prem. Eco (1.30x)
 - Category Check    - Domestic/Intl      - Business (1.75x)
                                          - First (2.40x)
                                          - Baggage check-in
                                               |
                                               v
                                     [4. Fare Calculation]
                                      - Class Multiplier
                                      - Concession Discount
                                      - Excess Baggage Fee
                                      - Surcharges & Taxes
                                               |
                                               v
                                     [5. Ticket Summary]
                                      - Boarding Pass
                                      - PNR Allocation
                                      - Itemized Invoice
```

### Module 1: Passenger Registration (`registerPassenger()`)
- Collects passenger name, age, gender, mobile number, and identity proof.
- Allows selection of passenger category:
  - **General / Regular Adult** (Standard fare)
  - **Student** (10% discount + 5 kg extra baggage allowance waiver)
  - **Senior Citizen** (15% discount for age 60+)
  - **Child** (20% discount for age 2–11)
  - **Armed Forces / Defense Personnel** (25% discount)
- Includes cross-validation (e.g., verifying that a passenger claiming Senior Citizen discount is $\ge 60$ years of age).

### Module 2: Flight Selection (`selectFlight()`)
- Uses a `switch` statement to select from 7 domestic and international routes originating from Mumbai (BOM):

| Option | Destination | Airport Code | Sector Type | Base Fare (₹) |
| :---: | :--- | :---: | :---: | :---: |
| 1 | New Delhi | DEL | Domestic | ₹ 5,200.00 |
| 2 | Bengaluru | BLR | Domestic | ₹ 4,100.00 |
| 3 | Kolkata | CCU | Domestic | ₹ 5,800.00 |
| 4 | Goa | GOI | Domestic | ₹ 3,200.00 |
| 5 | Dubai (UAE) | DXB | International | ₹ 16,500.00 |
| 6 | Singapore | SIN | International | ₹ 21,000.00 |
| 7 | London (UK) | LHR | International | ₹ 46,000.00 |

### Module 3: Travel Class Selection (`selectClass()`)
- Uses a `switch` statement to set class multiplier and complimentary baggage allowance:

| Class Option | Class Name | Fare Multiplier | Free Baggage Allowance |
| :---: | :--- | :---: | :---: |
| 1 | Economy Class | $1.00\times$ | 15 kg |
| 2 | Premium Economy | $1.30\times$ | 20 kg |
| 3 | Business Class | $1.75\times$ | 30 kg |
| 4 | First Class | $2.40\times$ | 40 kg |

*Note: Students receive an additional +5 kg complimentary baggage allowance bonus.*

### Module 4: Fare & Baggage Calculation Logic (`calculateFare()`)
- **Adjusted Class Fare**:  
  $$\text{Class Fare} = \text{Base Fare} \times \text{Class Multiplier}$$
- **Passenger Discount**:  
  $$\text{Discount Amount} = \frac{\text{Class Fare} \times \text{Discount Rate}}{100}$$
  $$\text{Net Flight Fare} = \text{Class Fare} - \text{Discount Amount}$$
- **Excess Baggage Charges**:  
  $$\text{Excess Weight} = \max(0, \text{Carried Weight} - \text{Free Allowance})$$
  $$\text{Baggage Fee} = \text{Excess Weight} \times \text{Excess Rate per kg}$$
  *(Domestic excess rate: ₹450/kg; International excess rate: ₹850/kg)*
- **Taxes & Surcharges**:
  - Airport Development Fee: ₹350.00
  - Aviation Fuel Surcharge: ₹450.00
  - GST: 5% for Economy/Premium Economy; 12% for Business/First Class applied on `(Net Flight Fare + Baggage Fee)`
- **Total Payable Fare**:  
  $$\text{Total} = \text{Net Flight Fare} + \text{Baggage Fee} + \text{Taxes \& Surcharges}$$

### Module 5: Ticket Summary & Boarding Pass (`generateTicketSummary()`)
- Generates a 6-character alphanumeric booking reference (PNR).
- Formats a receipt with timestamp, passenger details, itinerary, and an itemized tax invoice.

---

## 💻 4. Java Language Constructs Demonstrated

1. **`Scanner` Class (`java.util.Scanner`)**:
   - Used for interactive keyboard input with stream buffer parsing.
2. **`switch` Statements**:
   - Destination route selection (`case 1` to `case 7`).
   - Travel class selection (`case 1` to `case 4`).
   - Passenger category selection mapping.
3. **`if-else` Conditional Statements**:
   - Category age eligibility verification.
   - Discount tier selection logic (`armed forces == 25%`, `child == 20%`, `senior == 15%`, `student == 10%`).
   - Baggage excess threshold checks (`carriedBaggage > freeBaggage`).
   - GST slab differentiation based on cabin tier.
4. **Operators**:
   - **Arithmetic**: `+`, `-`, `*`, `/`, `%` for fare scaling, discounting, excess weight calculations, and tax percentages.
   - **Relational**: `>`, `<`, `>=`, `<=`, `==`, `!=` for boundary conditions and eligibility checks.
   - **Logical**: `&&`, `||`, `!` for multi-variable qualification.
   - **Assignment / Compound Assignment**: `=`, `+=`.
5. **Methods**:
   - Clear decomposition into modular methods: `registerPassenger()`, `selectFlight()`, `selectClass()`, `calculateFare()`, and `generateTicketSummary()`.

---

## 🚀 5. How to Compile and Run

### Prerequisites
- Java Development Kit (JDK 8 or higher, tested on JDK 21 and JDK 26).

### Step 1: Navigate to the Directory
```bash
cd /Users/sohamsandeeppatil/Documents/java_project
```

### Step 2: Compile the Java Source Code
```bash
javac FlightTicketFareCalculator.java
```

### Step 3: Run the Program
```bash
java FlightTicketFareCalculator
```

---

## 📋 6. Sample Execution Walkthrough

### Scenario: Domestic Student Booking with Excess Baggage
- **Passenger:** Soham Patil, Age 20, Student
- **Route:** Mumbai (BOM) to New Delhi (DEL) [Base Fare: ₹5,200.00]
- **Class:** Premium Economy ($1.30\times$, 20 kg allowance + 5 kg student bonus = 25 kg)
- **Carried Baggage:** 28.0 kg (3.0 kg excess @ ₹450/kg)

### Generated Output:
```text
==============================================================
             SKYWINGS AIRWAYS - BOARDING PASS
==============================================================
 PNR: SW250633         DATE: 28-Sept-2026 16:38
 PASSENGER : Soham Patil            AGE/SEX: 20 / Male
 CONTACT   : 9876543210             ID/PASS : PASS12345
 CATEGORY  : Student                DISCOUNT: 10%
--------------------------------------------------------------
 FLIGHT NO : SW-102                 CLASS   : Premium Economy
 ROUTE     : Mumbai (BOM) -> New Delhi (DEL)
 BAGGAGE   : 28.0 kg (Free: 25 kg | Excess: 3.0 kg)
--------------------------------------------------------------
 FARE BREAKDOWN:
   Base Ticket Fare (x1.30)       : ₹    6760.00
   Category Discount (-10%)       : -₹    676.00
   Net Fare After Discount         : ₹    6084.00
   Excess Baggage Charges          : ₹    1350.00
   Taxes & Surcharges (GST+Fees)   : ₹    1171.70
   ----------------------------------------------
   TOTAL PAYABLE AMOUNT            : ₹    8605.70
==============================================================
 Have a pleasant flight! Please carry valid government ID.
==============================================================
```

---


