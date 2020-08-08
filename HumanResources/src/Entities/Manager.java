package app.Entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import app.Services.HumanResourceService;

public class Manager extends Employee {
    // chức vụ
    private String position;

    public String getPosition() {
        return position;
    }

    public Manager(String id, String name, int age, double salary, String departmentID, Date date, String position) {
        super(id, name, age, salary, departmentID, date);
        this.position = position;
    }

    @Override
    public String toString() {
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
        String startDayString = ft.format(this.getStartDay());
        return String.format("|%-10s|%-20s|%-10s|%,20f|%-15s|%10s|%15s|%10s|", getId(), getName(), getAge(),
                getSalary(), getDepartmentId(), getDayOff(), startDayString, position);
    }

    private Employee getEmployeeById(String employeeId) {
        HumanResourceService humanResourceService = new HumanResourceService();
        Employee employee = humanResourceService.getEmployeeById(employeeId);
        return employee;
    }

    private boolean existDepartment(String departmentId) {
        HumanResourceService humanResourceService = new HumanResourceService();
        return humanResourceService.existDepartment(departmentId);
    }

    // thay đổi phòng ban cho nhân viên
    public void setDepartmentForEmployee(String employeeId, String departmentId) {
        Employee employee = getEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Mã nhân viên không đúng.");
            return;
        }

        if (!existDepartment(departmentId)) {
            System.out.println("Mã bộ phận không đúng.");
            return;
        }

        employee.setDepartmentId(departmentId);
        System.out.println("Chuyển bộ phận thành công!");
        System.out.println(employee.toString());
    }

    // thay đổi lương nhân viên
    public void setSalaryForEmployee(String employeeId, double salary) {

        Employee employee = getEmployeeById(employeeId);

        if (employee == null) {
            System.out.println("Mã nhân viên không đúng.");
        } else {
            employee.setSalary(salary);
            System.out.println("Sữa lương thành công!");
            System.out.println(employee.toString());
        }
    }
}