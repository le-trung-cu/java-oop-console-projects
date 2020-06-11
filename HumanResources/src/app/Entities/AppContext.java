package app.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


// Database
public class AppContext {
    
    public final List<Employee> Employees;
    public final List<Department> Departments;

    private static AppContext _instance;

    public static AppContext getInstance(){
        if(_instance == null){
            _instance = new AppContext();
        }
        return _instance;
    }

    private AppContext() {
        Employees = new ArrayList<Employee>();
        Departments = new ArrayList<Department>();
        // khởi tạo dữ liệu cho test
        seedData();
    }

    public void seedData() {
        Employees.add(new Employee("DT1", "name 1", 20, 5000000, "DT", new Date()));
        Employees.add(new Employee("DT2", "name 2", 20, 5000000, "DT", new Date()));
        Employees.add(new Employee("DT3", "name 3", 20, 5000000, "DT", new Date()));
        Employees.add(new Employee("THXD1", "name 4", 20, 5000000, "THXD", new Date()));
        Employees.add(new Employee("THXD2", "name 5", 20, 5000000, "THXD", new Date()));
        Employees.add(new Employee("TP1", "name 6", 20, 5000000, "TP", new Date()));
        Employees.add(new Employee("TP1", "name 6", 20, 5000000, "TP", new Date()));
        Employees.add(new Employee("TP2", "name 6", 20, 5000000, "TP", new Date()));

        Employees.add(new Manager("THXD3", "name 5", 20, 5000000, "THXD", new Date(), "cv1"));
        Employees.add(new Manager("TP3", "name 5", 20, 15000000, "THXD", new Date(), "cv1"));

        Departments.add(new Department("DT", "DT"));
        Departments.add(new Department("THXD", "THXD"));
        Departments.add(new Department("TP", "TP"));
    }
}