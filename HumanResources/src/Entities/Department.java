package app.Entities;

import java.util.Scanner;

import app.Services.HumanResourceService;

/**
 * Department
 */
public class Department {

    private String id;
    private String name;

    // số  lượng nhân viên trong 1 phòng ban
    private int countEmployees;

    public Department(String id, String name) {
        this.id = id.toUpperCase();
        this.name = name;
    }

    public void update(Scanner scan) {
        System.out.print("Nhập Mã bộ phận: ");
        String id = scan.nextLine();

        System.out.print("Nhập tên bộ phận: ");
        String name = scan.nextLine();

        this.id = id;
        this.name = name;
        HumanResourceService humanResourceService = new HumanResourceService();
        this.countEmployees = humanResourceService.getCountEmployeesInDepartment(id);
    }

    @Override
    public String toString() {
        return String.format("|%-20s|%-50s|%-14d|", id, name, getCountEmployees());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id.toUpperCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // số  lượng nhân viên trong phòng ban
    public int getCountEmployees() {
        HumanResourceService humanResourceService = new HumanResourceService();
        countEmployees = humanResourceService.getCountEmployeesInDepartment(id);
        return countEmployees;
    }
}