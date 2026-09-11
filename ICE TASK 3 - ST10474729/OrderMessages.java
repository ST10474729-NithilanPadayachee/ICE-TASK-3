/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ordermessages;

/**
 *
 * @author Nithilan
 */
/**
 * OrderMessages.java
 *
 * Stores the set of error messages that can occur while an order
 * is being placed at Serendipity Gifts. Each message describes a
 * specific input problem so that OrderException can be thrown with
 * a clear, human-readable explanation.
 */
public class OrderMessages
{
    // Index constants so calling code can refer to messages by name
    // instead of "magic numbers".
    public static final int ITEM_NOT_NUMERIC   = 0;
       public static final int ITEM_TOO_LOW       = 1;
    public static final int ITEM_TOO_HIGH      = 2;
    public static final int ITEM_NOT_FOUND     = 3;
     public static final int QUANTITY_NOT_NUMERIC = 4;
    public static final int QUANTITY_TOO_LOW   = 5;
      public static final int QUANTITY_TOO_HIGH  = 6;

          private String[] messages;

    public OrderMessages()
    {
        messages = new String[7];

         messages[ITEM_NOT_NUMERIC]     = "Error: Item number must be numeric.";
        messages[ITEM_TOO_LOW]         = "Error: Item number cannot be less than 0.";
        messages[ITEM_TOO_HIGH]        = "Error: Item number cannot be   greater than 9999.";
          messages[ITEM_NOT_FOUND]       = "Error: Item number does not correspond to a valid item.";
        messages[QUANTITY_NOT_NUMERIC] = "Error: Quantity must be numeric.";
        messages[QUANTITY_TOO_LOW]     = "Error: Quantity cannot be less than 1.";
        messages[QUANTITY_TOO_HIGH]    = "Error: Quantity cannot be   greater than 12.";
    } 

    /**
     * Returns the error message that corresponds to the given index.
     * @param index one of the OrderMessages constants above
     * @return the matching error message text
     */
    public String getMessage(int index)
    {
        return messages[index];
    }

    /**
     * @return the full array of error messages.
     */
    public String[] getMessages()
    {
        return messages;
                          }
}