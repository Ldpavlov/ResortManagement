package com.example.manager;

import java.util.*;

import com.example.model.SkiSchool;

public class SkiSchoolManager {
    private List<SkiSchool> skiSchools;

    public SkiSchoolManager(List<SkiSchool> skiSchools) {
        this.skiSchools = skiSchools;
    }

    public double calculatePaymentToResort(double percentageToResort) {
        return calculateTotalRevenue() * percentageToResort / 100;
    }

    public double calculateTotalRevenue() {
        double totalRevenue = 0;
        for (SkiSchool skiSchool : skiSchools) {
            totalRevenue += skiSchool.calculateIncomeFromRent();
            totalRevenue += getInstructorsHours(skiSchool) * 50;
        }
        return totalRevenue;
    }

    public double calculateTotalProfit() {
        double totalProfit = 0;
        double revenue = calculateTotalRevenue();
        double instructorsSalaries = 0;

        for (SkiSchool skiSchool : skiSchools) {
            instructorsSalaries = (skiSchool.getSalary() + getInstructorsBonusSalary(skiSchool))
                    * skiSchool.getInstructors().size();
            revenue = revenue - instructorsSalaries;
        }
        totalProfit = revenue;
        return totalProfit;
    }

    public double getInstructorsHours(SkiSchool skiSchool) {
        double hours = 0;
        for (int i = 0; i < skiSchool.getInstructors().size(); i++) {
            hours += skiSchool.getInstructors().get(i).getHours();
        }

        return hours;
    }

    public double getInstructorsBonusSalary(SkiSchool skiSchool) {
        List<Double> totalHoursWorkedList = skiSchool.getTotalHoursWorkedByInstructor();
        double bonusSalary = 0;
        for (int i = 0; i < totalHoursWorkedList.size(); i++) {
            double bonusRate = skiSchool.getBonusRate();
            double hours = totalHoursWorkedList.get(i);
            bonusSalary += hours * bonusRate;
        }
        return bonusSalary;
    }

}