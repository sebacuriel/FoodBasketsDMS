/**
 * Sebastian Yael Curiel Franco
 * Software Development I - CEN 3024C - 13038
 * October 18, 2026
 * Basket.java
 * This class defines a Basket object with a number,
 * name, three fruits, and two additives. It also
 * handles how the information is output.
 */

public class Basket
{
    private final int basketNumber;
    private String basketName;

    private Food fruit1;
    private Food fruit2;
    private Food fruit3;
    private Food additive1;
    private Food additive2;

    public Basket(int basketNumber, String basketName, Food fruit1, Food fruit2, Food fruit3, Food additive1, Food additive2)
    {
        this.basketNumber = basketNumber;
        this.basketName = basketName;

        this.fruit1 = fruit1;
        this.fruit2 = fruit2;
        this.fruit3 = fruit3;
        this.additive1 = additive1;
        this.additive2 = additive2;
    }

    public int getBasketNumber()
    {
        return basketNumber;
    }

    public String getBasketName()
    {
        return basketName;
    }

    public Food getFruit1()
    {
        return fruit1;
    }

    public Food getFruit2()
    {
        return fruit2;
    }

    public Food getFruit3()
    {
        return fruit3;
    }

    public Food getAdditive1()
    {
        return additive1;
    }

    public Food getAdditive2()
    {
        return additive2;
    }

    public void setBasketName(String basketName)
    {
        this.basketName = basketName;
    }

    public void setFruit1(Food fruit1)
    {
        this.fruit1 = fruit1;
    }

    public void setFruit2(Food fruit2)
    {
        this.fruit2 = fruit2;
    }

    public void setFruit3(Food fruit3)
    {
        this.fruit3 = fruit3;
    }

    public void setAdditive1(Food additive1)
    {
        this.additive1 = additive1;
    }

    public void setAdditive2(Food additive2)
    {
        this.additive2 = additive2;
    }

    public String toString()
    {
        return

                "#" + basketNumber +
                " - " + basketName +
                " - " + fruit1.getFoodName() +
                " - " + fruit2.getFoodName() +
                " - " + fruit3.getFoodName() +
                " - " + additive1.getFoodName() +
                " - " + additive2.getFoodName();
    }
}
