package Model;

import MyEnum.SalaryType;

import java.time.LocalDate;
import java.util.UUID;

public class Employee extends Person {
    int employeeID;
    UUID employeeGUID;

    String contractNumber;
    LocalDate startDate;
    double salary;
    SalaryType salaryType;

    int companyID;


    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public UUID getEmployeeGUID() {
        return employeeGUID;
    }

    public void setEmployeeGUID(UUID employeeGUID) {
        this.employeeGUID = employeeGUID;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public SalaryType getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(SalaryType salaryType) {
        this.salaryType = salaryType;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }


}
