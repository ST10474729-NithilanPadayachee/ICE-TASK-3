/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ordermessages;

/**
 *
 * @author Nithilan
 */
import java.util.Scanner;

/**
 * PlaceAnOrder.java
 *
 * Interactive console application for Serendipity Gifts. Prompts the
 * customer for an item number and a quantity, validates both values,
 * and either reports an error (via OrderException) or displays the
 * total amount due.
 */
public class Placeanorder
{
    // Parallel arrays representing the current inventory.
    private static final int[] ITEM_NUMBERS = { 111, 222, 333, 444 };
    private static final double[] ITEM_PRICES = { 0.89, 1.47, 2.43, 5.99 };

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        OrderMessages orderMessages = new OrderMessages();
        String again = "y";

        System.out.println("Welcome to Serendipity Gifts Order Entry");
        System.out.println("-----------------------------------------");

        while (again.equalsIgnoreCase("y"))
        {
            try
            {
                int itemNumber = readItemNumber(input, orderMessages);
                int quantity = readQuantity(input, orderMessages);

                double price = findPrice(itemNumber, orderMessages);
                double total = price * quantity;

                System.out.printf("%nItem #%d x %d @ $%.2f each%n", itemNumber, quantity, price);
                System.out.printf("Total amount due: $%.2f%n", total);
            }
            catch (Orderexception orderException)
            {
                System.out.println();
                System.out.println(orderException.getMessage());
            }

            System.out.print("\nPlace another order? (y/n): ");
            again = input.nextLine().trim();
        }

        System.out.println("\nThank you for shopping at Serendipity Gifts!");
        input.close();
    }

   
    private static int readItemNumber(Scanner input, OrderMessages orderMessages) throws Orderexception
    {
        System.out.print("Enter item number: ");
        String itemInput = input.nextLine().trim();

        int itemNumber;
        try
        {
            itemNumber = Integer.parseInt(itemInput);
        }
        catch (NumberFormatException e)
        {
            throw new Orderexception(orderMessages.getMessage(OrderMessages.ITEM_NOT_NUMERIC));
        }

        if (itemNumber < 0)
        {
            throw new Orderexception(orderMessages.getMessage(OrderMessages.ITEM_TOO_LOW));
        }
        if (itemNumber > 9999)
        {
            throw new Orderexception(orderMessages.getMessage(OrderMessages.ITEM_TOO_HIGH));
        }

        // Confirm the item number matches an item in the inventory.
        boolean found = false;
        for (int number : ITEM_NUMBERS)
        {
            if (number == itemNumber)
            {
                found = true;
                break;
            }
        }
        if (!found)
        {
            throw new Orderexception(orderMessages.getMessage(OrderMessages.ITEM_NOT_FOUND));
        }

        return itemNumber;
    }

    /**
     * Prompts for and validates the quantity.
     * Throws OrderException if the value is non-numeric or out of range.
     */
    private static int readQuantity(Scanner input, OrderMessages orderMessages) throws Orderexception
    {
        System.out.print("Enter quantity: ");
        String quantityInput = input.nextLine().trim();

        int quantity;
        try
        {
            quantity = Integer.parseInt(quantityInput);
        }
        catch (NumberFormatException e)
        {
            throw new Orderexception(orderMessages.getMessage(OrderMessages.QUANTITY_NOT_NUMERIC));
        }

        if (quantity < 1)
        {
            throw new Orderexception(orderMessages.getMessage(OrderMessages.QUANTITY_TOO_LOW));
        }
        if (quantity > 12)
        {
            throw new Orderexception(orderMessages.getMessage(OrderMessages.QUANTITY_TOO_HIGH));
        }

        return quantity;
    }

    /**
     * Looks up the price for a valid item number.
     * (Item number is assumed already validated by readItemNumber.)
     */
    private static double findPrice(int itemNumber, OrderMessages orderMessages) throws Orderexception
    {
        for (int i = 0; i < ITEM_NUMBERS.length; i++)
        {
            if (ITEM_NUMBERS[i] == itemNumber)
            {
                return ITEM_PRICES[i];
            }
        }
        // Should not happen since readItemNumber already validated it,
        // but included as a safeguard.
        throw new Orderexception(orderMessages.getMessage(OrderMessages.ITEM_NOT_FOUND));
    }
}
