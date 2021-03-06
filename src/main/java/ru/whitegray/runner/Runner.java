package ru.whitegray.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.whitegray.products.Product;
import ru.whitegray.products.beans.Cart;
import ru.whitegray.products.beans.ProductRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {

    private final List<Product> products;
    private final Cart cart;
    private final ProductRepository productRepository;

    public Runner(List<Product> products, Cart cart, ProductRepository productRepository) {
        this.products = products;
        this.cart = cart;
        this.productRepository = productRepository;
    }



    @PostConstruct
    private void postConstruct() {
        System.out.println("postCont");
    }

    @Override
    public void run(String... args) throws Exception {
        for (int q = 1; q < 6; q++) {
            productRepository.addProduct(q, "Product_" + q, makePrice(), (int) (Math.random()*100+1));
        }
        productRepository.listProducts();
        productRepository.showProduct(4);
        productRepository.showProduct(-4);
        productRepository.showProduct(14);
        productRepository.removeProduct(-3);
        productRepository.removeProduct(3);
        productRepository.removeProduct(13);
        productRepository.listProducts();
        productRepository.addProduct(7, "Product_" + 7, makePrice(),20);
        productRepository.addProduct(8, "Product_" + 8, makePrice(),1);
        productRepository.addProduct(9, "Product_" + 9, makePrice(),21);
        productRepository.addProduct(10, "Product_" + 10, makePrice(),12);
        productRepository.addProduct(6, "Product_" + 6, makePrice(),1);
        productRepository.listProducts();
        productRepository.removeProduct(6);
        productRepository.listProducts();
        cart.listProducts();
        cart.addProduct(8);
        cart.addProduct(2);
        cart.addProduct(10);
        cart.listProducts();
        productRepository.listProducts();
        cart.removeProduct(8);
        cart.listProducts();

        productRepository.listProducts();
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.print("\nКоманды: add id, rem id, listCart, lispProd, quit\nВведите команду: ");
            String entered = scan.nextLine();
            if (entered.startsWith("add")){
                String[] arrFromLine = entered.split(" ");
                String numberFromLine =arrFromLine[1].strip();
                int prodId = Integer.parseInt (numberFromLine);
                cart.addProduct(prodId);
            }
            else if (entered.startsWith("rem")){
                String[] arrFromLine = entered.split(" ");
                String numberFromLine =arrFromLine[1].strip();
                int prodId = Integer.parseInt (numberFromLine);
                cart.removeProduct(prodId);
            }
            else if (entered.equalsIgnoreCase("listCart")){
                cart.listProducts();
            }
            else if (entered.equalsIgnoreCase("listProd")){
                productRepository.listProducts();
            }
            else if (entered.equalsIgnoreCase("quit")){
                scan.close();
                return;
            }
        }

    }



    /**
     *  создаеёт новое значение price
     */
    private static float makePrice() {
        double doub = Math.random() * 10000;
        double scale = Math.pow(10, 2);
        double price = Math.ceil(doub * scale) / scale;
        return (float) price;
    }
}
