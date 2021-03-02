package SortingStation;

import employee.Employee;

import java.util.ArrayList;

public class SortingStation {
    private ArrayList<Employee> employees;

    public SortingStation(){
        employees = new ArrayList<>();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
}
