package app;

import java.util.List;
import java.util.Scanner;
import app.Entities.*;
import app.Services.HumanResourceService;

public class HumanResources {

    private Scanner scan;
    // service class làm việc với data
    private final HumanResourceService _resourceService;
    // Quản lý
    private Manager _managerAuthenticated = null;
    // Thoát chương trình
    private boolean existProgram = false;

    public HumanResources(Scanner scan) {
        this.scan = scan;
        _resourceService = new HumanResourceService();
    }

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        HumanResources app = new HumanResources(scan);
        do {
            app.showMenu();
            int itemSelected = scan.nextInt();
            scan.nextLine();
            app.handel(itemSelected);
        } while (!app.existProgram);
    }

    // Hiển thị Menu
    public void showMenu() {
        System.out.println("Chon:");
        System.out.println("1.  Hiển thị danh sách nhân viên hiện có trong công ty.");
        System.out.println("2.  Hiển thị các bộ phận trong công ty.");
        System.out.println("3.  Hiển thị các nhân viên theo từng bộ phận.");
        System.out.println("4.  Thêm nhân viên mới vào công ty.");
        System.out.println("5.  Điều chuyển nhân viên từ bộ phần này sang bộ phận khác (quản lý).");
        System.out.println("6.  Thay đổi mức lương của nhân viên (quản lý).");
        System.out.println("7.  Tìm kiếm thông tin nhân viên theo tên hoặc mã nhân viên.");
        System.out.println("8.  Hiển thị bảng lương của nhân viên toàn công ty (thứ tự giảm dần).");
        System.out.println("9.  Hiển thị bảng lương của nhân viên theo thứ tự tăng dần.");
        System.out.println("10. Thoát");
    }

    // Sử lý khi người dùng chon menu
    public void handel(int itemSelected) {
        List<Employee> eList;
        List<Department> dList;
        switch (itemSelected) {
            case 1:
                // hiển thị danh sách nhân viên
                eList = _resourceService.getEmployees();
                showTableEmployees(eList);
                enterToContinue();
                break;
            case 2:
                // hiển thị danh sách phòng ban
                dList = _resourceService.getDepartments();
                showTableDepartments(dList);
                enterToContinue();
                break;
            case 3:
                // hiển thị nhân viên theo phòng ban
                showEmployeesByDepartment();
                enterToContinue();
                break;
            case 4:
                // thêm nhân nhân viên
                createEmployee();
                enterToContinue();
                break;
            case 5:
                // thay đổi phòng ban của nhân viên
                setDepartmentEmployee();
                enterToContinue();
                break;
            case 6:
                // thay đổi lương nhân viên
                setSalaryEmployee();
                enterToContinue();
                break;
            case 7:
                // tìm kiếm nhân viên
                searchEmployee();
                enterToContinue();
                break;
            case 8:
                // hiển thị lương giảm dần
                eList = _resourceService.getEmployees("desc");
                showTableEmployees(eList);
                enterToContinue();
                break;
            case 9:
                // hiển thị lương tăng dần
                eList = _resourceService.getEmployees("asc");
                showTableEmployees(eList);
                enterToContinue();
                break;
            case 10:
                this.existProgram = true;
                System.out.println("Thoát!");
                break;
        }
    }

    // tìm kiếm nhân viên
    private void searchEmployee() {
        System.out.println("Tìm kiếm thông tin nhân viên theo tên hoặc mã nhân viên.");
        System.out.print("Nhập thông tin tìm kiếm: ");
        String query = scan.nextLine();
        List<Employee> employees = _resourceService.findEmployees(query);

        System.out.println("Bạn tìm kiếm cho: " + query);
        showTableEmployees(employees);
    }

    // Hiển thị các bộ phận trong công ty.
    public void showTableDepartments(List<Department> dList) {
        if (dList == null || dList.size() == 0) {
            System.out.println("Không có bộ phận nào!");
            return;
        }

        System.out.println(String.format("|%-20s|%-50s|%-14s|", "Mã phòng ban", "Tên phòng ban", "Số lượng NV"));
        for (Department e : dList) {
            System.err.println(e.toString());
        }
    }

    // Hiển thị các nhân viên theo từng bộ phận
    public void showEmployeesByDepartment() {
        System.out.print("Bạn muốn xem danh sách nhân viên của bộ phận nào (nhập Mã bộ phận): ");
        String departmentId = scan.nextLine();
        Department department = _resourceService.getDepartmentById(departmentId);
        if (department == null) {
            System.out.println("Mã bộ phận không tồn tại");
            return;
        }

        List<Employee> employees = _resourceService.getEmployeesByDepartment(departmentId);

        if (employees == null || employees.size() == 0) {
            System.out.println("Không có nhân viên nào trong bộ phận");
            return;
        }

        System.out.println();
        System.out.println("Danh sách nhân viên trong bộ phận:");
        System.out.println("Tên bộ phận: " + department.getName());
        System.out.println("Id:          " + department.getId());
        showTableEmployees(employees);
    }

    // thêm nhân viên
    public void createEmployee() {
        Employee employee = Employee.createEmployee(scan);

        if(_resourceService.addEmployee(employee)){
            System.out.println("Thêm nhân viên thành công.");
        }else{
            System.out.println("Thêm nhân viên không thành công.");
        }
    }

    // Hiển thị danh sách nhân viên
    public void showTableEmployees(List<Employee> employees) {
        if (employees == null || employees.size() == 0) {
            System.out.println("Không có nhân viên nào!");
            return;
        }

        System.out.println(String.format("|%-10s|%-20s|%-10s|%-20s|%-15s|%10s|%15s|%10s|", "Mã NV", "Tên NV", "Tuổi",
                "Lương NV", "Mã phòng ban", "Ngày nghỉ", "Ngày vào làm", "Chức vị"));
        for (Employee e : employees) {
            System.err.println(e.toString());
        }
    }

    // chức năng đăng nhập cho quản lý
    private void loginToManage() {
        do {
            System.out.print("Nhập mã nhân viên của bạn để đăng nhập:");
            String employeeId = scan.nextLine().trim();
            Employee employee = _resourceService.getEmployeeById(employeeId);
            if (employee != null && employee instanceof Manager) {
                _managerAuthenticated = (Manager) employee;
                System.out.println("Chúc mừng đăng nhập thành công");
                return;
            } else {
                System.out
                        .println("Đăng nhập thất bại: Mã nhân viên của bạn bị sai hoặc bạn không có vai trò quản lý.");
                System.out.print("Đăng nhập lại (Y|N):");
                String input = scan.nextLine().trim();
                if (!input.equalsIgnoreCase("Y")) {
                    return;
                }
            }
        } while (true);
    }

    // Điều chuyển nhân viên từ bộ phần này sang bộ phận khác
    private void setDepartmentEmployee() {
        if (_managerAuthenticated == null) {
            System.out.println("Chức năng yêu cầu vai trò là Quản lý.");
            loginToManage();
            if (_managerAuthenticated == null) {
                return;
            }
        }
        System.out.println("Điều chuyển nhân viên từ bộ này sang bộ phận khác.");
        System.out.print("Mã NV cần điều chuyển: ");
        String employeeId = scan.nextLine().trim();

        System.out.print("Mã bộ phận điều chuyển: ");
        String departmentId = scan.nextLine().trim();

        _managerAuthenticated.setDepartmentForEmployee(employeeId, departmentId);
    }

    // Thay đổi mức lương của nhân viên
    private void setSalaryEmployee() {
        if (_managerAuthenticated == null) {
            System.out.println("Chức năng yêu cầu vai trò là Quản lý.");
            loginToManage();
            if (_managerAuthenticated == null) {
                return;
            }
        }
        System.out.println("Thay đổi mức lương của nhân viên:");
        System.out.print("Mã NV cần thay đổi lương: ");
        String employeeId = scan.nextLine().trim();

        System.out.print("Nhập lương mới cho nhân viên: ");
        double salary = scan.nextDouble();
        scan.nextLine();

        _managerAuthenticated.setSalaryForEmployee(employeeId, salary);

    }

    private void enterToContinue() {
        System.out.print("Nhấn Enter để tiếp tục");
        this.scan.nextLine();
    }
}