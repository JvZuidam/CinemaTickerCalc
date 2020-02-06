import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.Order;
import org.junit.Test;

public class OrderTest {

    public boolean isPositiveNumber(int number) {

        boolean result = false;
        if (number >= 1) {
            result = true;
        }
        return result;
    }

    @Test
    public void checkIfOrderNrIsPositive() {
        //Arrange
        Order order = new Order(0, true);
        OrderTest orderTest = new OrderTest();

        //Act
        int result = order.getOrderNr();


        //Assert
        assertTrue(orderTest.isPositiveNumber(result));
    }
}
