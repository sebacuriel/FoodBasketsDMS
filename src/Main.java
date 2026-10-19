/**
 * Sebastian Yael Curiel Franco
 * Software Development I - CEN 3024C - 13038
 * October 18, 2026
 * Main.java
 * This application asks the user to select an option
 * to either create, import, view, edit, or delete baskets.
 * It is used to more easily prepare meal plans.
 */

import java.io.*;
import java.util.*;

public class Main
{
    /**
     * method: main
     * parameters: none
     * return: none
     * purpose: creates a menu for the user to
     * create, import, view, edit, or delete
     * baskets, as well as exiting the program.
     */
    public static void main()
    {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();

        int input;

        while (true)
        {
            System.out.println("\n- Food Baskets DMS -\n");

            System.out.println("1. Create Basket");
            System.out.println("2. Import Baskets");
            System.out.println("3. View Baskets");
            System.out.println("4. Edit Basket");
            System.out.println("5. Delete Basket");
            System.out.println("6. Exit Program\n");

            try
            {
                input = scanner.nextInt();
                scanner.nextLine();

                System.out.println();

                switch (input)
                {
                    case 1:

                        controller.CreateBasket();

                        break;

                    case 2:

                        controller.ImportBaskets();

                        break;

                    case 3:

                        controller.ViewBaskets();

                        break;

                    case 4:

                        controller.EditBasket();

                        break;

                    case 5:

                        controller.DeleteBasket();

                        break;

                    case 6:

                        System.out.println("\nGoodbye!");

                        return;

                    default:

                        System.out.println("\nInvalid Option");
                }
            }

            catch(InputMismatchException e)
            {
                System.out.println("\nInvalid Option");
                scanner.nextLine();
            }
        }
    }
}