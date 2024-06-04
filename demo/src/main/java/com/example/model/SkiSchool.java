package com.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.common.BonusCalculator;
import com.example.common.NoSkisAvailableException;
import com.example.data.DataWriterInterface;

public class SkiSchool implements BonusCalculator {
    private String name;
    private double salary;
    private double bonusRate;
    private int totalSkiRented;
    private int totalSnowsRented;
    private int availableSkis;
    private int availableSnows;
    private double skiRentPricePerDay;
    private List<Instructor> instructors;
    private int dailyAverageRentedSkis;
    private int totalDaysWorked;
    private DataWriterInterface dataWriter; // Use the interface
    private LocalDate today = LocalDate.now();

    public SkiSchool(String name, double salary, double bonusRate, double skiRentPricePerDay, int availableSkis,
            int availableSnows, int dailyAverageRentedSkis, int totalDaysWorked, DataWriterInterface dataWriter) {
        this.name = name;
        this.salary = salary;
        this.bonusRate = bonusRate;
        this.skiRentPricePerDay = skiRentPricePerDay;
        this.instructors = new ArrayList<>();
        this.availableSkis = availableSkis;
        this.availableSnows = availableSnows;
        this.dailyAverageRentedSkis = dailyAverageRentedSkis;
        this.totalDaysWorked = totalDaysWorked;
        this.dataWriter = dataWriter;
    }

    public double calculateIncomeFromRent() {
        return (totalDaysWorked * dailyAverageRentedSkis) * skiRentPricePerDay;
    }

    public int totalSkiPairsRented() {
        return this.totalSkiRented;
    }

    public int totalSnowboardsRented() {
        return this.totalSnowsRented;
    }

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    public int countSnowboardInstructors() {
        int count = 0;
        for (Instructor instructor : instructors) {
            if (!instructor.isSkiInstructor()) {
                count++;
            }
        }
        return count;
    }

    public List<Double> getTotalHoursWorkedByInstructor() {
        List<Double> totalHoursList = new ArrayList<>();
        for (Instructor instructor : instructors) {
            totalHoursList.add(instructor.getHours());
        }
        return totalHoursList;
    }

    @Override
    public double calculateBonusSalary(double hours) {
        return hours * getBonusRate();
    }

    public int rentEquipment(String equipmentType) throws NoSkisAvailableException {
        if (equipmentType.equalsIgnoreCase("ski")) {
            if (availableSkis <= 0) {
                dataWriter.writeToFile("No skis available for rent.");
                throw new NoSkisAvailableException("ERROR: No more available skis today on: " + today + " for rent.");
            }
            totalSkiRented++;
            return --availableSkis;
        } else if (equipmentType.equalsIgnoreCase("snowboard")) {
            if (availableSnows <= 0) {
                dataWriter.writeToFile("No snowboards available for rent.");
                throw new NoSkisAvailableException(
                        "ERROR: No more available snowboards today on: " + today + " for rent.");
            }
            totalSnowsRented++;
            return --availableSnows;
        } else {
            dataWriter.writeToFile("Unknown equipment type: " + equipmentType);
            throw new IllegalArgumentException("Unknown equipment type: " + equipmentType);
        }
    }

    public void handleEquipmentRental(String equipmentType) {
        try {
            int rentedCount = rentEquipment(equipmentType);
            dataWriter.writeToFile(equipmentType.substring(0, 1).toUpperCase() + equipmentType.substring(1) +
                    " rented successfully. " + equipmentType + "s left: " + rentedCount);
        } catch (NoSkisAvailableException e) {
            System.out.println(e.getMessage());
            dataWriter.writeToFile(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid equipment type: " + equipmentType);
            dataWriter.writeToFile("Invalid equipment type: " + equipmentType);
        }
    }

    public double getBonusRate() {
        return bonusRate;
    }

    public double getSkiRentPricePerDay() {
        return skiRentPricePerDay;
    }

    public String getName() {
        return name;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public double getSalary() {
        return salary;
    }

    public int getAvailableSkis() {
        return availableSkis;
    }

    public int getAvailableSnows() {
        return availableSnows;
    }

    public void setAvailableSkis(int availableSkis) {
        this.availableSkis = availableSkis;
    }

    public void setAvailableSnows(int availableSnows) {
        this.availableSnows = availableSnows;
    }
}
