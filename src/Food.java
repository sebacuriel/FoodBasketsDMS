/**
 * Sebastian Yael Curiel Franco
 * Software Development I - CEN 3024C - 13038
 * October 18, 2026
 * Food.java
 * This class defines a Food object with a name,
 * category, preparation time, and calories.
 */

public class Food
{
    private final String foodName;
    private final String category;
    private final double prepTime;
    private final int calories;

    public Food(String foodName, String category, double prepTime, int calories)
    {
        this.foodName = foodName;
        this.category = category;
        this.prepTime = prepTime;
        this.calories = calories;
    }

    public String getFoodName()
    {
        return foodName;
    }

    public String getCategory()
    {
        return category;
    }

    public double getPrepTime()
    {
        return prepTime;
    }

    public int getCalories()
    {
        return calories;
    }
}