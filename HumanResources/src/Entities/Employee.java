package app.Entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Employee
 */
public class Employee {

    // mã nhân viên
    private String id;
    private String name;
    private int age;
    private double salary;

    // ngày vào làm
    private Date startDay;
    // mã phòng ban
    private String departmentId;
    // ngày nghỉ
    private int dayOff;

    public Employee() {
    }

    public Employee(String id, String name, int age, double salary, String departmentId, Date startDay) {
        this.id = id.toUpperCase();
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.departmentId = departmentId;
        this.startDay = startDay;
    }

    public Employee(String id, String name, int age, double salary, String departmentId, Date startDay, int dayOff) {
        this.id = id.toUpperCase();
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.departmentId = departmentId;
        this.startDay = startDay;
        this.dayOff = dayOff;
    }

    // tạo nhân viên
    public static Employee createEmployee(Scanner scan) {
        System.out.print("Nhập Mã Nhân Viên: ");
        String id = scan.nextLine().trim();

        System.out.print("Nhập tên nhân viên: ");
        String name = scan.nextLine().trim();

        System.out.print("Nhập tuổi nhân viên: ");
        int age = scan.nextInt();
        scan.nextLine();

        System.out.print("Nhập lương nhân viên: ");
        double salary = scan.nextDouble();
        scan.nextLine();

        System.out.print("Nhập Mã Phòng Ban: ");
        String departmentId = scan.nextLine().trim();

        System.out.print("Nhập số ngày nghỉ: ");
        int dayOff = scan.nextInt();
        scan.nextLine();

        Date startDay = null;
        do {
            System.out.print("Nhập ngày vào làm theo định dạng dd/MM/yyyy:");
            String dateStr = scan.nextLine();
            SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
            try {
                startDay = ft.parse(dateStr);
                break;
            } catch (ParseException e) {
                System.out.println("Ngày vào làm không đúng định dạng");
            }
        } while (true);

        Employee employee = new Employee(id, name, age, salary, departmentId, startDay, dayOff);
        return employee;
    }

    // cập nhật thông tin nhân viên
    public void update(Scanner scan){

        System.out.print("Nhập tên nhân viên: ");
        String name = scan.nextLine().trim();

        System.out.print("Nhập tuổi nhân viên: ");
        int age = scan.nextInt();
        scan.nextLine();

        System.out.print("Nhập số ngày nghỉ: ");
        int dayOff = scan.nextInt();
        scan.nextLine();

        Date startDay = null;
        do {
            System.out.print("Nhập ngày vào làm theo định dạng dd/MM/yyyy:");
            String dateStr = scan.nextLine();
            SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
            try {
                startDay = ft.parse(dateStr);
                break;
            } catch (ParseException e) {
                System.out.println("Ngày vào làm không đúng định dạng");
            }
        } while (true);

        this.name = name;
        this.age = age;
        this.startDay = startDay;
        this.dayOff = dayOff;
    }

    @Override
    public String toString() {
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");

        String startDayString = ft.format(this.startDay);

        return String.format("|%-10s|%-20s|%-10s|%,20f|%-15s|%10s|%15s|", id, name, age, salary, departmentId, dayOff,
                startDayString);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

   
    public double getSalary() {
        return salary;
    }


    public Date getStartDay() {
        return startDay;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public int getDayOff() {
        return dayOff;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}