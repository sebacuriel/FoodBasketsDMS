/**
 * Sebastian Yael Curiel Franco
 * Software Development I - CEN 3024C - 13038
 * October 18, 2026
 * Controller.java
 * This class handles all the methods called by the Main class.
 * It contains a CreateBasket method to handle manually adding baskets.
 * It contains an ImportBaskets method to add multiple baskets through a file.
 * It contains a ViewBaskets method to see the arraylist of created baskets.
 * It contains an EditBasket method to edit information about a basket.
 * It contains a DeleteBasket method to delete a basket from the arraylist.
 * It contains a CalculateCalories method to automatically get the basket's calories.
 * It contains a CalculatePrepTime method to automatically get the basket's preparation time.
 */

import java.io.*;
import java.util.*;

public class Controller
{
    private final ArrayList<Basket> baskets = new ArrayList<>();
    private final FoodList foodList = new FoodList();

    Scanner scanner = new Scanner(System.in);

    /**
     * method: CreateBasket
     * parameters: none
     * return: none
     * purpose: create baskets and add them to an arraylist
     */
    public void CreateBasket()
    {
        System.out.println("= Fruit List =\n");

        for (Food food : foodList.getFoods())
        {
            if (food.getCategory().equals("Fruit"))
            {
                System.out.println("- " + food.getFoodName());
            }
        }

        System.out.println("\n= Additives List =\n");

        for (Food food : foodList.getFoods())
        {
            if (food.getCategory().equals("Additive"))
            {
                System.out.println("- " + food.getFoodName());
            }
        }

        System.out.print("\nEnter the Basket Name: ");
        String name = scanner.nextLine();

        if (name.isBlank())
        {
            name = "Unnamed";
        }

        Food fruit1;
        Food fruit2;
        Food fruit3;
        Food additive1;
        Food additive2;

        do
        {
            System.out.print("Enter a Fruit: ");
            String input = scanner.nextLine();

            fruit1 = null;

            for (Food food : foodList.getFoods())
            {
                if (food.getFoodName().equalsIgnoreCase(input) && food.getCategory().equals("Fruit"))
                {
                    fruit1 = food;

                    break;
                }
            }

            if (fruit1 == null)
            {
                System.out.println("\nInvalid.\n");
            }
        }
        while (fruit1 == null);

        do
        {
            System.out.print("Enter a Fruit: ");
            String input = scanner.nextLine();

            fruit2 = null;

            for (Food food : foodList.getFoods())
            {
                if (food.getFoodName().equalsIgnoreCase(input) && food.getCategory().equals("Fruit"))
                {
                    fruit2 = food;

                    break;
                }
            }

            if (fruit2 == null)
            {
                System.out.println("\nInvalid.\n");
            }
        }
        while (fruit2 == null);

        do
        {
            System.out.print("Enter a Fruit: ");
            String input = scanner.nextLine();

            fruit3 = null;

            for (Food food : foodList.getFoods())
            {
                if (food.getFoodName().equalsIgnoreCase(input) && food.getCategory().equals("Fruit"))
                {
                    fruit3 = food;

                    break;
                }
            }

            if (fruit3 == null)
            {
                System.out.println("\nInvalid.\n");
            }
        }
        while (fruit3 == null);

        do
        {
            System.out.print("Enter an Additive: ");
            String input = scanner.nextLine();

            additive1 = null;

            for (Food food : foodList.getFoods())
            {
                if (food.getFoodName().equalsIgnoreCase(input) && food.getCategory().equals("Additive"))
                {
                    additive1 = food;

                    break;
                }
            }

            if (additive1 == null)
            {
                System.out.println("\nInvalid.\n");
            }
        }
        while (additive1 == null);

        do
        {
            System.out.print("Enter an Additive: ");
            String input = scanner.nextLine();

            additive2 = null;

            for (Food food : foodList.getFoods())
            {
                if (food.getFoodName().equalsIgnoreCase(input) && food.getCategory().equals("Additive"))
                {
                    additive2 = food;

                    break;
                }
            }

            if (additive2 == null)
            {
                System.out.println("\nInvalid.\n");
            }
        }
        while (additive2 == null);

        Basket basket = new Basket(baskets.size() + 1, name, fruit1, fruit2, fruit3, additive1, additive2);
        baskets.add(basket);

        System.out.println();

        ViewBaskets();
    }

    /**
     * method: ImportBaskets
     * parameters: none
     * return: none
     * purpose: add baskets through a file to an arraylist
     */
    public void ImportBaskets()
    {
        System.out.print("Enter the File's Directory: ");
        String directory = scanner.nextLine();

        try
        {
            File file = new File(directory);
            Scanner fscanner = new Scanner(file);

            int line = 0;

            while (fscanner.hasNextLine())
            {
                line++;

                String data = fscanner.nextLine();
                String[] format = data.split(" - ");

                if (format.length != 6)
                {
                    System.out.println("\nInvalid Format (Must be Name - F1 - F2 - F3 - A1 - A2) in Line " + line);

                    continue;
                }

                if (format[0].isBlank())
                {
                    format[0] = "Unnamed";
                }

                Food fruit1 = null;
                Food fruit2 = null;
                Food fruit3 = null;
                Food additive1 = null;
                Food additive2 = null;

                for (Food food : foodList.getFoods())
                {
                    if (food.getFoodName().equalsIgnoreCase(format[1]) && food.getCategory().equals("Fruit"))
                    {
                        fruit1 = food;
                    }

                    if (food.getFoodName().equalsIgnoreCase(format[2]) && food.getCategory().equals("Fruit"))
                    {
                        fruit2 = food;
                    }

                    if (food.getFoodName().equalsIgnoreCase(format[3]) && food.getCategory().equals("Fruit"))
                    {
                        fruit3 = food;
                    }

                    if (food.getFoodName().equalsIgnoreCase(format[4]) && food.getCategory().equals("Additive"))
                    {
                        additive1 = food;
                    }

                    if (food.getFoodName().equalsIgnoreCase(format[5]) && food.getCategory().equals("Additive"))
                    {
                        additive2 = food;
                    }
                }

                if (fruit1 != null && fruit2 != null && fruit3 != null && additive1 != null && additive2 != null)
                {
                    Basket basket = new Basket(baskets.size() + 1, format[0], fruit1, fruit2, fruit3, additive1, additive2);
                    baskets.add(basket);
                }

                else
                {
                    System.out.println("\nInvalid food in Line " + line);
                }
            }

            System.out.println();

            ViewBaskets();
        }

        catch (FileNotFoundException e)
        {
            System.out.println("\nInvalid File, check your directory.");
            System.out.println("Make sure to include the file extension.");
        }
    }

    /**
     * method: ViewBaskets
     * parameters: none
     * return: none
     * purpose: show all the baskets in the arraylist
     */
    public void ViewBaskets()
    {
        if (baskets.isEmpty())
        {
            System.out.println("No baskets created.");

            return;
        }

        for (Basket basket : baskets)
        {
            System.out.println(basket);
            System.out.println("Prep Time: " + CalculatePrepTime(basket) + " seconds.");
            System.out.println("Total Calories: " + CalculateCalories(basket) + " cal.");
            System.out.println();
        }
    }

    /**
     * method: EditBasket
     * parameters: none
     * return: none
     * purpose: edit a basket's contents from the arraylist
     */
    public void EditBasket()
    {
        int n;

        if (baskets.isEmpty())
        {
            System.out.println("No baskets created.");

            return;
        }

        do
        {
            System.out.print("Enter a basket #: ");

            try
            {
                n = scanner.nextInt();
                scanner.nextLine();
            }

            catch (InputMismatchException e)
            {
                System.out.println("\nInvalid.");
                System.out.println();
                scanner.nextLine();

                continue;
            }

            for (Basket basket : baskets)
            {
                if (basket.getBasketNumber() == n)
                {
                    System.out.println();

                    int option;

                    do
                    {
                        System.out.println("- Basket #" + n + " -");
                        System.out.println();

                        System.out.println("1. Basket Name");
                        System.out.println("2. Fruit 1");
                        System.out.println("3. Fruit 2");
                        System.out.println("4. Fruit 3");
                        System.out.println("5. Additive 1");
                        System.out.println("6. Additive 2");
                        System.out.println("7. Back to Menu");
                        System.out.println();

                        System.out.print("Select an edit option: ");

                        try
                        {
                            option = scanner.nextInt();
                            scanner.nextLine();
                        }

                        catch (InputMismatchException e)
                        {
                            System.out.println("\nInvalid.");
                            System.out.println();
                            scanner.nextLine();

                            continue;
                        }

                        switch (option)
                        {
                            case 1:

                                System.out.print("Enter a new Basket Name: ");
                                String name = scanner.nextLine();

                                if (name.isBlank())
                                {
                                    name = "Unnamed";
                                }

                                basket.setBasketName(name);

                                break;

                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:

                                Food foodIndex = null;
                                String cat = option <= 4 ? "Fruit" : "Additive";

                                do
                                {
                                    if (cat.equals("Fruit"))
                                    {
                                        System.out.println("\n= Fruit List =\n");

                                        for (Food food : foodList.getFoods())
                                        {
                                            if (food.getCategory().equals("Fruit"))
                                            {
                                                System.out.println("- " + food.getFoodName());
                                            }
                                        }

                                        System.out.println();
                                        System.out.print("Enter a Fruit: ");
                                    }

                                    else
                                    {
                                        System.out.println("\n= Additives List =\n");

                                        for (Food food : foodList.getFoods())
                                        {
                                            if (food.getCategory().equals("Additive"))
                                            {
                                                System.out.println("- " + food.getFoodName());
                                            }
                                        }

                                        System.out.println();
                                        System.out.print("Enter an Additive: ");
                                    }

                                    String input = scanner.nextLine();

                                    for (Food food : foodList.getFoods())
                                    {
                                        if (food.getFoodName().equalsIgnoreCase(input) && food.getCategory().equals(cat))
                                        {
                                            foodIndex = food;

                                            break;
                                        }
                                    }

                                    if (foodIndex == null)
                                    {
                                        System.out.println("\nInvalid.\n");
                                    }
                                }
                                while (foodIndex == null);

                                switch (option)
                                {
                                    case 2:

                                        basket.setFruit1(foodIndex);

                                        break;

                                    case 3:

                                        basket.setFruit2(foodIndex);

                                        break;

                                    case 4:

                                        basket.setFruit3(foodIndex);

                                        break;

                                    case 5:

                                        basket.setAdditive1(foodIndex);

                                        break;

                                    case 6:

                                        basket.setAdditive2(foodIndex);

                                        break;
                                }

                                break;

                            case 7:

                                return;

                            default:

                                System.out.println("\nInvalid.");
                        }

                        System.out.println();
                    }
                    while (true);
                }
            }

            System.out.println("\nInvalid Basket #");
            System.out.println();
        }
        while (true);
    }

    /**
     * method: DeleteBasket
     * parameters: none
     * return: none
     * purpose: remove a basket from the arraylist
     */
    public void DeleteBasket()
    {
        int n;

        if (baskets.isEmpty())
        {
            System.out.println("No baskets created.");

            return;
        }

        do
        {
            System.out.print("Enter a basket #: ");

            try
            {
                n = scanner.nextInt();
            }

            catch (InputMismatchException e)
            {
                System.out.println("\nInvalid.");
                System.out.println();
                scanner.nextLine();

                continue;
            }

            for (Basket basket : baskets)
            {
                if (basket.getBasketNumber() == n)
                {
                    String selection;

                    do
                    {
                        System.out.print("Confirm deletion of Basket #" + n + "? (Y/N) - ");
                        selection = scanner.next();

                        if (!selection.equalsIgnoreCase("Y") && !selection.equalsIgnoreCase("N"))
                        {
                            System.out.println("\nInvalid.\n");
                        }
                    }
                    while (!selection.equalsIgnoreCase("Y") && !selection.equalsIgnoreCase("N"));

                    if (selection.equalsIgnoreCase("Y"))
                    {
                        baskets.remove(basket);

                        System.out.println("\nRemoved Basket #" + n);
                    }

                    else
                    {
                        System.out.println("\nBasket was not deleted.");
                    }

                    return;
                }
            }

            System.out.println("\nInvalid #");
            System.out.println();
        }
        while (true);
    }

    /**
     * method: CalculateCalories
     * parameters: basket
     * return: int
     * purpose: automatically calculate a basket's
     * calories using the selected food objects' data.
     */
    public int CalculateCalories(Basket basket)
    {
        int c = 0;

        if (basket.getFruit1() != null)
        {
            c += basket.getFruit1().getCalories();
        }

        if (basket.getFruit2() != null)
        {
            c += basket.getFruit2().getCalories();
        }

        if (basket.getFruit3() != null)
        {
            c += basket.getFruit3().getCalories();
        }

        if (basket.getAdditive1() != null)
        {
            c += basket.getAdditive1().getCalories();
        }

        if (basket.getAdditive2() != null)
        {
            c += basket.getAdditive2().getCalories();
        }

        return c;
    }

    /**
     * method: CalculatePrepTime
     * parameters: basket
     * return: double
     * purpose: automatically calculate a basket's
     * prep time using the selected food objects' data.
     */
    public double CalculatePrepTime(Basket basket)
    {
        double t = 0;

        if (basket.getFruit1() != null)
        {
            t += basket.getFruit1().getPrepTime();
        }

        if (basket.getFruit2() != null)
        {
            t += basket.getFruit2().getPrepTime();
        }

        if (basket.getFruit3() != null)
        {
            t += basket.getFruit3().getPrepTime();
        }

        if (basket.getAdditive1() != null)
        {
            t += basket.getAdditive1().getPrepTime();
        }

        if (basket.getAdditive2() != null)
        {
            t += basket.getAdditive2().getPrepTime();
        }

        return t;
    }
}