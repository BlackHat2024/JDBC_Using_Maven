package util;

public final class Queries {

    private Queries() {}

    public static final String FIND_ALL_EMPLOYEES = "SELECT * FROM employees;";

    public static final String FIND_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE employeeNumber = ?;";

    public static final String INSERT_NEW_EMPLOYEE = "INSERT INTO employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle)" +
            " VALUES (?,?, ?, ?, ?, ?,?,?);";
    public static final String UPDATE_EMPLOYEE = "UPDATE employees SET lastName = ?, firstName = ?, extension = ?, email = ?, officeCode = ?, reportsTo = ?, jobTitle = ? WHERE employeeNumber = ?;";

    public static final String FIND_ALL_CUSTOMERS = "SELECT * FROM customers;";
    public static final String FIND_CUSTOMER_BY_ID = "SELECT * FROM customers WHERE customerNumber = ?;";

    public static final String INSERT_NEW_CUSTOMER = "INSERT INTO customers(customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    public static final String UPDATE_CUSTOMER = "UPDATE customers SET customerName = ?, contactLastName = ?, contactFirstName = ?, phone = ?, addressLine1 = ?, addressLine2 = ?, city = ?, state = ?, postalCode = ?, country = ?, salesRepEmployeeNumber = ?, creditLimit = ? WHERE customerNumber = ?;";

    public static final String FIND_ALL_OFFICES = "SELECT * FROM offices;";

    public static final String FIND_OFFICE_BY_ID = "SELECT * FROM offices WHERE officeCode = ?;";

    public static final String INSERT_NEW_OFFICE = "INSERT INTO offices(officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    public static final String UPDATE_OFFICE = "UPDATE offices SET city = ?, phone = ?, addressLine1 = ?, addressLine2 = ?, state = ?, country = ?, postalCode = ?, territory = ? WHERE officeCode = ?;";

    public static final String FIND_ALL_ORDERS = "SELECT * FROM orders;";

    public static final String FIND_ORDER_BY_ID = "SELECT * FROM orders WHERE orderNumber = ?;";

    public static final String INSERT_NEW_ORDER = "INSERT INTO orders(orderNumber, orderDate, requiredDate, shippedDate, status, comments, customerNumber) VALUES ( ?, ?, ?, ?, ?, ?, ?);";

    public static final String UPDATE_ORDER = "UPDATE orders SET orderDate = ?, requiredDate = ?, shippedDate = ?, status = ?, comments = ?, customerNumber = ? WHERE orderNumber = ?;";

}
