package main;

import model.entity.Customer;
import model.entity.Employee;
import model.entity.Office;
import model.entity.Order;
import repository.impl.*;

import java.sql.Date;
import java.util.Scanner;

import static util.JdbcConnection.connect;

public class Main {

    public static void main(String[] args) {

        EmployeeRepository employeeRepository = new EmployeeRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        OrderRepository orderRepository = new OrderRepository();
        OfficeRepository officeRepository = new OfficeRepository();

        Employee employee= new Employee();
        Customer customer= new Customer();
        Order order= new Order();
        Office office= new Office();

        System.out.println(connect());

        Scanner input = new Scanner(System.in);

        System.out.println("1 Employee");
        System.out.println("2 Customer");
        System.out.println("3 Orders");
        System.out.println("4 Offices");
        int chose=input.nextInt();
        while(chose!=0){
            if(chose<0||chose>4){
                System.out.println("Wrong input! Chose 1-4 :");
                chose=input.nextInt();
            }
            else{
                if(chose==1){
                    while (chose!=0){
                        System.out.println("1 Check by id ");
                        System.out.println("2 Add new");
                        System.out.println("3 Update");
                        chose=input.nextInt();
                        if(chose<0||chose>3){
                            System.out.println("Wrong input! Chose 1-3 :");
                            chose=input.nextInt();
                        }
                        else{

                            if(chose==1){
                                System.out.println(employeeRepository.exists(1002));
                            }
                            else if(chose==2){
                                employee.setId(1000);
                                employee.setLastName("f");
                                employee.setFirstName("filan");
                                employee.setExtension("a");
                                employee.setEmail("a@gmail.com");
                                employee.setOfficeCode("2");
                                employee.setReportsTo(1143);
                                employee.setJobTitle("Sales Reps");
                                System.out.println(employeeRepository.save(employee));

                            }
                            else if(chose==3){
                                employee.setId(1000);
                                employee.setLastName("f");
                                employee.setFirstName("filan");
                                employee.setExtension("a");
                                employee.setEmail("a@gmail.com");
                                employee.setOfficeCode("2");
                                employee.setReportsTo(1143);
                                employee.setJobTitle("Sales Reps");
                                System.out.println(employeeRepository.update(employee));

                            }

                        }

                    }

                }
                else if(chose==2){

                    while (chose!=0){
                        System.out.println("1 Check by id ");
                        System.out.println("2 Add new");
                        System.out.println("3 Update");
                        chose=input.nextInt();
                        if(chose<0||chose>3){
                            System.out.println("Wrong input! Chose 1-3 :");
                            chose=input.nextInt();
                        }

                        else{

                            if(chose==1){
                                System.out.println(customerRepository.exists(1002));

                            }
                            else if(chose==2){
                                customer.setCustomerName("");
                                customer.setContactLastName("");
                                customer.setContactFirstName("");
                                customer.setPhone("");
                                customer.setAddress1("");
                                customer.setAddress2("");
                                customer.setCity("");
                                customer.setState("");
                                customer.setPostalCode("");
                                customer.setCountry("");
                                customer.setSalesRepEmployeeNumber(1000);
                                customer.setCreditLimit(25000.00);
                                customer.setCustomerNumber(10000);
                                System.out.println(customerRepository.save(customer));

                            }
                            else if(chose==3){
                                customer.setCustomerName("");
                                customer.setContactLastName("");
                                customer.setContactFirstName("");
                                customer.setPhone("");
                                customer.setAddress1("");
                                customer.setAddress2("");
                                customer.setCity("");
                                customer.setState("");
                                customer.setPostalCode("");
                                customer.setCountry("");
                                customer.setSalesRepEmployeeNumber(1000);
                                customer.setCreditLimit(25000.00);
                                customer.setCustomerNumber(10000);
                                System.out.println(customerRepository.update(customer));

                            }

                        }


                    }

                }

                else if(chose==3){

                    while (chose!=0){
                        System.out.println("1 Check by id ");
                        System.out.println("2 Add new");
                        System.out.println("3 Update");
                        chose=input.nextInt();
                        if(chose<0||chose>3){
                            System.out.println("Wrong input! Chose 1-3 :");
                            chose=input.nextInt();
                        }

                        else{

                            if(chose==1){
                                System.out.println(orderRepository.exists(1002));

                            }
                            else if(chose==2){
                                order.setOrderNumber(122);
                                order.setOrderDate(new java.sql.Date(2022, 06, 15));
                                order.setRequiredDate(new java.sql.Date(2022, 06, 15));
                                order.setShippedDate(new java.sql.Date(2022, 06, 15));
                                order.setStatus("");
                                order.setComments("");
                                order.setCustomerNumber(100);
                                System.out.println(orderRepository.save(order));

                            }
                            else if(chose==3){
                                order.setOrderNumber(122);
                                order.setOrderDate(new java.sql.Date(2022, 06, 15));
                                order.setRequiredDate(new java.sql.Date(2022, 06, 15));
                                order.setShippedDate(new java.sql.Date(2022, 06, 15));
                                order.setStatus("");
                                order.setComments("");
                                order.setCustomerNumber(100);
                                System.out.println(orderRepository.update(order));

                            }

                        }


                    }

                }

                else{

                    while (chose!=0){
                        System.out.println("1 Check by id ");
                        System.out.println("2 Add new");
                        System.out.println("3 Update");
                        chose=input.nextInt();
                        if(chose<0&&chose>3){
                            System.out.println("Wrong input! Chose 1-3 :");
                            chose=input.nextInt();
                        }
                        else{

                            if(chose==1){
                                System.out.println(officeRepository.exists(1002));

                            }
                            else if(chose==2){
                                office.setOfficeCode(100);
                                office.setCity("");
                                office.setPhone("");
                                office.setAddress1("");
                                office.setAddress2("");
                                office.setState("");
                                office.setCountry("");
                                office.setPostalCode("");
                                office.setTerritory("");
                                System.out.println(officeRepository.save(office));

                            }
                            else if(chose==3){
                                office.setOfficeCode(100);
                                office.setCity("");
                                office.setPhone("");
                                office.setAddress1("");
                                office.setAddress2("");
                                office.setState("");
                                office.setCountry("");
                                office.setPostalCode("");
                                office.setTerritory("");
                                System.out.println(officeRepository.update(office));

                            }

                        }


                    }

                }

            }

        }

    }

}
