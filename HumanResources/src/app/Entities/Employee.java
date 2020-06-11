package app.Entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Employee
 */
public class Employee {

    private String id;
    private String name;
    private int age;
    private double salary;
    private Date startDay;
    private String departmentId;
    private int dayOff;

    public Employee(){}

    public Employee(String id, String name, int age, double salary, String departmentId, Date startDay) {
        this.id = id.toUpperCase();
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.departmentId = departmentId;
        this.startDay = startDay;
    }

    public void update(Scanner scan) {
        // Scanner scan = new Scanner(System.in);
        System.out.print("Nhập Mã Nhân Viên: ");
        String id = scan.nextLine().trim();

        System.out.print("Nhập tên nhân viên: ");
        String name = scan.nextLine().trim();

        System.out.print("Nhập tuổi nhân viên: ");
        int age = scan.nextInt();
        scan.nextLine(); // clear nextInt();

        System.out.print("Nhập số ngày nghỉ: ");
        int dayOff = scan.nextInt();
        scan.nextLine(); // clear nextInt();

        System.out.print("Nhập ngày vào làm theo định dạng dd/MM/yyyy:");
        String dateStr = scan.nextLine();
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
        Date startDay = null;
        try {
            startDay = ft.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Ngày vào làm không đúng định dạng");
        }

        this.id = id;
        this.name = name;
        this.age = age;
        this.dayOff = dayOff;
        this.startDay = startDay;
    }

    @Override
    public String toString() {
        

        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");

        String startDayString = ft.format(this.startDay);

        return String.format("|%-10s|%-20s|%-10s|%,20f|%-15s|%10s|%15s|", id, name, age, salary, departmentId, dayOff, startDayString);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    protected void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    protected void setDepartmentId(String departmentID) {
        this.departmentId = departmentID;
    }

    public int getDayOff() {
        return dayOff;
    }

    public void setDayOff(int dayOff) {
        this.dayOff = dayOff;
    }
}