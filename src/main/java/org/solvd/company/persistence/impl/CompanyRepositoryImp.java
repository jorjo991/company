package org.solvd.company.persistence.impl;

import org.solvd.company.domain.budget.Budget;
import org.solvd.company.domain.budget.Salary;
import org.solvd.company.domain.client.Client;
import org.solvd.company.domain.company.Address;
import org.solvd.company.domain.company.Company;
import org.solvd.company.domain.department.Department;
import org.solvd.company.domain.employees.Employee;
import org.solvd.company.domain.employees.EmployeeType;
import org.solvd.company.domain.office.Office;
import org.solvd.company.domain.office.Room;
import org.solvd.company.domain.project.Project;
import org.solvd.company.domain.task.Task;
import org.solvd.company.persistence.CompanyRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class CompanyRepositoryImp implements CompanyRepository {

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void create(Company company) {
        Connection connection = connectionPool.getConnection();
        String createStatement = "Insert into companies (name) values (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createStatement, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, company.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                company.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Company company) {
        Connection connection = connectionPool.getConnection();
        String updateCommand = "update companies  set name =? where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateCommand)) {
            preparedStatement.setLong(2, company.getId());
            preparedStatement.setString(1, company.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Company> get(Long id) {
        Connection connection = connectionPool.getConnection();
        // this query join company-> clients , office -> address , rooms   5 table join in general
        String getCommand = "select  \n" +
                "c.id as company_id, c.name as company_name,  \n" +
                "r.id as room_id,r.capacity as room_capacity, r.available as room_available ,r.room_number, \n" +
                "a.id as address_id, a.city as address_city , a.street as address_street , a.number as address_number,\n" +
                "o.id as office_id , o.name as office_name, o.capacity as office_capacity, \n" +
                "cl.id as client_id,cl.name as client_name , cl.surname as client_surname , cl.email as client_email , cl.birthday as client_birthday,cl.active as client_active,cl.age as client_age\n" +
                "from companies c   \n" +
                "left join  offices o on c.id=o.company_id\n" +
                "left join  rooms r on o.id=r.office_id \n" +
                "left join  addresses a on o.id=a.office_id \n" +
                "left join clients cl on c.id=cl.company_id where c.id=?";
        try (PreparedStatement statement = connection.prepareStatement(getCommand)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Company company = new Company();
            List<Client> clients = new ArrayList<>();
            List<Office> offices = new ArrayList<>();
            List<Long> officeIds = new ArrayList<>();
            Long companyId = 0L;
            String companyName = "";
            while (resultSet.next()) {
                companyId = resultSet.getLong("company_id");
                companyName = resultSet.getString("company_name");

                // get client from joined tables
                Client client = getClient(resultSet.getInt("client_age"),
                        resultSet.getString("client_name"),
                        resultSet.getString("client_surname"),
                        resultSet.getString("client_email"),
                        resultSet.getDate("client_birthday").toLocalDate(), resultSet.getBoolean("client_active"), resultSet.getLong("client_id"));
                if (!clients.contains(client)) clients.add(client);

                // get offices of company and their rooms
                Room currentRoom = getRoom(resultSet.getLong("room_id"), resultSet.getInt("room_capacity"), resultSet.getBoolean("room_available"), resultSet.getString("room_number"));
                Long currentOfficeID = resultSet.getLong("office_id");
                if (!officeIds.contains(currentOfficeID)) {
                    Address address = getAddress(resultSet.getLong("address_id"),
                            resultSet.getString("address_city"),
                            resultSet.getString("address_street"), resultSet.getString("address_number"));
                    officeIds.add(currentOfficeID);
                    Office office = getOffice(currentOfficeID, resultSet.getString("office_name"), resultSet.getInt("office_capacity"), address, new ArrayList<>());
                    office.getRooms().add(currentRoom);
                    offices.add(office);
                } else {
                    for (Office office : offices) {
                        if (office.getId() == currentOfficeID) {
                            if (!office.getRooms().contains(currentRoom)) office.getRooms().add(currentRoom);
                        }
                    }
                }
            }
            company.setClients(clients);
            company.setOffices(offices);
            company.setId(companyId);
            company.setName(companyName);

            //  map departments->(project->task),(employee->salary)
            String query = "SELECT  c.id AS company_id,c.name AS company_name,\n" +
                    "    d.id AS department_id,d.name AS department_name,d.number AS department_number,\n" +
                    "    e.id AS employee_id,e.name AS employee_name,e.age AS employee_age,e.surname AS employee_surname,e.email AS employee_email,e.birth_day AS employee_birth_day, e.employee_type AS employee_type,\n" +
                    "    s.id AS salary_id,s.amount AS salary_amount,s.bonus as salary_bonus, s.cut_percentage,\n" +
                    "    p.id AS project_id,p.name AS project_name, p.finished as project_finished,\n" +
                    "    t.id AS task_id, t.name AS task_name, t.start_time as task_start_time, t.end_time as task_end_time\n" +
                    "FROM companies c\n" +
                    "LEFT JOIN departments d ON d.company_id = c.id\n" +
                    "LEFT JOIN employees e ON e.department_id = d.id\n" +
                    "LEFT JOIN salary s ON s.employee_id = e.id\n" +
                    "LEFT JOIN projects p ON p.department_id = d.id\n" +
                    "JOIN tasks t ON t.project_id = p.id where c.id=?\n" +
                    "ORDER BY c.id, d.id, e.id, p.id, t.id;";
            try (PreparedStatement preparedStatement1 = connection.prepareStatement(query)) {

                preparedStatement1.setLong(1, id);
                ResultSet resultSet1 = preparedStatement1.executeQuery();

                Map<Long, Department> departmentMap = new HashMap<>();
                Map<Long, Project> projectMap = new HashMap<>();
                Map<Long, Task> taskMap = new HashMap<>();
                Map<Long, Employee> employeeMap = new HashMap<>();

                while (resultSet1.next()) {
                    Salary salary = getSalary(resultSet1.getLong("salary_id"),
                            resultSet1.getDouble("salary_amount"),
                            resultSet1.getDouble("salary_bonus"),
                            resultSet1.getInt("cut_percentage"));

                    Employee employee = getEmployee(resultSet1.getLong("employee_id"),
                            resultSet1.getInt("employee_age"),
                            resultSet1.getString("employee_name"),
                            resultSet1.getString("employee_surname"),
                            resultSet1.getString("employee_email"),
                            resultSet1.getDate("employee_birth_day").toLocalDate(),
                            resultSet1.getString("employee_type"), salary, new ArrayList<>(), new ArrayList<>());
                    Task task = getTask(resultSet1.getLong("task_id"),
                            resultSet1.getString("task_name"),
                            resultSet1.getDate("task_start_time").toLocalDate(),
                            resultSet1.getDate("task_end_time").toLocalDate());

                    Project project = getProject(resultSet1.getLong("project_id"),
                            resultSet1.getString("project_name"),
                            new ArrayList<>(),
                            resultSet1.getBoolean("project_finished"));

                    Department department = getDepartment(resultSet1.getLong("department_id"),
                            resultSet1.getString("department_name"),
                            resultSet1.getString("department_number"),
                            new ArrayList<>(), new ArrayList<>());

                    if (!departmentMap.containsKey(department.getId())) {
                        departmentMap.put(department.getId(), department);
                    } else {
                        department = departmentMap.get(department.getId());
                    }
                    if (!employeeMap.containsKey(employee.getId())) {
                        employeeMap.put(employee.getId(), employee);
                    } else {
                        employee = employeeMap.get(employee.getId());
                    }
                    if (!department.getEmployees().contains(employee)) {
                        department.getEmployees().add(employee);
                    }

                    if (!projectMap.containsKey(project.getId())) {
                        projectMap.put(project.getId(), project);
                    } else {
                        project = projectMap.get(project.getId());
                    }
                    if (!department.getProjects().contains(project)) {
                        department.getProjects().add(project);
                    }

                    if (!taskMap.containsKey(task.getId())) {
                        taskMap.put(task.getId(), task);
                    } else {
                        task = taskMap.get(task.getId());
                    }
                    if (!project.getTasks().contains(task)) {
                        project.getTasks().add(task);
                    }
                }
                company.setDepartments(departmentMap.values().stream().toList());

                String selectForBudget = "select  b.id as budget_id, b.total_amount as budget_totalAmount ,  \n" +
                        "b.spend_amount as budget_spent, b.description \n" +
                        "as budget_description from companies c \n" +
                        "left join budgets b on c.id=b.company_id  where c.id=?";
                Budget budget = new Budget();

                try (PreparedStatement budgetSelect = connection.prepareStatement(selectForBudget)) {
                    budgetSelect.setLong(1, id);
                    ResultSet budgetQueryResult = budgetSelect.executeQuery();
                    while (budgetQueryResult.next()) {
                        budget = getBudget(budgetQueryResult.getLong("budget_id"),
                                budgetQueryResult.getDouble("budget_totalAmount"),
                                budgetQueryResult.getDouble("budget_spent"),
                                budgetQueryResult.getString("budget_description"));
                    }
                    company.setBudget(budget);
                }

            }
            return Optional.of(company);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }

    @Override
    public void delete(Long id) {
        Connection connection = connectionPool.getConnection();
        String deleteString = "Delete from companies where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteString)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Company> readAll() {
        Connection connection = connectionPool.getConnection();
        Map<Long, Company> companyMap = new HashMap<>();
        // this query join company-> clients , office -> address , rooms   5 table join in general
        String getCommand = "select  \n" +
                "c.id as company_id, c.name as company_name,  \n" +
                "r.id as room_id,r.capacity as room_capacity, r.available as room_available ,r.room_number, \n" +
                "a.id as address_id, a.city as address_city , a.street as address_street , a.number as address_number,\n" +
                "o.id as office_id , o.name as office_name, o.capacity as office_capacity, \n" +
                "cl.id as client_id,cl.name as client_name , cl.surname as client_surname , cl.email as client_email , cl.birthday as client_birthday,cl.active as client_active,cl.age as client_age\n" +
                "from companies c   \n" +
                "left join  offices o on c.id=o.company_id\n" +
                "left join  rooms r on o.id=r.office_id \n" +
                "left join  addresses a on o.id=a.office_id \n" +
                "left join clients cl on c.id=cl.company_id ";

        try (PreparedStatement statement = connection.prepareStatement(getCommand)) {
            ResultSet resultSet = statement.executeQuery();
            List<Client> clients = new ArrayList<>();
            List<Office> offices = new ArrayList<>();
            List<Long> officeIds = new ArrayList<>();
            Long companyId = 0L;
            String companyName = "";
            Company company = new Company();
            while (resultSet.next()) {

                // if new company occurred
                if (!companyMap.containsKey(resultSet.getLong("company_id"))) {
                    System.out.println(company);
                    company = new Company();
                    clients = new ArrayList<>();
                    offices = new ArrayList<>();
                    officeIds = new ArrayList<>();
                }
                companyId = resultSet.getLong("company_id");
                companyName = resultSet.getString("company_name");
                Client client = new Client();
                if (resultSet.getLong("client_id") != 0) {
                    // get client from joined tables
                    client = getClient(resultSet.getInt("client_age"),
                            resultSet.getString("client_name"),
                            resultSet.getString("client_surname"),
                            resultSet.getString("client_email"),
                            resultSet.getDate("client_birthday").toLocalDate(), resultSet.getBoolean("client_active"), resultSet.getLong("client_id"));

                }
                if (!clients.contains(client)) clients.add(client);

                // get offices of company and their rooms
                Room currentRoom = getRoom(resultSet.getLong("room_id"), resultSet.getInt("room_capacity"), resultSet.getBoolean("room_available"), resultSet.getString("room_number"));
                Long currentOfficeID = resultSet.getLong("office_id");
                if (!officeIds.contains(currentOfficeID)) {
                    Address address = getAddress(resultSet.getLong("address_id"),
                            resultSet.getString("address_city"),
                            resultSet.getString("address_street"), resultSet.getString("address_number"));
                    officeIds.add(currentOfficeID);
                    Office office = getOffice(currentOfficeID, resultSet.getString("office_name"), resultSet.getInt("office_capacity"), address, new ArrayList<>());
                    office.getRooms().add(currentRoom);
                    offices.add(office);
                } else {
                    for (Office office : offices) {
                        if (office.getId() == currentOfficeID) {
                            if (!office.getRooms().contains(currentRoom)) office.getRooms().add(currentRoom);
                        }
                    }
                }
                company.setClients(clients);
                company.setOffices(offices);
                company.setId(companyId);
                company.setName(companyName);
                companyMap.put(companyId, company);
            }

            //  map departments->(project->task),(employee->salary)
            String query = "SELECT  c.id AS company_id,c.name AS company_name,\n" +
                    "    d.id AS department_id,d.name AS department_name,d.number AS department_number,\n" +
                    "    e.id AS employee_id,e.name AS employee_name,e.age AS employee_age,e.surname AS employee_surname,e.email AS employee_email,e.birth_day AS employee_birth_day, e.employee_type AS employee_type,\n" +
                    "    s.id AS salary_id,s.amount AS salary_amount,s.bonus as salary_bonus, s.cut_percentage,\n" +
                    "    p.id AS project_id,p.name AS project_name, p.finished as project_finished,\n" +
                    "    t.id AS task_id, t.name AS task_name, t.start_time as task_start_time, t.end_time as task_end_time\n" +
                    "FROM companies c\n" +
                    "LEFT JOIN departments d ON d.company_id = c.id\n" +
                    "LEFT JOIN employees e ON e.department_id = d.id\n" +
                    "LEFT JOIN salary s ON s.employee_id = e.id\n" +
                    "LEFT JOIN projects p ON p.department_id = d.id\n" +
                    "JOIN tasks t ON t.project_id = p.id\n" +
                    "ORDER BY c.id, d.id, e.id, p.id, t.id;";
            try (PreparedStatement preparedStatement1 = connection.prepareStatement(query)) {

                ResultSet resultSet1 = preparedStatement1.executeQuery();

                Map<Long, Department> departmentMap = new HashMap<>();
                Map<Long, Project> projectMap = new HashMap<>();
                Map<Long, Task> taskMap = new HashMap<>();
                Map<Long, Employee> employeeMap = new HashMap<>();
                Long currentCompanyId = -1L;
                while (resultSet1.next()) {
                    if (resultSet1.getLong("company_id") != currentCompanyId) {
                        departmentMap = new HashMap<>();
                        projectMap = new HashMap<>();
                        taskMap = new HashMap<>();
                        employeeMap = new HashMap<>();
                        currentCompanyId = resultSet1.getLong("company_id");
                    }
                    Salary salary = getSalary(resultSet1.getLong("salary_id"),
                            resultSet1.getDouble("salary_amount"),
                            resultSet1.getDouble("salary_bonus"),
                            resultSet1.getInt("cut_percentage"));

                    Employee employee = getEmployee(resultSet1.getLong("employee_id"),
                            resultSet1.getInt("employee_age"),
                            resultSet1.getString("employee_name"),
                            resultSet1.getString("employee_surname"),
                            resultSet1.getString("employee_email"),
                            resultSet1.getDate("employee_birth_day").toLocalDate(),
                            resultSet1.getString("employee_type"), salary, new ArrayList<>(), new ArrayList<>());
                    Task task = getTask(resultSet1.getLong("task_id"),
                            resultSet1.getString("task_name"),
                            resultSet1.getDate("task_start_time").toLocalDate(),
                            resultSet1.getDate("task_end_time").toLocalDate());

                    Project project = getProject(resultSet1.getLong("project_id"),
                            resultSet1.getString("project_name"),
                            new ArrayList<>(),
                            resultSet1.getBoolean("project_finished"));

                    Department department = getDepartment(resultSet1.getLong("department_id"),
                            resultSet1.getString("department_name"),
                            resultSet1.getString("department_number"),
                            new ArrayList<>(), new ArrayList<>());

                    if (!departmentMap.containsKey(department.getId())) {
                        departmentMap.put(department.getId(), department);
                    } else {
                        department = departmentMap.get(department.getId());
                    }
                    if (!employeeMap.containsKey(employee.getId())) {
                        employeeMap.put(employee.getId(), employee);
                    } else {
                        employee = employeeMap.get(employee.getId());
                    }
                    if (!department.getEmployees().contains(employee)) {
                        department.getEmployees().add(employee);
                    }

                    if (!projectMap.containsKey(project.getId())) {
                        projectMap.put(project.getId(), project);
                    } else {
                        project = projectMap.get(project.getId());
                    }
                    if (!department.getProjects().contains(project)) {
                        department.getProjects().add(project);
                    }

                    if (!taskMap.containsKey(task.getId())) {
                        taskMap.put(task.getId(), task);
                    } else {
                        task = taskMap.get(task.getId());
                    }
                    if (!project.getTasks().contains(task)) {
                        project.getTasks().add(task);
                    }
                    companyMap.get(companyId).setDepartments(departmentMap.values().stream().toList());
                }

                String selectForBudget = "select c.id as company_id, b.id as budget_id, b.total_amount as budget_totalAmount ,  \n" +
                        "b.spend_amount as budget_spent, b.description \n" +
                        "as budget_description from companies c \n" +
                        "left join budgets b on c.id=b.company_id ";
                Budget budget = new Budget();

                try (PreparedStatement budgetSelect = connection.prepareStatement(selectForBudget)) {
                    ResultSet budgetQueryResult = budgetSelect.executeQuery();
                    while (budgetQueryResult.next()) {
                        budget = getBudget(budgetQueryResult.getLong("budget_id"),
                                budgetQueryResult.getDouble("budget_totalAmount"),
                                budgetQueryResult.getDouble("budget_spent"),
                                budgetQueryResult.getString("budget_description"));
                        companyMap.get(budgetQueryResult.getLong("company_id")).setBudget(budget);
                    }

                }
                return companyMap.values().stream().toList();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    // get objects
    public static Office getOffice(Long id, String name, Integer capacity, Address address, List<Room> rooms) {
        Office office = new Office();
        office.setId(id);
        office.setName(name);
        office.setCapacity(capacity);
        office.setAddress(address);
        office.setRooms(rooms);
        return office;
    }

    public static Room getRoom(Long id, Integer capacity, Boolean available, String roomNumber) {
        Room room = new Room();
        room.setId(id);
        room.setCapacity(capacity);
        room.setAvailable(available);
        room.setRoomNumber(roomNumber);
        return room;
    }

    public static Client getClient(int age, String name, String surname, String email, LocalDate birthday, Boolean active, Long id) {
        Client client = new Client();
        client.setActive(active);
        client.setAge(age);
        client.setName(name);
        client.setEmail(email);
        client.setBirthday(birthday);
        client.setSurname(surname);
        client.setId(id);
        return client;
    }

    public static Address getAddress(Long id, String city, String street, String number) {
        Address address = new Address();
        address.setId(id);
        address.setNumber(number);
        address.setCity(city);
        address.setStreet(street);
        return address;
    }

    public static EmployeeType parseEmployeeType(String typeText) {
        if (typeText == null) {
            throw new IllegalArgumentException("Employee type cannot be null");
        }
        switch (typeText.toUpperCase()) {
            case "MANAGER":
                return EmployeeType.MANAGER;
            case "CEO":
                return EmployeeType.CEO;
            case "JUNIOR":
                return EmployeeType.JUNIOR;
            case "MIDDLE":
                return EmployeeType.MIDDLE;
            case "SENIOR":
                return EmployeeType.SENIOR;
            default:
                throw new IllegalArgumentException("Unknown employee type: " + typeText);
        }
    }

    public static Employee getEmployee(Long id, int age, String name, String surname, String email, LocalDate birthDay, String employeeType, Salary salary, List<Task> tasks, List<Project> projects) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setSalary(salary);
        employee.setAge(age);
        employee.setTask(tasks);
        employee.setSurname(surname);
        employee.setName(name);
        employee.setEmail(email);
        employee.setBirthday(birthDay);
        employee.setEmployeeType(parseEmployeeType(employeeType));
        employee.setWoksOnProjects(projects);
        return employee;
    }

    public static Task getTask(Long id, String name, LocalDate startTime, LocalDate endTime) {
        Task task = new Task();
        task.setId(id);
        task.setName(name);
        task.setEndTime(endTime);
        task.setStartTime(startTime);
        return task;
    }

    public static Project getProject(Long id, String name, List<Task> tasks, boolean finished) {
        Project project = new Project();
        project.setId(id);
        project.setName(name);
        project.setTasks(tasks);
        project.setFinished(finished);
        return project;
    }

    public static Department getDepartment(Long id, String name, String departmentNumber, List<Employee> employees, List<Project> projects) {
        Department department = new Department();
        department.setId(id);
        department.setName(name);
        department.setDepartmentNumber(departmentNumber);
        department.setEmployees(employees);
        department.setProjects(projects);
        return department;
    }

    public static Salary getSalary(Long id, Double amount, Double bonus, Integer cutPercentage) {
        return new Salary(id, amount, bonus, cutPercentage);
    }

    public static Budget getBudget(Long budgetID, Double totalAmount, Double spent, String belongs) {
        return new Budget(budgetID, totalAmount, spent, belongs);
    }

}
