package controller;

import customexception.CustomException;
import model.dto.ProductDto;
import model.entity.Product;
import model.service.ProductService;
import model.service.ProductServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ProductController {
    private final ProductService productService = new ProductServiceImpl();

    public List<ProductDto> queryAllProducts() throws CustomException {
        return productService.queryAllProducts();
    }

    public int addNewProduct() throws CustomException {
        try {
            System.out.print("=".repeat(10));
            System.out.print(" Add New Product: ");
            System.out.println("=".repeat(10));
            System.out.print("[+] Insert product's name: ");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            System.out.print("[+] Insert product's code: ");
            String code = scanner.nextLine();
            System.out.println("--------------------------------------");
            System.out.println("[*] Product Imported Date");
            System.out.print("[+] Insert year (number): ");
            int importedYear = scanner.nextInt();
            System.out.print("[+] Insert month (number): ");
            int importedMonth = scanner.nextInt();
            System.out.print("[+] Insert day (number): ");
            int importedDay = scanner.nextInt();
            System.out.println("--------------------------------------");
            System.out.println("[+] Insert product's Expired Date");
            System.out.print("[+] Insert year (number): ");
            int expiredYear = scanner.nextInt();
            System.out.print("[+] Insert month (number): ");
            int expiredMonth = scanner.nextInt();
            System.out.print("[+] Insert day (number): ");
            int expiredDay = scanner.nextInt();
            System.out.print("[+] Insert product's description: ");
            String description = new Scanner(System.in).nextLine();
            return productService.addNewProduct(Product.builder()
                    .id(new Random().nextInt(10000))
                    .productName(name)
                    .productCode(code)
                    .isDeleted(false)
                    .importedDate(Date.valueOf(LocalDate.of(importedYear, importedMonth, importedDay)))
                    .expiredDate(Date.valueOf(LocalDate.of(expiredYear, expiredMonth, expiredDay)))
                    .productDescription(description)
                    .build());
        }catch (InputMismatchException e){
            throw new CustomException("Error: " + e.getMessage());
        }
    }

    public int updateProductById() throws CustomException {
        System.out.println("[+] Insert product's ID to update: ");
        return productService.updateProductById(new Scanner(System.in).nextInt());
    }

    public int deleteProductById() throws CustomException {
        System.out.println("[+] Insert product's ID to delete: ");
       return productService.deleteProductById(new Scanner(System.in).nextInt());
    }

}
