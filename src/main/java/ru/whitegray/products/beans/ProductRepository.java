package ru.whitegray.products.beans;

import org.springframework.stereotype.Component;
import ru.whitegray.products.Product;
import java.util.ArrayList;
import java.util.List;



@Component
public class ProductRepository {
    private static final List<Product> products = new ArrayList<>();


    public static List<Product> getProducts() {
        return products;
    }

    public static Product getProduct(int index) {
        return products.get(index);
    }

    /**
     * выводит Product list
     */
    public void listProducts() {
        System.out.println("В магазине:\n   id    name        price       quantity");
        for (Product q : products) {
            System.out.println("\t" + q.getId() + "   " + q.getName() + "   " + q.getPrice() + "  \t\t " + q.getQuantity());
        }
    }

    /**
     * выводит Product по id
     */
    public void showProduct(int id) {
        int index = findProduct(id);
        if (index == -1) {
            return;
        }
        Product product = products.get(index);
        System.out.println("\n\t id =" + product.getId() + "   name = " + product.getName() + "   price = " + product.getPrice() + "   quantity = " + product.getQuantity() + "\n");
    }


    /**
     * добавляет новый Product
     */
    public static void addProduct(int id, String name, float price, int quantity) {

        Product product = new Product(id, name, price, quantity);
        ProductRepository.getProducts().add(product);
        System.out.println("\nДобавлен Product: "+id + "   " + name +"    price = "+price+"    quantity = "+quantity+"\n");
    }

    /**
     * удаление Product по его id
     */
    public static void removeProduct(int id) {
        int index = findProduct(id);
        if (index ==-1){
            return;
        }
        ProductRepository.getProducts().remove(index);
        System.out.println("\nProduct c id " + id +" удалён из Product!\n");
    }


    /**
     *  находим нужный Product в List
     */
    protected static int findProduct(int id){
        for (Product product : ProductRepository.getProducts()) {
            if (product.getId() == id){
                return ProductRepository.getProducts().indexOf(product);
            }
        }
        System.out.println("\n\tПродукта с id = " + id + " у нас нет !\n");
        return -1;
    }
}
