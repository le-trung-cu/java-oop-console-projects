package app.Services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import app.Entities.*;

public class HumanResourceService {
    // data context
    private final AppContext _context;

    public HumanResourceService() {
        _context = AppContext.getInstance();
    }

    // thêm nhân viên
    public boolean addEmployee(Employee employee) {
        if(existEmployee(employee.getId())){
            System.out.println("Mã nhân viên đã tồn tại");
            return false;
        }else if(!existDepartment(employee.getDepartmentId())){
            System.err.println("Mã phòng ban không đúng.");
            return false;
        }
        return _context.Employees.add(employee);
    }

    // lấy danh sách nhân viên
    public List<Employee> getEmployees() {
        return _context.Employees;
    }

    // lấy danh sách nhân viên, sắp xếp theo mức lương tăng dần hoăc giảm dần
    public List<Employee> getEmployees(String order) {
        List<Employee> employees = new ArrayList<Employee>(_context.Employees);
        switch (order) {
            case "asc":
                // mức lương tăng dần
                employees.sort(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {
                        return (int) (o1.getSalary() - o2.getSalary());
                    }
                });
                break;
            case "desc":
                // mức lương giảm dần
                employees.sort(new Comparator<Employee>(){
                    @Override
                    public int compare(Employee o1, Employee o2) {
                        return (int) (o2.getSalary() - o1.getSalary());
                    }
                });
            break;
        }
        return employees;
    }

    // lấy danh nhân viên theo mã phòng ban
    public List<Employee> getEmployeesByDepartment(String departmentId) {
        List<Employee> employees = new ArrayList<Employee>();
        for (Employee employee : _context.Employees) {
            if (employee.getDepartmentId().equalsIgnoreCase(departmentId)) {
                employees.add(employee);
            }
        }
        return employees;
    }

    // lấy nhân viên bởi mã nhân viên
    public Employee getEmployeeById(String employeeId) {
        for (Employee employee : _context.Employees) {
            if (employee.getId().equalsIgnoreCase(employeeId.toUpperCase())) {
                return employee;
            }
        }
        return null;
    }

    // tìm nhân viên bởi mã nhân viên | tên nhân viên
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

    // số  lượng nhân viên trong 1 phòng ban
    public int getCountEmployeesInDepartment(String departmentId) {
        int count = 0;
        for (Employee employee : _context.Employees) {
            if (employee.getDepartmentId().equalsIgnoreCase(departmentId)) {
                count++;
            }
        }
        return count;
    }

    // lấy danh sách phòng ban
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

    // kiểm tra mã phòng ban có tồn tại
    public boolean existDepartment(String departmentId) {
        for (Department department : _context.Departments) {
            if (department.getId().equalsIgnoreCase(departmentId)) {
                return true;
            }
        }
        return false;
    }

    // kiểm tra mã nhân viên
    public boolean existEmployee(String employeeId) {
        for (Employee employee : _context.Employees) {
            if (employee.getId().equalsIgnoreCase(employeeId)) {
                return true;
            }
        }
        return false;
    }
}