package Manager;

import java.util.Collection;
import java.util.HashMap;

public class Shop {
    private final Store store;
    private final HashMap<String, Orders> customerOrder;
    public Shop(){
        this.store = new Store();
        this.customerOrder = new HashMap<>();
    }

    public void createFruit(){
        do{
            boolean check = this.store.addFruit();
            if(check)
                System.out.println("Add successfully\n");
            else
                System.out.println("Add fail");
        }while (InputValidation.getYN(""));
    }

    public void viewOrder(){
        Collection<String> CustomerName = this.customerOrder.keySet();
        for (String name: CustomerName) {
            System.out.println("Customer: " + name);
            this.customerOrder.get(name).viewOrders();
        }
    }

    public void shopping(){
        String customerName = InputValidation.getString("Input your name: ", "Name can not empty");
        customerName = customerName.toLowerCase();
        Orders orders;
        if(this.customerOrder.containsKey(customerName))
            orders = this.customerOrder.get(customerName);
        else
            orders = new Orders();
        do{
            orders.addOrder(this.store.buyFruit());
        }while(InputValidation.getYN(""));
        this.customerOrder.put(customerName, orders);
        System.out.println("Add your order(s) successfully");
    }
    public static void main(String[] args) {
        Shop obj = new Shop();
        do{
            System.out.println("1.Create Fruit");
            System.out.println("2.View orders");
            System.out.println("3.Shopping (for buyer)");
            System.out.println("4.Exit");
            switch (InputValidation.getAnInteger("Input your option: ", "Option invalid!!", 1, 4)){
                case 1: obj.createFruit(); break;
                case 2: obj.viewOrder(); break;
                case 3: obj.shopping(); break;
                case 4:
                    System.out.println("bye!!!");
                    return;
                default:
                    System.out.println("how did you get here?");
            }
        }while(true);
    }
}
