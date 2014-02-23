package com.systems.expert.core.data;

import java.time.LocalDate;
import java.time.Period;

/**
 * Created by Kage on 2/20/14.
 */
public class Client implements Person, Employee, Debitor{

    /**
     * Personal
     */
    private String name;
    private String address;
    private LocalDate birthDate;
    private boolean moldovanNationality;
    private String sex;
    private boolean identityCardCopyPresent;
    private Nationality nationality;

    /**
     * Financial
     */
    private String organisation;
    private String job;
    private LocalDate hireDate;
    private double salary;
    private double addIncome;
    private int maintainMatures;
    private int maintainUnderage;
    private boolean possessingHouse;
    private boolean possessingAuto;
    private boolean salaryInvoicePresent;
    private boolean workCardCopyPresent;
    private boolean possessingDebts;

    /**
     * Credit
     */
    private double creditAmount;
    private int creditDuration;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public int getAge() {

        LocalDate today = LocalDate.now();

        Period age = Period.between(birthDate, today);

        return age.getYears();
    }

    @Override
    public boolean isIdentityCardCopyPresent() {
        return identityCardCopyPresent;
    }

    @Override
    public void setIdentityCardCopyPresent(boolean identityCardCopyPresent) {
        this.identityCardCopyPresent = identityCardCopyPresent;
    }

    @Override
    public Nationality getNationality() {
        return nationality;
    }

    @Override
    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    @Override
    public int getMaintainMatures() {
        return maintainMatures;
    }

    @Override
    public void setMaintainMatures(int maintainMatures) {
        this.maintainMatures = maintainMatures;
    }

    @Override
    public int getMaintainUnderage() {
        return maintainUnderage;
    }

    @Override
    public void setMaintainUnderage(int maintainUnderage) {
        this.maintainUnderage = maintainUnderage;
    }

    @Override
    public boolean isPossessingHouse() {
        return possessingHouse;
    }

    @Override
    public void setPossessingHouse(boolean possessingHouse) {
        this.possessingHouse = possessingHouse;
    }

    @Override
    public boolean isPossessingAuto() {
        return possessingAuto;
    }

    @Override
    public void setPossessingAuto(boolean possessingAuto) {
        this.possessingAuto = possessingAuto;
    }


    @Override
    public String getOrganisation() {
        return organisation;
    }

    @Override
    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    @Override
    public String getJob() {
        return job;
    }

    @Override
    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public LocalDate getHireDate() {
        return hireDate;
    }

    @Override
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public double getAddIncome() {
        return addIncome;
    }

    @Override
    public void setAddIncome(double addIncome) {
        this.addIncome = addIncome;
    }

    @Override
    public boolean isSalaryInvoicePresent() {
        return salaryInvoicePresent;
    }

    @Override
    public void setSalaryInvoicePresent(boolean salaryInvoicePresent) {
        this.salaryInvoicePresent = salaryInvoicePresent;
    }

    @Override
    public boolean isWorkCardCopyPresent() {
        return workCardCopyPresent;
    }

    @Override
    public void setWorkCardCopyPresent(boolean workCardCopyPresent) {
        this.workCardCopyPresent = workCardCopyPresent;
    }

    @Override
    public int getMonthWorking() {

        LocalDate today = LocalDate.now();

        Period age = Period.between(hireDate, today);

        return age.getMonths() + age.getYears() * 12;
    }


    public boolean isPossessingDebts(){
        return  possessingDebts;
    }

    public void setPossessingDebts(boolean possessingDebts){
        this.possessingDebts = possessingDebts;
    }

    @Override
    public double getCreditAmount() {
        return creditAmount;
    }

    @Override
    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    @Override
    public int getCreditDuration() {
        return creditDuration;
    }

    @Override
    public void setCreditDuration(int creditDuration) {
        this.creditDuration = creditDuration;
    }
}
