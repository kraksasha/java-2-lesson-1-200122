package com.geekbrains.person.customer;

import com.geekbrains.market.Market;
import com.geekbrains.person.Person;
import com.geekbrains.person.seller.Seller;
import com.geekbrains.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private List<Product> expectedPurchaseList;
    private List<Product> purchaseList;
    private String nameSeller;
    private String lastNameSeller;

    public String getNameSeller() {
        return nameSeller;
    }

    public void setNameSeller(String nameSeller) {
        this.nameSeller = nameSeller;
    }

    public String getLastNameSeller() {
        return lastNameSeller;
    }

    public void setLastNameSeller(String lastNameSeller) {
        this.lastNameSeller = lastNameSeller;
    }

    public Customer(List<Product> expectedPurchaseList, int cash, String nameSeller, String lastNameSeller) {
        this.purchaseList = new ArrayList<>();
        this.expectedPurchaseList = expectedPurchaseList;
        this.setCash(cash);
        this.nameSeller = nameSeller;
        this.lastNameSeller = lastNameSeller;
    }

    public void addPurchase(Product product) {
        if (purchaseList == null) {
            purchaseList = new ArrayList<>();
        }

        purchaseList.add(product);
    }

    public void findProductOnMarket(Market market) {
        for (Product product : getExpectedPurchaseList()) {
            for (Seller seller : market.getSellers()) {
                if (seller.getName().equals(nameSeller) && seller.getLastName().equals(lastNameSeller)){
                    boolean isBought = seller.sellProducts(this, product);
                    if (isBought) {
                        break;
                    } else {
                        for (Seller seller2 : market.getSellers()){
                            boolean isBought2 = seller2.sellProducts(this, product);
                            if (isBought2) {
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    public void info() {
        StringBuilder result = new StringBuilder("Я купил ");
        if (purchaseList.size() == 0) {
            result.append("ничего");
        } else {
            for(Product product: purchaseList) {
                result.append(product.getName());
                result.append(" в количестве ");
                result.append(product.getQuantity());
                result.append(" ");
            }
        }

        result.append(". У меня осталось: ");
        result.append(getCash());
        result.append(" рублей");

        System.out.println(result);
    }

    public List<Product> getExpectedPurchaseList() {
        return expectedPurchaseList;
    }

    public List<Product> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Product> purchaseList) {
        this.purchaseList = purchaseList;
    }

}
