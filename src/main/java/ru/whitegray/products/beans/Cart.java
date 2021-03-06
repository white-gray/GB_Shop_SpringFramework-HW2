package ru.whitegray.products.beans;


import org.springframework.stereotype.Component;
import ru.whitegray.products.Product;

import java.util.ArrayList;
import java.util.List;



@Component
public class Cart {

    private static final List<Product> productsInCart = new ArrayList<>();

    public static List<Product> getProductsInCart() {
        return productsInCart;
    }



    /**
     * выводит Product in Cart
     */
    public void listProducts() {
        System.out.println("\nУ Вас в корзине:\n   id    name        price       quantity");
        for (Product q : productsInCart) {
            System.out.println("\t" + q.getId() + "   " + q.getName() + "   " + q.getPrice() + "  \t\t " + q.getQuantity());
        }
    }

    /**
     * добавляет новый Product
     */
    public static void addProduct(int id) {
        int index = ProductRepository.findProduct(id);
        Product prod = ProductRepository.getProduct(index);
        ProductRepository.removeProduct(id);
        productsInCart.add(prod);
        System.out.println("\nВ корзину добавлен Product: "+id + "   " + prod.getName() +"    price = "+prod.getPrice()+"    quantity = "+prod.getQuantity()+"\n");
    }

    /**
     * удаление Product по его id
     */
    public static void removeProduct(int id) {
        int index = findProduct(id);
        if (index ==-1){
            return;
        }
        ProductRepository.addProduct(productsInCart.get(index).getId(), productsInCart.get(index).getName(), productsInCart.get(index).getPrice(), productsInCart.get(index).getQuantity());
        productsInCart.remove(index);
        System.out.println("\nProduct c id " + id +" удалён из корзины!\n");
    }


    /**
     *  находим нужный Product в Cart
     */
    protected static int findProduct(int id){
        for (Product product : getProductsInCart()) {
            if (product.getId() == id){
                return getProductsInCart().indexOf(product);
            }
        }
        System.out.println("\n\tПродукта с id = " + id + " нет в корзине!\n");
        return -1;
    }
}
