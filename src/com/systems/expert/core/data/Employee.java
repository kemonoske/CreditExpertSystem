package com.systems.expert.core.data;

import java.time.LocalDate;

/**
 * Created by Kage on 2/22/14.
 */
public interface Employee {

    public String getOrganisation();
    public void setOrganisation(String organisation);

    public String getJob();
    public void setJob(String job);

    public LocalDate getHireDate();
    public void setHireDate(LocalDate hireDate);

    public double getSalary();
    public void setSalary(double salary);

    public double getAddIncome();
    public void setAddIncome(double addIncome);

    public boolean isSalaryInvoicePresent();
    public void setSalaryInvoicePresent(boolean salaryInvoicePresent);

    public boolean isWorkCardCopyPresent();
    public void setWorkCardCopyPresent(boolean workCardCopyPresent);

    public int getMonthWorking();

}
