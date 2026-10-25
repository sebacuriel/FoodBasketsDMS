import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;

public class UnitTests
{
    @org.junit.jupiter.api.Test
    void createBasket()
    {
        FoodList foodList = new FoodList();

        Food fruit1 = foodList.getFoods().get(0);
        Food fruit2 = foodList.getFoods().get(1);
        Food fruit3 = foodList.getFoods().get(2);
        Food additive1 = foodList.getFoods().get(3);
        Food additive2 = foodList.getFoods().get(4);

        Basket basket = new Basket
        (
                1,
                "Test",
                fruit1,
                fruit2,
                fruit3,
                additive1,
                additive2
        );

        assertNotNull(basket, "Error: Basket failed to create.");

        assertEquals(1, basket.getBasketNumber(), "Error: Incorrect basket number.");
        assertEquals("Test", basket.getBasketName(), "Error: Mismatched basket name.");
        assertEquals(fruit1, basket.getFruit1(), "Error: Invalid fruit 1.");
        assertEquals(fruit2, basket.getFruit2(), "Error: Invalid fruit 2.");
        assertEquals(fruit3, basket.getFruit3(), "Error: Invalid fruit 3.");
        assertEquals(additive1, basket.getAdditive1(), "Error: Invalid additive 1.");
        assertEquals(additive2, basket.getAdditive2(), "Error: Invalid additive 2.");
    }

    @org.junit.jupiter.api.Test
    void editBasket()
    {
        FoodList foodList = new FoodList();

        Food fruit1 = foodList.getFoods().get(0);
        Food fruit2 = foodList.getFoods().get(1);
        Food fruit3 = foodList.getFoods().get(2);
        Food additive1 = foodList.getFoods().get(3);
        Food additive2 = foodList.getFoods().get(4);

        Basket basket = new Basket
        (
                1,
                "Test",
                fruit1,
                fruit2,
                fruit3,
                additive1,
                additive2
        );

        basket.setBasketName("Edited Test");
        basket.setFruit1(fruit2);
        basket.setFruit2(fruit3);
        basket.setFruit3(fruit1);
        basket.setAdditive1(additive2);
        basket.setAdditive2(additive1);

        assertEquals("Edited Test", basket.getBasketName(), "Error: failed to update name.");
        assertEquals(fruit2, basket.getFruit1(), "Error: failed to update fruit 1.");
        assertEquals(fruit3, basket.getFruit2(), "Error: failed to update fruit 2.");
        assertEquals(fruit1, basket.getFruit3(), "Error: failed to update fruit 3.");
        assertEquals(additive2, basket.getAdditive1(), "Error: failed to update additive 1.");
        assertEquals(additive1, basket.getAdditive2(), "Error: failed to update additive 2.");
    }

    @org.junit.jupiter.api.Test
    void deleteBasket()
    {
        ArrayList<Basket> baskets = new ArrayList<>();

        FoodList foodList = new FoodList();

        Food fruit1 = foodList.getFoods().get(0);
        Food fruit2 = foodList.getFoods().get(1);
        Food fruit3 = foodList.getFoods().get(2);
        Food additive1 = foodList.getFoods().get(3);
        Food additive2 = foodList.getFoods().get(4);

        Basket basket = new Basket
        (
                1,
                "Test",
                fruit1,
                fruit2,
                fruit3,
                additive1,
                additive2
        );

        baskets.add(basket);

        baskets.remove(basket);

        assertEquals(0, baskets.size(), "Error: failed to update the list.");
        assertFalse(baskets.contains(basket), "Error: failed to delete the basket.");
    }

    @org.junit.jupiter.api.Test
    void calculateCalories()
    {
        Controller controller = new Controller();

        FoodList foodList = new FoodList();

        Food fruit1 = foodList.getFoods().get(0);
        Food fruit2 = foodList.getFoods().get(1);
        Food fruit3 = foodList.getFoods().get(2);
        Food additive1 = foodList.getFoods().get(3);
        Food additive2 = foodList.getFoods().get(4);

        Basket basket = new Basket
        (
                1,
                "Test",
                fruit1,
                fruit2,
                fruit3,
                additive1,
                additive2
        );

        int expected =
        fruit1.getCalories() +
        fruit2.getCalories() +
        fruit3.getCalories() +
        additive1.getCalories() +
        additive2.getCalories();

        assertEquals(expected, controller.CalculateCalories(basket), "Error: calculation mismatch.");
    }

    @org.junit.jupiter.api.Test
    void calculatePrepTime()
    {
        Controller controller = new Controller();

        FoodList foodList = new FoodList();

        Food fruit1 = foodList.getFoods().get(0);
        Food fruit2 = foodList.getFoods().get(1);
        Food fruit3 = foodList.getFoods().get(2);
        Food additive1 = foodList.getFoods().get(3);
        Food additive2 = foodList.getFoods().get(4);

        Basket basket = new Basket
        (
                1,
                "Test",
                fruit1,
                fruit2,
                fruit3,
                additive1,
                additive2
        );

        double expected =
        fruit1.getPrepTime() +
        fruit2.getPrepTime() +
        fruit3.getPrepTime() +
        additive1.getPrepTime() +
        additive2.getPrepTime();

        assertEquals(expected, controller.CalculatePrepTime(basket), "Error: calculation mismatch.");
    }
}