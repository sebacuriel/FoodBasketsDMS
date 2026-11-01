/**
 * Sebastian Yael Curiel Franco
 * Software Development I - CEN 3024C - 13038
 * October 31, 2026
 * FoodList.java
 * This class is used to store information
 * about all the existing food objects,
 * as well as defining new ones.
 */

import java.util.*;

public class FoodList
{
    private final ArrayList<Food> foods = new ArrayList<>();

    public FoodList()
    {
        foods.add(new Food("Apple", "Fruit", 30, 80));
        foods.add(new Food("Banana", "Fruit", 10, 100));
        foods.add(new Food("Strawberry", "Fruit", 8, 40));
        // todo: add more in database phase

        foods.add(new Food("Granola", "Additive", 5, 60));
        foods.add(new Food("Milk", "Additive", 15, 80));
        // todo: add more in database phase
    }

    public ArrayList<Food> getFoods()
    {
        return foods;
    }
}
