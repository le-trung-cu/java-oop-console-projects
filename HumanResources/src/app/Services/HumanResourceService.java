package app.Services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import app.Entities.*;

public class HumanResourceService {

    private final AppContext _context;

    public HumanResourceService() {
        _context = AppContext.getInstance();
    }

    public void addEmployee(Employee employee) {
        _context.Employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return _context.Employees;
    }

    public List<Employee> getEmployees(String order) {
        List<Employee> employees = new ArrayList<Employee>(_context.Employees);
        switch (order) {
            case "asc":
                employees.sort(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {
                        return (int) (o2.getSalary() - o1.getSalary());
                    }
                });
                break;
        }
        return employees;
    }

    public List<Employee> getEmployeesByDepartment(String departmentId) {
        List<Employee> employees = new ArrayList<Employee>();
        for (Employee employee : _context.Employees) {
            if (employee.getDepartmentId().equalsIgnoreCase(departmentId)) {
                employees.add(employee);
            }
        }
        return employees;
    }

    public Employee getEmployeeById(String employeeId) {
        for (Employee employee : _context.Employees) {
            if (employee.getId().equalsIgnoreCase(employeeId.toUpperCase())) {
                return employee;
            }
        }
        return null;
    }

    public List<Employee> findEmployees(String query) {
        List<Employee> employees = new ArrayList<Employee>();

        for (Employee employee : _context.Employees) {
            if (employee.getId().contains(query.toUpperCase())
                    || employee.getName().toLowerCase().contains(query.toLowerCase())) {
                employees.add(employee);
            }
        }
        return employees;
    }

    public int getCountEmployeesInDepartment(String departmentId) {
        int count = 0;
        for (Employee employee : _context.Employees) {
            if (employee.getDepartmentId().equalsIgnoreCase(departmentId)) {
                count++;
            }
        }
        return count;
    }

    public List<Department> getDepartments() {
        return _context.Departments;
    }

    public Department getDepartmentById(String departmentId) {
        for (Department department : _context.Departments) {
            if (department.getId().equalsIgnoreCase(departmentId)) {
                return department;
            }
        }
        return null;
    }

    public boolean ExistDepartment(String departmentId) {
        for (Department department : _context.Departments) {
            if (department.getId().equalsIgnoreCase(departmentId)) {
                return true;
            }
        }
        return false;
    }

    public boolean ExistEmployee(String employeeId) {
        for (Employee employee : _context.Employees) {
            if (employee.getId().equalsIgnoreCase(employeeId)) {
                return true;
            }
        }
        return false;
    }
}