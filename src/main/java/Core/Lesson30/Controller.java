package Core.Lesson30;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    public List<Employee> employeesByProject(String projectName) {
        List<Employee> employeesByProject = new ArrayList<>();

        for (Project project : ProjectDAO.projects) {
            if (project.getName().equalsIgnoreCase(projectName)) {
                for (Employee employee : EmployeeDAO.employees) {
                    if (employee.getProjects().contains(project)) {
                        employeesByProject.add(employee);
                    }
                }
                break;
            }
        }
        return employeesByProject;
    }

    public List<Project> projectsByEmployee(Employee employee) {
        return employee.getProjects();
    }

    public List<Employee> employeesByDepartmentWithoutProject(DepartmentType departmentType) {
        List<Employee> employeesByDepartmentWithoutProject = new ArrayList<>();
        for (Department departments : DepartmentDAO.departments) {
           if (departments.getType() == departmentType){
               for (Employee employees: departments.getEmployees()) {
                   if (employees.getProjects().size() == 0){
                       employeesByDepartmentWithoutProject.add(employees);
                   }
               }
           }
        }
        return employeesByDepartmentWithoutProject;
    }

    public List<Employee> employeesWithoutProject() {
        List<Employee> employeesWithoutProject = new ArrayList<>();
        for (Employee employee : EmployeeDAO.employees) {
            if (employee.getProjects().size() == 0) {
                employeesWithoutProject.add(employee);
            }
        }
        return employeesWithoutProject;
    }

    public List<Employee> employeesByTeamLead(Employee lead) {
        List<Employee> employeesByTeamLead = new ArrayList<>();

        for (Project leadProjects : lead.getProjects()) {
            for (Employee employees : employeesByProject(leadProjects.getName())) {
                if (employees.getPosition() != Position.TEAM_LEAD) {//Для тим-лида подчиненные все, как я понимаю.
                    employeesByTeamLead.add(employees);
                }
            }
        }
        return employeesByTeamLead;
    }


    public List<Employee> teamLeadsByEmployee(Employee employee) {
        List<Employee> teamLeadsByEmployee = new ArrayList<>();

        for (Project projects : employee.getProjects()) {
            for (Employee employees : employeesByProject(projects.getName())) {
                if (employees.getPosition() == Position.TEAM_LEAD) {
                    teamLeadsByEmployee.add(employees);
                }
            }
        }
        return teamLeadsByEmployee;
    }

    public List<Employee> employeesByProjectEmployee(Employee employee) {
        List<Employee> employeesByProjectEmployee = new ArrayList<>();
        for (Project projectByEmployee : projectsByEmployee(employee)) {
            employeesByProjectEmployee.addAll(employeesByProject(projectByEmployee.getName()));
        }
        return employeesByProjectEmployee;
    }

    public List<Project> projectsByCustomer(Customer customer) {
        List<Project> projectsByCustomer = new ArrayList<>();
        for (Project project : ProjectDAO.projects) {
            if (project.getCustomer() == customer) {
                projectsByCustomer.add(project);
            }
        }

        return projectsByCustomer;
    }

    public List<Employee> employeesByCustomerProjects(Customer customer) {
        List<Employee> employeesByCustomerProjects = new ArrayList<>();

        for (Project customerProjects : projectsByCustomer(customer)) {
            employeesByCustomerProjects.addAll(employeesByProject(customerProjects.getName()));
        }
        return employeesByCustomerProjects;
    }
}
