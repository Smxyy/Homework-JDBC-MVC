package view;

import controller.CustomerController;
import controller.OrderController;
import controller.ProductController;
import customexception.CustomException;
import model.dto.CustomerDto;
import model.dto.OrderDto;
import model.dto.ProductDto;
import model.entity.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class View {
    private static final CustomerController customerController = new CustomerController();
    private static final OrderController orderController = new OrderController();
    private static final ProductController productController = new ProductController();
    public static void menu() throws CustomException {
        while (true) {
            System.out.print("=".repeat(10));
            System.out.print(" Food Panda System ");
            System.out.println("=".repeat(10));
            System.out.println("1. Display All Data");
            System.out.println("2. Add New Data");
            System.out.println("3. Update Data");
            System.out.println("4. Delete Data");
            System.out.println("0, 99. Exit");
            System.out.println( "=".repeat(40));
            System.out.print("[+] Insert option: ");
            int option = new Scanner(System.in).nextInt();
            switch(option){
                case 1->queryAllData();
                case 2->addNewData();
                case 3->updateData();
                case 4->deleteData();
                case 0, 99->System.exit(0);
                default -> System.out.println("[!] Invalid option ");
            }
        }
    }

    public static void queryAllData() throws CustomException {
        while(true){
            System.out.print("=".repeat(10));
            System.out.print(" List All Data System ");
            System.out.println("=".repeat(10));
            System.out.println("1. Display All Customers");
            System.out.println("2. Display All Orders");
            System.out.println("3. Display All Products");
            System.out.println("0, 99. Back");
            System.out.println("=".repeat(40));
            System.out.print("[+] Insert option: ");
            int option = new Scanner(System.in).nextInt();
            switch(option){
                case 1->{
                    List<CustomerDto> customerDtoList = customerController.queryAllCustomers();
                    if(customerDtoList.isEmpty()){
                        System.out.println("[!] Customer table is empty");
                    }else{
                        Table table = new Table(3, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
                        table.setColumnWidth(0, 25, 25);
                        table.setColumnWidth(1, 25, 25);
                        table.setColumnWidth(2, 25, 25);
                        //Head table
                        table.addCell("Customer's Name".toUpperCase());
                        table.addCell("Customer's Email".toUpperCase());
                        table.addCell("Customer's Bio".toUpperCase());
                        //Data
                        for(CustomerDto customerDto: customerDtoList){
                            //data
                            table.addCell(customerDto.name());
                            table.addCell(customerDto.email());
                            table.addCell(customerDto.bio());
                        }
                        System.out.println(table.render());
                    }
                }
                case 2-> {
                    List<OrderDto> orderDtoList = orderController.queryAllOrders();
                    if(orderDtoList.isEmpty()){
                        System.out.println("[!] Order table is empty");
                    }else {
                        Table table = new Table(6, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
                        table.setColumnWidth(0, 25, 25);
                        table.setColumnWidth(1, 25, 25);
                        table.setColumnWidth(2, 25, 25);
                        table.setColumnWidth(3, 25, 25);
                        table.setColumnWidth(4, 25, 25);
                        table.setColumnWidth(5, 25, 25);

                        //Head table
                        table.addCell("Order's ID".toUpperCase());
                        table.addCell("Order's Name".toUpperCase());
                        table.addCell("Order's Description".toUpperCase());
                        table.addCell("Customer's Name".toUpperCase());
                        table.addCell("Customer's Email".toUpperCase());
                        table.addCell("Order's Date".toUpperCase());

                        //Data
                        for (OrderDto orderDto : orderDtoList) {
                            //data
                            table.addCell(orderDto.id().toString());
                            table.addCell(orderDto.orderName());
                            table.addCell(orderDto.orderDescription());
                            table.addCell(orderDto.customerDto().name());
                            table.addCell(orderDto.customerDto().email());
                            table.addCell(orderDto.orderedAt().toString());
                        }
                        System.out.println(table.render());
                    }
                }
                case 3-> {
                    List<ProductDto> productDtoList = productController.queryAllProducts();
                    if(productDtoList.isEmpty()){
                        System.out.println("[!] Product table is empty");
                    }else{
                        Table table = new Table(6, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
                        table.setColumnWidth(0, 25, 25);
                        table.setColumnWidth(1, 25, 25);
                        table.setColumnWidth(2, 25, 25);
                        table.setColumnWidth(3, 25, 25);

                        //header data
                        table.addCell("Product's Name".toUpperCase());
                        table.addCell("Product's Imported Date".toUpperCase());
                        table.addCell("Product's Exported Date".toUpperCase());
                        table.addCell("Product's Description".toUpperCase());

                        //data
                        for(ProductDto productDto: productDtoList){
                            table.addCell(productDto.productName());
                            table.addCell(productDto.importedDate().toString());
                            table.addCell(productDto.expiredDate().toString());
                            table.addCell(productDto.productDescription());
                        }
                        System.out.println(table.render());
                    }
                }
                case 0, 99 -> {
                    return;
                }
                default -> System.out.println("[!] Invalid option");
            }
        }
    }

    public static void addNewData() throws CustomException {
        while (true) {
            System.out.print("=".repeat(10));
            System.out.print(" Add New Data System ");
            System.out.println("=".repeat(10));
            System.out.println("1. Add New Customer");
            System.out.println("2. Add New Order");
            System.out.println("3. Add New Product");
            System.out.println("0, 99. Back");
            System.out.println( "=".repeat(40));
            System.out.print("[+] Insert option: ");
            int option = new Scanner(System.in).nextInt();
            switch(option){
                case 1-> {
                    int result = customerController.addNewCustomer();
                    if(result > 0){
                        System.out.println("[+] Added new customer successfully");
                    }else{
                        System.out.println("[!] Failed to add new customer");
                    }
                }

                case 2-> {
                    int result = orderController.addNewOrder();
                    if(result > 0){
                        System.out.println("[+] Added new order successfully");
                    }else{
                        System.out.println("[!] Failed to add new order");
                    }
                }
                case 3-> {
                    int result = productController.addNewProduct();
                    if(result > 0){
                        System.out.println("[+] Added new product successfully");
                    }else{
                        System.out.println("[!] Failed to add new product");
                    }
                }
                case 0, 99-> {
                    return;
                }
                default -> System.out.println("[!] Invalid option");
            }
        }
    }

    public static void updateData() throws CustomException {
        while (true) {
            System.out.print("=".repeat(10));
            System.out.print(" Update Data System ");
            System.out.println("=".repeat(10));
            System.out.println("1. Update Customer");
            System.out.println("2. Update Order");
            System.out.println("3. Update Product");
            System.out.println("0, 99. Back");
            System.out.println( "=".repeat(40));
            System.out.print("[+] Insert option: ");
            int option = new Scanner(System.in).nextInt();
            switch(option){
                case 1->{
                    int result = customerController.updateCustomerByID();
                    if(result > 0){
                        System.out.println("[*] Updated customer successfully");
                    }else{
                        System.out.println("[!] Failed to update customer");
                    }
                }
                case 2->{
                    int result = orderController.updateOrder();
                    if(result > 0){
                        System.out.println("[*] Updated order successfully");
                    }else{
                        System.out.println("[!] Failed to update order");
                    }
                }
                case 3-> {
                    int result = productController.updateProductById();
                    if(result > 0){
                        System.out.println("[*] Updated product successfully");
                    }else{
                        System.out.println("[!] Failed to update product");
                    }
                }
                case 0, 99-> {
                    return;
                }
                default -> System.out.println("[!] Invalid option");
            }
        }
    }

    public static void deleteData() throws CustomException {
        while (true) {
            System.out.print("=".repeat(10));
            System.out.print(" Delete Data System ");
            System.out.println("=".repeat(10));
            System.out.println("1. Delete Customer");
            System.out.println("2. Delete Order");
            System.out.println("3. Delete Product");
            System.out.println("0, 99. Back");
            System.out.println( "=".repeat(40));
            System.out.print("[+] Insert option: ");
            int option = new Scanner(System.in).nextInt();
            switch(option){
                case 1->{
                    int result = customerController.deleteCustomerById();
                    if(result > 0){
                        System.out.println("[-] Deleted customer successfully");
                    }else{
                        System.out.println("[!] Failed to delete customer");
                    }
                }
                case 2-> {
                    int result = orderController.deleteOrder();
                    if(result > 0){
                        System.out.println("[-] Deleted order successfully");
                    }else{
                        System.out.println("[!] Failed to delete order");
                    }
                }
                case 3-> {
                    int result = productController.deleteProductById();
                    if(result > 0){
                        System.out.println("[-] Deleted product successfully");
                    }else{
                        System.out.println("[!] Failed to delete product");
                    }
                }
                case 0, 99-> {
                    return;
                }
                default -> System.out.println("[!] Invalid option");
            }
        }
    }

}
