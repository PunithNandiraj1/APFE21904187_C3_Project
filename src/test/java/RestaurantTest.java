import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;



import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    public void init() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //REFACTORED ALL THE REPEATED LINES OF CODE AND UPDATED

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, I HAVE USED THE CONCEPT OF MOCKING AND TO DO THAT ONE MUST IMPORT MOCKITO WHICH IS ALREADY DONE IN THIS CODE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITTTEN UNIT TEST CASE HERE TO CHECK IF THE RESTAURANT TIME IS BETWEEN OPENING AND CLOSING TIME WHICH SHOULD GIVE TRUE.
        Restaurant mockClass = Mockito.spy(restaurant);
        Mockito.when(mockClass.getCurrentTime()).thenReturn(LocalTime.parse("11:00:00"));
        boolean isOpen = mockClass.isRestaurantOpen();

        assertTrue(isOpen);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITTEN UNIT TEST CASE HERE TO CHECK IF THE RESTAURANT TIME IS BETWEEN OPENING AND CLOSING TIME WHICH SHOULD GIVE FALSE
        Restaurant mockClass = Mockito.spy(restaurant);
        Mockito.when(mockClass.getCurrentTime()).thenReturn(LocalTime.parse("23:00:00"));
        boolean isOpen = mockClass.isRestaurantOpen();

        assertFalse(isOpen);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {


        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {


        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }


    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}