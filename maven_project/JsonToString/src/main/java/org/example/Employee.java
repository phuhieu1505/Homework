package org.example;

class Employee {
    private int EmpID;
    private String EmpName;

    public Employee() {
    }

    public int getEmpID() {
        return EmpID;
    }

    public void setEmpID(int empID) {
        EmpID = empID;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }


    public Employee(int empID, String empName) {
        EmpID = empID;
        EmpName = empName;
    }
}
