import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //hard coding open and close time to keep restaurant open all the time for testing
        //so that at anytime this test case is executed, the restaurant is open
        LocalTime openingTime = LocalTime.parse("00:00:00");
        LocalTime closingTime = LocalTime.parse("23:59:59");

        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        addMenuItems(restaurant);
        assertTrue(restaurant.isRestaurantOpen());

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){

        //Keeping the restaurant open for only 2 seconds at 3 AM, when the chance of running this test is NIL.
        LocalTime openingTime = LocalTime.parse("03:00:00");
        LocalTime closingTime = LocalTime.parse("03:00:02");

        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        addMenuItems(restaurant);
        assertFalse(restaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        addRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws ItemNotFoundException {
        addRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        addRestaurant();
        assertThrows(ItemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>ORDER VALUE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void selecting_items_in_menu_should_calculate_the_total_order_value() throws ItemNotSelectedException{
        addRestaurant();

        ArrayList<String> itemNames = new ArrayList<String>();
        itemNames.add("Sweet corn soup");
        itemNames.add("Vegetable lasagne");
        itemNames.add("Chicken lasagne");

        int initialOrderValue = restaurant.getOrderValue(itemNames);
        restaurant.addToMenu("Sizzling brownie",319);
        itemNames.add("Sizzling brownie");
        assertEquals(initialOrderValue+319,restaurant.getOrderValue(itemNames));
    }

    @Test
    public void not_selecting_any_item_should_throw_exception(){
        addRestaurant();
        ArrayList<String> itemNames = new ArrayList<String>();
        assertThrows(ItemNotSelectedException.class,
                ()->restaurant.getOrderValue(itemNames));
    }

    //<<<<<<<<<<<<<<<<<<<<<<<ORDER VALUE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>METHODS ADDED FOR FACTORING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    private Restaurant addRestaurant() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        addMenuItems(restaurant);
        return restaurant;
    }

    private void addMenuItems(Restaurant restaurant) {
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Chicken lasagne", 369);

    }
    //<<<<<<<<<<<<<<<<<<<<METHODS ADDED FOR FACTORING>>>>>>>>>>>>>>>>>>>>>>>>>>

}