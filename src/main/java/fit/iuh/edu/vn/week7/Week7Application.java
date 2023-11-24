package fit.iuh.edu.vn.week7;

import fit.iuh.edu.vn.week7.enums.EmployeeStatus;
import fit.iuh.edu.vn.week7.enums.ProductStatus;
import fit.iuh.edu.vn.week7.models.*;
import fit.iuh.edu.vn.week7.repositories.CustomerRepository;
import fit.iuh.edu.vn.week7.repositories.EmployeeRepository;
import fit.iuh.edu.vn.week7.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import net.datafaker.Faker;
import net.datafaker.providers.base.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Week7Application {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(Week7Application.class, args);
    }

//    @Bean
//    CommandLineRunner createDataProduct() {
//        return args -> {
//            Faker faker = new Faker();
//            Device devices = faker.device();
//            for (int i = 0; i < 200; i++) {
//                Product product = new Product(
//                        devices.modelName(),
//                        faker.lorem().paragraph(2), "piece", devices.manufacturer(),
//                        ProductStatus.ACTIVE
//                );
//                productRepository.save(product);
//            }
//        };
//    }
//
//    @Bean
//    CommandLineRunner createDataEmployee() {
//        return args -> {
//            Faker faker = new Faker();
//            Device devices = faker.device();
//            for (int i = 0; i < 200; i++) {
//                Employee employee = new Employee(
//                        faker.name().fullName(),
//                        faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
//                        faker.internet().emailAddress(),
//                        faker.phoneNumber().cellPhone(),
//                        faker.address().fullAddress(),
//                        EmployeeStatus.ACTIVE
//                );
//                employeeRepository.save(employee);
//            }
//        };
//    }
//
//    @Bean
//    CommandLineRunner createDataCustomer() {
//        return args -> {
//            Faker faker = new Faker();
//            Device devices = faker.device();
//            for (int i = 0; i < 200; i++) {
//                Customer customer = new Customer(
//                        faker.name().fullName(),
//                        faker.internet().emailAddress(),
//                        faker.phoneNumber().cellPhone(),
//                        faker.address().fullAddress()
//                );
//                customerRepository.save(customer);
//            }
//        };
//    }

    @Bean
    @Transactional
    CommandLineRunner createDataProduct1() {
        return args -> {
            Faker faker = new Faker();
            Random rnd = new Random();
            Device devices = faker.device();
            for (int i = 0; i < 200; i++) {

                Product product = new Product(
                        devices.modelName(),
                        faker.lorem().paragraph(2), "piece", devices.manufacturer(),
                        ProductStatus.ACTIVE
                );
                ProductImage img = new ProductImage("img/sp.png", "ex img");
                img.setProduct(product);
                ProductPrice price = new ProductPrice(LocalDateTime.now(), rnd.nextInt(1500), "Note #" + i);
                price.setProduct(product);
                product.setProductImageList(List.of(img));
                product.setProductPrices(List.of(price));

                productRepository.save(product);
            }
        };
    }
}


