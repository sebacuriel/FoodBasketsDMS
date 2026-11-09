/**
 * Sebastian Yael Curiel Franco
 * Software Development I - CEN 3024C - 13038
 * November 6, 2026
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
        foods.add(new Food("Strawberry", "Fruit", 8, 50));
        foods.add(new Food("Watermelon", "Fruit", 8, 40));
        foods.add(new Food("Pineapple", "Fruit", 8, 80));
        foods.add(new Food("Peach", "Fruit", 8, 60));
        foods.add(new Food("Grapes", "Fruit", 20, 100));
        foods.add(new Food("Pear", "Fruit", 30, 90));

        foods.add(new Food("Almonds", "Additive", 8, 120));
        foods.add(new Food("Yogurt", "Additive", 10, 100));
        foods.add(new Food("Granola", "Additive", 5, 60));
        foods.add(new Food("Raisins", "Additive", 8, 90));
        foods.add(new Food("Milk", "Additive", 15, 80));
    }

    public ArrayList<Food> getFoods()
    {
        return foods;
    }
}
