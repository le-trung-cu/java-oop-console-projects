package app.Entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import app.Services.HumanResourceService;

public class Manager extends Employee {

    public String getPosition() {
        return position;
    }

    public Manager(String id, String name, int age, double salary, 
            String departmentID, Date date, String position ) {
        super(id, name, age, salary, departmentID, date);
        this.position = position;
    }

    public void setDepartmentForEmployee(String employeeId, String departmentId) {
        Employee employee = getEmployee(employeeId);
        if (employee == null) {
            System.out.println("Không Tìm Thấy nhân viên id = " + employeeId);
        } else {
            employee.setDepartmentId(departmentId);
            System.out.println("Chuyển bộ phận thành công!");
            System.out.println(employee.toString());
        }
    }

    public void setSalaryForEmployee(String employeeId, double salary) {
        Employee employee = getEmployee(employeeId);
        if (employee == null) {
            System.out.println("Không Tìm Thấy nhân viên id = " + employeeId);
        } else {
            employee.setSalary(salary);
            System.out.println("Sữa lương thành công!");
            System.out.println(employee.toString());
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
        String startDayString = ft.format(this.getStartDay());
        return String.format("|%-10s|%-20s|%-10s|%,20f|%-15s|%10s|%15s|%10s|", getId(), getName(), getAge(), getSalary(), getDepartmentId(),
                getDayOff(), startDayString, position);
    }

    private Employee getEmployee(String employeeId) {
        HumanResourceService humanResourceService = new HumanResourceService();
        Employee employee = humanResourceService.getEmployeeById(employeeId);
        return employee;
    }

    private String position;
}