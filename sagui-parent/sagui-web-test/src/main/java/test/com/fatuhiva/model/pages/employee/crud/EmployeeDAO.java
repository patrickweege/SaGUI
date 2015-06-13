package test.com.fatuhiva.model.pages.employee.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import test.com.fatuhiva.model.pages.crud.employee.Employee;

public class EmployeeDAO {

    private Connection getConnection() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            Connection c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/employeeDB", "SA", "");
            return c;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Employee getByID(long id) {
        Connection connection = getConnection();
        try {
            return getByID(id, connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    private Employee getByID(long id, Connection connection) {
        try {
            ResultSetHandler<Employee> h = new BeanHandler<Employee>(Employee.class);

            QueryRunner run = new QueryRunner();
            Employee found = run.query(connection, "SELECT ID, NOME, SALARY FROM EMPLOYEE WHERE ID = ?", h, id);

            return found;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Employee getNew() {
        Employee newEmp = new Employee();
        newEmp.setId(getNextID());
        return newEmp;

    }

    private long getNextID() {
        Connection connection = getConnection();
        try {
            ResultSetHandler<Integer> h = new ScalarHandler<Integer>("MAX_ID");
            QueryRunner run = new QueryRunner();
            Integer found = run.query(connection, "SELECT MAX(ID) AS MAX_ID FROM EMPLOYEE", h);
            found = found == null ? 0 : found;
            return found + 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public Employee save(Employee emp) {
        if (emp.getId() <= 0) {
            emp.setId(getNextID());
        }
        Connection connection = getConnection();
        try {
            this.save(emp, connection);
            return this.getByID(emp.getId(), connection);
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public List<Employee> getAll() {
        Connection connection = getConnection();
        try {
            ResultSetHandler<List<Employee>> h = new BeanListHandler<Employee>(Employee.class);

            QueryRunner run = new QueryRunner();
            List<Employee> all = run.query(connection, "SELECT ID, NOME, SALARY FROM EMPLOYEE", h);

            return all;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public void save(Employee emp, Connection connection) {
        try {
            Employee found = getByID(emp.getId(), connection);
            if (found != null) {
                update(emp, connection);
            } else {
                insert(emp, connection);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(long id) {
        Connection connection = getConnection();
        try {
            this.delete(id, connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    private void insert(Employee employee, Connection connection) {
        try {
            QueryRunner run = new QueryRunner();
            run.update(connection, "INSERT INTO EMPLOYEE (ID, NOME, SALARY) VALUES (?, ?, ?)", employee.getId(), employee.getNome(), employee.getSalary());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void update(Employee employee, Connection connection) {
        try {
            QueryRunner run = new QueryRunner();
            run.update(connection, "UPDATE EMPLOYEE SET ID = ?, NOME = ?, SALARY = ? WHERE ID = ?", employee.getId(), employee.getNome(), employee.getSalary(), employee.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(long id, Connection connection) {
        try {
            ResultSetHandler<List<Employee>> h = new BeanListHandler<Employee>(Employee.class);

            QueryRunner run = new QueryRunner();
            run.update(connection, "DELETE FROM EMPLOYEE WHERE ID = ?", id, h);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
