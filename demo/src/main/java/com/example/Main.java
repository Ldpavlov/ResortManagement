package com.example;

import java.util.*;

import com.example.common.NoSkisAvailableException;
import com.example.data.DataReader;
import com.example.data.DataReaderInterface;
import com.example.data.DataWriter;
import com.example.data.DataWriterInterface;
import com.example.manager.SkiSchoolManager;
import com.example.model.Country;
import com.example.model.Instructor;
import com.example.model.Resort;
import com.example.model.SkiSchool;

import java.io.IOException;
import java.time.*;

public class Main {
        public static void main(String[] args) throws NoSkisAvailableException, IOException {

                LocalDate today = LocalDate.now();
                DataWriterInterface dataWriter = new DataWriter("resort_data.txt");
                DataReaderInterface dataReader = new DataReader("resort_data.txt");

                // Create a new resort instance
                Resort aspenResort = new Resort("Aspen", 111, 645, Country.USA, 31000, 5000000, 1.0);

                // Adding ski pass types/prices Aspen
                aspenResort.addSkiPassType("DAY", 75.0);
                aspenResort.addSkiPassType("WEEK", 500.0);
                aspenResort.addSkiPassType("SEASONAL", 1000.0);

                // Record ski pass sales Aspen
                aspenResort.recordSkiPassSale("DAY", 10000);
                aspenResort.recordSkiPassSale("WEEK", 7000);
                aspenResort.recordSkiPassSale("SEASONAL", 1000);

                // Create new ski schools and instructors
                SkiSchool skiSchool = new SkiSchool("Ski With Me", 2000, 0.8, 35, 5, 3, 30, 200, dataWriter);
                Instructor gale = new Instructor("Gale", true, 150);
                Instructor max = new Instructor("Max", false, 220);
                skiSchool.addInstructor(gale);
                skiSchool.addInstructor(max);

                SkiSchool skiSchool2 = new SkiSchool("NumberOne", 2500, 1, 35, 10, 10, 30, 100, dataWriter);
                Instructor tim = new Instructor("Tim", true, 120);
                Instructor mike = new Instructor("Mike", false, 250);
                skiSchool2.addInstructor(tim);
                skiSchool2.addInstructor(mike);

                aspenResort.addSkiSchool(skiSchool);
                aspenResort.addSkiSchool(skiSchool2);

                // Create second ski resort instance
                Resort banskoResort = new Resort("Bansko", 20, 100, Country.BULGARIA, 15000, 1000000, 2.0);

                // Adding ski pass types/prices Bansko
                banskoResort.addSkiPassType("DAY", 75.0);
                banskoResort.addSkiPassType("WEEK", 500.0);
                banskoResort.addSkiPassType("SEASONAL", 1500.0);

                // Record ski pass sales Bansko
                banskoResort.recordSkiPassSale("DAY", 10000);
                banskoResort.recordSkiPassSale("WEEK", 5000);
                banskoResort.recordSkiPassSale("SEASONAL", 500);

                // Create new ski schools and instructors
                SkiSchool snowPeaks = new SkiSchool("Snow Peaks", 3000, 2.0, 20, 2, 5, 20, 200, dataWriter);
                Instructor gosho = new Instructor("Gosho", true, 150);
                Instructor pesho = new Instructor("Pesho", false, 220);
                snowPeaks.addInstructor(gosho);
                snowPeaks.addInstructor(pesho);

                SkiSchool pirinSkiClub = new SkiSchool("Pirin Ski School", 3300, 1.5, 25, 5, 5, 25, 250, dataWriter);
                Instructor tosho = new Instructor("Tosho", true, 170);
                Instructor misho = new Instructor("Misho", false, 215);
                pirinSkiClub.addInstructor(tosho);
                pirinSkiClub.addInstructor(misho);

                banskoResort.addSkiSchool(snowPeaks);
                banskoResort.addSkiSchool(pirinSkiClub);

                // Add all resorts to a list
                List<Resort> allResorts = new ArrayList<>();
                allResorts.add(aspenResort);
                allResorts.add(banskoResort);

                for (Resort rs : allResorts) {

                        double totalPrice = rs.getFinancialManager().calculateTotalRevenueFromSkiPasses();
                        double totalElectricityCost = rs.getFinancialManager().calculateTotalCosts()
                                        - rs.getFinancialManager().getMaintenanceCosts();
                        double profit = rs.getProfit();

                        // DataWriter dataWriter = new DataWriter("resort_data.txt");
                        dataWriter.writeToFile(rs.toString());
                        dataWriter.writeToFile("Total price of ski passes sold: $" + totalPrice);
                        dataWriter.writeToFile(
                                        "Total maintenance and electricity costs: $"
                                                        + rs.getFinancialManager().calculateTotalCosts());
                        dataWriter.writeToFile("Total price for electricity: $" + totalElectricityCost);
                        dataWriter.writeToFile("Total resort profit: $" + profit + "\n");

                        List<SkiSchool> allSkiSchools = rs.getAllSkiSchools();

                        for (SkiSchool sSchool : allSkiSchools) {
                                dataWriter.writeToFile("Ski school: " + sSchool.getName());
                                for (Instructor instr : sSchool.getInstructors()) {
                                        if (instr.isSkiInstructor()) {
                                                dataWriter.writeToFile("Ski instructor: " + instr.getName()
                                                                + " salary (with bonus): $"
                                                                + (sSchool.getSalary() + sSchool.calculateBonusSalary(
                                                                                instr.getHours())));
                                        } else {
                                                dataWriter
                                                                .writeToFile("Snowboard instructor: " + instr.getName()
                                                                                + " salary (with bonus): $"
                                                                                + (sSchool.getSalary() + sSchool
                                                                                                .calculateBonusSalary(
                                                                                                                instr.getHours()))
                                                                                + "\n");

                                        }
                                }
                                sSchool.handleEquipmentRental("ski");
                                sSchool.handleEquipmentRental("ski");
                                sSchool.handleEquipmentRental("ski");
                                sSchool.handleEquipmentRental("ski");
                                sSchool.handleEquipmentRental("ski");
                                sSchool.handleEquipmentRental("ski");
                                sSchool.handleEquipmentRental("snowboard");
                                sSchool.handleEquipmentRental("snowboard");
                                sSchool.handleEquipmentRental("snowboard");
                                sSchool.handleEquipmentRental("snowboard");
                                sSchool.handleEquipmentRental("snowboard");
                                sSchool.handleEquipmentRental("helmet");

                                dataWriter.writeToFile(String.format(
                                                "\nRental Summary for %s:\n- Total skis rented: %d\n- Total snowboards rented: %d\nAnnual income from rentals: $%.2f",
                                                today, sSchool.totalSkiPairsRented(), sSchool.totalSnowboardsRented(),
                                                sSchool.calculateIncomeFromRent()));

                        }

                        SkiSchoolManager schoolManager = new SkiSchoolManager(allSkiSchools);

                        dataWriter.writeToFile("Calculate ski schools total revenue $"
                                        + schoolManager.calculateTotalRevenue());
                        dataWriter
                                        .writeToFile(
                                                        "Calculate ski schools total profit $"
                                                                        + (schoolManager.calculateTotalProfit()
                                                                                        - schoolManager.calculatePaymentToResort(
                                                                                                        rs.getPercentageToResort()))
                                                                        + "\n");
                        dataWriter.writeToFile("Calculate the percentage cost for all ski schools to the resort $"
                                        + schoolManager.calculatePaymentToResort(rs.getPercentageToResort()));

                        dataWriter.writeToFile(
                                        "\n---------------------------------------------------------------------------------------\n");
                }

                // dataReader.readFile("resort_data.txt");
        }
}