import model.Bid;
import model.Car;
import model.Cars;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BidTest {
    Bid b1;
    Bid b2;
    Bid b3;
    Car c1;
    Car c2;
    Car c3;
    User u1;
    User u2;
    User u3;
    User u4;
    User u5;

    @BeforeEach
    void setUp() {
        c1 = new Car();
        c2 = new Car();
        c3 = new Car();
        u1 = new User();
        u2 = new User();
        u3 = new User();
        u4 = new User();
        u5 = new User();
        b1 = new Bid(u1, c1, 1000);
        b2 = new Bid(u2, 2000);
        b3 = new Bid(u2, c2, 3000);
        u3.createUser("user1", "password1", "password1");
        u4.createUser("user2", "password2", "passwordINVALID");
        u5.createUser("user3", "password3", "password3");
    }

    @Test
    void testConstructor() {
        assertEquals(c1, b1.getCar());
        assertEquals(u1, b1.getUser());
        assertEquals(1000, b1.getBidAmount());

        assertNull(b2.getCar());
        assertEquals(u2, b2.getUser());
        assertEquals(2000, b2.getBidAmount());

        assertEquals(c2, b3.getCar());
        assertEquals(u2, b3.getUser());
        assertEquals(3000, b3.getBidAmount());

        assertEquals("", c1.getMake());
        assertEquals("", c1.getModel());
        assertEquals("", c1.getColour());
        assertEquals("", c1.getTransmission());
        assertEquals("", c1.getDriveType());
        assertEquals("", c1.getCondition());
        assertEquals("", c1.getDescription());
        assertEquals(0, c1.getYear());
        assertEquals(0, c1.getPrice());
        assertEquals(0, c1.getMileage());
        assertEquals(0, c1.getId());
        assertEquals(0, c1.getBids().size());

        assertEquals("", c2.getMake());
        assertEquals("", c2.getModel());
        assertEquals("", c2.getColour());
        assertEquals("", c2.getTransmission());
        assertEquals("", c2.getDriveType());
        assertEquals("", c2.getCondition());
        assertEquals("", c2.getDescription());
        assertEquals(0, c2.getYear());
        assertEquals(0, c2.getPrice());
        assertEquals(0, c2.getMileage());
        assertEquals(0, c2.getId());
        assertEquals(0, c2.getBids().size());
    }

    @Test
    void testGetCar() {
        assertEquals(c1, b1.getCar());
        assertNull(b2.getCar());
        assertEquals(c2, b3.getCar());
    }

    @Test
    void testGetBidAmount() {
        assertEquals(1000, b1.getBidAmount());
        assertEquals(2000, b2.getBidAmount());
        assertEquals(3000, b3.getBidAmount());
    }

    @Test
    void testGetBid() {
        assertEquals(c1.getListingCar() + "\nBid: $" + 1000, b1.getBid());
        assertEquals("Bid: $" + 2000, b2.getBid());
        assertEquals(c2.getListingCar() + "\nBid: $" + 3000, b3.getBid());
    }

    @Test
    public void testUserCreateUser() {
        assertTrue(u3.createUser("user1", "password1", "password1"));
        assertFalse(u4.createUser("user2", "password2", "passwordINVALID"));
        assertTrue(u5.createUser("user3", "password3", "password3"));
    }


    /*@Test
    public void testLogin() {
        assertTrue(u1.login("user1", "password1"));
        assertFalse(user3.login("user3", "FAIL"));
    }*/

    @Test
    public void testUserCreateCar() {
        Car user2Car = new Car();
        user2Car.setMake("Honda");
        user2Car.setModel("Civic");
        user2Car.setColour("Red");
        user2Car.setTransmission("Manual");
        user2Car.setDriveType("Front Wheel Drive");
        user2Car.setCondition("Used");
        user2Car.setYear(2010);
        user2Car.setPrice(10000);
        user2Car.setMileage(100000);
        user2Car.setId(1);
        user2Car.setDescription("This is a test car");
        u4.createCar(user2Car);
        assertEquals(0, u3.getCars().size());
        assertEquals(1, u4.getCars().size());
    }

    @Test
    void testUserDeleteCar() {
        Car user2Car = new Car();
        user2Car.setId(0);
        Car user2Car2 = new Car();
        user2Car2.setId(1);
        u4.createCar(user2Car);
        u4.createCar(user2Car2);
        assertNull(u3.deleteCar(0));
        u4.deleteCar(0);
        assertEquals(1, user2Car2.getId());
    }

    @Test
    void testUserEditCar() {
        assertFalse(u3.editCar(0, 1, "Honda"));

        Car user2Car = new Car();
        user2Car.setId(1);
        u4.createCar(user2Car);

        assertTrue(u4.editCar(1, 1, "Honda"));
        Car user2Car2 = new Car();
        user2Car2.setId(2);
        u4.createCar(user2Car2);
        user2Car2.setMake("Honda1");
        u4.editCar(2, 1, "Honda2");
        assertEquals("Honda2", user2Car2.getMake());
    }

    @Test
    void testUserPlaceBid() {
        Car c1 = new Car();
        c1.setId(1);
        Car c2 = new Car();
        c2.setId(2);
        Car c3 = new Car();
        c3.setId(3);

        Cars cars = new Cars();
        cars.addCar(c1);
        cars.addCar(c2);
        assertTrue(u3.placeBid(1, 10000, cars));
        assertTrue(u3.placeBid(2, 20000, cars));
        assertEquals(2, u3.getBids().size());
    }

    @Test
    void testUserGetBids() {
        Car c1 = new Car();
        c1.setId(1);
        Car c2 = new Car();
        c2.setId(2);
        Car c3 = new Car();
        c3.setId(3);

        Cars cars = new Cars();
        cars.addCar(c1);
        cars.addCar(c2);
        u3.placeBid(1, 10000, cars);
        u3.placeBid(2, 20000, cars);
        assertEquals(2, u3.getBids().size());
    }

    @Test
    void testUserGetName() {
        assertEquals("user1", u3.getName());
        assertNull(u4.getName());
        assertEquals("user3", u5.getName());
    }

    @Test
    void testUserGetCars() {
        Car user3Car = new Car();
        Car user3Car2 = new Car();
        u5.createCar(user3Car);
        u5.createCar(user3Car2);
        assertEquals(0, u3.getCars().size());
        assertEquals(2, u5.getCars().size());
    }

    @Test
    void testUserGetWonCars() {
        Car c1 = new Car();
        c1.setId(1);
        Car c2 = new Car();
        c2.setId(2);
        Car c3 = new Car();
        c3.setId(3);

        Cars cars = new Cars();
        cars.addCar(c1);
        cars.addCar(c2);
        u3.placeBid(1, 10000, cars);
        u3.placeBid(2, 20000, cars);
        u3.placeBid(3, 30000, cars);
        c1.bid(u3, 10000);
        assertEquals(2, u3.getBids().size());
        assertEquals(0, u3.getWonCars().size());

        u4.placeBid(1, 40000, cars);
        c1.bid(u4, 40000);
        c1.giveToWinner();
        assertEquals(1, u4.getWonCars().size());
    }

    @Test
    void testCarSetMake() {
        c1.setMake("Honda");
        assertEquals("Honda", c1.getMake());
        c2.setMake("Toyota");
        assertEquals("Toyota", c2.getMake());
        c3.setMake("Ford");
        assertEquals("Ford", c3.getMake());
    }

    @Test
    void testCarIsExpired() {
        assertFalse(c1.isExpired());
        c2.markExpired();
        assertTrue(c2.isExpired());
        assertFalse(c3.isExpired());
    }

    @Test
    void testCarSetModel() {
        c1.setModel("Civic");
        assertEquals("Civic", c1.getModel());
        c2.setModel("Corolla");
        assertEquals("Corolla", c2.getModel());
        c3.setModel("F-150");
        assertEquals("F-150", c3.getModel());
    }

    @Test
    void testSetColour() {
        c1.setColour("Red");
        assertEquals("Red", c1.getColour());
        c2.setColour("Blue");
        assertEquals("Blue", c2.getColour());
        c3.setColour("Black");
        assertEquals("Black", c3.getColour());
    }

    @Test
    void testSetTransmission() {
        c1.setTransmission("Automatic");
        assertEquals("Automatic", c1.getTransmission());
        c2.setTransmission("Manual");
        assertEquals("Manual", c2.getTransmission());
        c3.setTransmission("Automatic");
        assertEquals("Automatic", c3.getTransmission());
    }

    @Test
    void testCarSetDriveType() {
        c1.setDriveType("Front Wheel Drive");
        assertEquals("Front Wheel Drive", c1.getDriveType());
        c2.setDriveType("RWD");
        assertEquals("RWD", c2.getDriveType());
        c3.setDriveType("All Wheel Drive");
        assertEquals("All Wheel Drive", c3.getDriveType());
    }

    @Test
    void testCarSetCondition() {
        c1.setCondition("New");
        assertEquals("New", c1.getCondition());
        c2.setCondition("Used");
        assertEquals("Used", c2.getCondition());
        c3.setCondition("Poor");
        assertEquals("Poor", c3.getCondition());
    }

    @Test
    void testCarSetDescription() {
        c1.setDescription("desc1");
        assertEquals("desc1", c1.getDescription());
        c2.setDescription("desc2");
        assertEquals("desc2", c2.getDescription());
        c3.setDescription("desc3");
        assertEquals("desc3", c3.getDescription());
    }

    @Test
    void testCarSetYear() {
        c1.setYear(2019);
        assertEquals(2019, c1.getYear());
        c2.setYear(2018);
        assertEquals(2018, c2.getYear());
        c3.setYear(2017);
        assertEquals(2017, c3.getYear());
    }

    @Test
    void testCarSetPrice() {
        c1.setPrice(10000);
        assertEquals(10000, c1.getPrice());
        c2.setPrice(20000);
        assertEquals(20000, c2.getPrice());
        c3.setPrice(30000);
        assertEquals(30000, c3.getPrice());
    }

    @Test
    void testCarSetMileage() {
        c1.setMileage(1000);
        assertEquals(1000, c1.getMileage());
        c2.setMileage(2000);
        assertEquals(2000, c2.getMileage());
        c3.setMileage(3000);
        assertEquals(3000, c3.getMileage());
    }

    @Test
    void testCarSetId() {
        c1.setId(1);
        assertEquals(1, c1.getId());
        c2.setId(2);
        assertEquals(2, c2.getId());
        c3.setId(3);
        assertEquals(3, c3.getId());
    }

    @Test
    void testCarGetHighestBid() {
        User u1 = new User();
        User u2 = new User();

        assertNull(c1.getHighestBid());

        c1.bid(u1, 1000);
        assertEquals(1000, c1.getHighestBid().getBidAmount());

        c2.bid(u2, 2000);
        c2.bid(u1, 3000);
        assertEquals(3000, c2.getHighestBid().getBidAmount());
    }

    @Test
    void testCarGetListingCar() {
        assertEquals("[ condition]    , ; with 0km for $0.", c1.getListingCar());

        c2.setMake("Toyota");
        c2.setModel("Corolla");
        c2.setColour("Blue");
        c2.setTransmission("Manual");
        c2.setDriveType("Rear Wheel Drive");
        c2.setCondition("Used");
        c2.setMileage(0);
        c2.setPrice(0);
        assertEquals("[Used condition] Manual Blue Toyota Corolla, Rear Wheel Drive; with 0km for $0.",
                c2.getListingCar());

        c3.setMake("Ford");
        c3.setModel("F-150");
        c3.setColour("Black");
        c3.setTransmission("Automatic");
        c3.setDriveType("All Wheel Drive");
        c3.setCondition("Excellent");
        c3.setMileage(0);
        c3.setPrice(0);
        c3.setTimer(10000);
        assertEquals("[Excellent condition] Automatic Black Ford F-150, All Wheel Drive; with 0km for $0." +
                        "\n\tTime remaining: 10000 seconds.",
                c3.getListingCar());
    }

    @Test
    void testCarEdit() {
        c1.setMake("Opel");
        c1.setModel("Astra");
        c1.setColour("Red");
        c1.setTransmission("Automatic");
        c1.setDriveType("Front Wheel Drive");
        c1.setCondition("New");
        c1.setDescription("desc");
        c1.setYear(2019);
        c1.setPrice(10000);
        c1.setMileage(1000);
        c1.edit(1, "Toyota");
        assertEquals("Toyota", c1.getMake());
        c1.edit(2, "Corolla");
        assertEquals("Corolla", c1.getModel());
        c1.edit(3, "Blue");
        assertEquals("Blue", c1.getColour());
        c1.edit(4, "Manual");
        assertEquals("Manual", c1.getTransmission());
        c1.edit(5, "Rear Wheel Drive");
        assertEquals("Rear Wheel Drive", c1.getDriveType());
        c1.edit(6, "Used");
        assertEquals("Used", c1.getCondition());
        c1.edit(7, "1997");
        assertEquals(1997, c1.getYear());
        c1.edit(8, "100000");
        assertEquals(100000, c1.getMileage());
        c1.edit(9, "1000");
        assertEquals(1000, c1.getPrice());
        c1.edit(10, "editDesc");
        assertEquals("editDesc", c1.getDescription());

        c2.edit(1, "Nissan");
        assertEquals("Nissan", c2.getMake());
        c2.edit(2, "R34 GTR");
        assertEquals("R34 GTR", c2.getModel());
        c2.edit(3, "Red");
        assertEquals("Red", c2.getColour());
        c2.edit(4, "Automatic");
        assertEquals("Automatic", c2.getTransmission());
        c2.edit(5, "All Wheel Drive");
        assertEquals("All Wheel Drive", c2.getDriveType());
        c2.edit(6, "Used");
        assertEquals("Used", c2.getCondition());
        c2.edit(7, "1996");
        assertEquals(1996, c2.getYear());
        c2.edit(8, "1000");
        assertEquals(1000, c2.getMileage());
        c2.edit(9, "100000");
        assertEquals(100000, c2.getPrice());
        c2.edit(10, "editDesc2");
        assertEquals("editDesc2", c2.getDescription());
    }

    @Test
    void testCarBid() {
        User u1 = new User();
        User u2 = new User();
        assertEquals(0, c1.getBids().size());

        c2.bid(u1, 2000);
        assertEquals(1, c2.getBids().size());
        assertEquals(u1, c2.getBids().get(0).getUser());

        c3.bid(u1, 4000);
        c3.bid(u2, 1000);
        assertEquals(2, c3.getBids().size());
        assertEquals(u1, c3.getBids().get(0).getUser());
    }

    @Test
    void testCarSetTimer() {
        c1.setTimer(0);
        assertEquals(0, c1.getTimeLeftInSeconds());

        c2.setTimer(10000);
        assertEquals(10000, c2.getTimeLeftInSeconds());

        c3.setTimer(200);
        assertEquals(200, c3.getTimeLeftInSeconds());
    }

    @Test
    void testCarMarkExpired() {
        c2.setTimer(10000);
        assertFalse(c2.isExpired());

        c3.setTimer(20000);
        c3.markExpired();
        assertTrue(c3.isExpired());
    }

    @Test
    void testCarGiveToWinner() {
        User u1 = new User();
        User u2 = new User();
        c1.bid(u1, 1000);
        c1.giveToWinner();
        assertEquals(1, u1.getWonCars().size());
        assertEquals(c1, u1.getWonCars().get(0));
        assertTrue(c1.isExpired());

        c2.bid(u1, 2000);
        c2.bid(u2, 3000);
        c2.giveToWinner();
        assertEquals(1, u2.getWonCars().size());
        assertEquals(c2, u2.getWonCars().get(0));
        assertTrue(c2.isExpired());

        c3.bid(u1, 2000);
        c3.bid(u2, 1000);
        c3.giveToWinner();
        assertEquals(2, u1.getWonCars().size());
        assertEquals(c3, u1.getWonCars().get(1));
        assertTrue(c3.isExpired());

    }

    @Test
    void testCarGetTimeLeftInSeconds() {
        c1.setTimer(0);
        assertEquals(0, c1.getTimeLeftInSeconds());

        c2.setTimer(10000);
        assertEquals(10000, c2.getTimeLeftInSeconds());

        c3.setTimer(20000);
        assertEquals(20000, c3.getTimeLeftInSeconds());
    }

    @Test
    void testCarGetBids() {
        User u1 = new User();
        User u2 = new User();

        assertEquals(0, c1.getBids().size());

        c2.bid(u1, 2000);
        assertEquals(1, c2.getBids().size());
        assertEquals(u1, c2.getBids().get(0).getUser());

        c3.bid(u1, 4000);
        c3.bid(u2, 1000);
        assertEquals(2, c3.getBids().size());
        assertEquals(u1, c3.getBids().get(0).getUser());
    }

    @Test
    void testCarGetMake() {
        assertEquals("", c1.getMake());
        c2.setMake("Nissan");
        assertEquals("Nissan", c2.getMake());
        c3.setMake("Honda");
        assertEquals("Honda", c3.getMake());
    }

    @Test
    void testCarGetModel() {
        assertEquals("", c1.getModel());
        c2.setModel("R34 GTR");
        assertEquals("R34 GTR", c2.getModel());
        c3.setModel("Civic");
        assertEquals("Civic", c3.getModel());
    }

    @Test
    void testCarGetColour() {
        assertEquals("", c1.getColour());
        c2.setColour("Red");
        assertEquals("Red", c2.getColour());
        c3.setColour("Blue");
        assertEquals("Blue", c3.getColour());
    }

    @Test
    void testCarGetTransmission() {
        assertEquals("", c1.getTransmission());
        c2.setTransmission("Automatic");
        assertEquals("Automatic", c2.getTransmission());
        c3.setTransmission("Manual");
        assertEquals("Manual", c3.getTransmission());
    }

    @Test
    void testCarGetDriveType() {
        assertEquals("", c1.getDriveType());
        c2.setDriveType("All Wheel Drive");
        assertEquals("All Wheel Drive", c2.getDriveType());
        c3.setDriveType("Front Wheel Drive");
        assertEquals("Front Wheel Drive", c3.getDriveType());
    }

    @Test
    void testCarGetCondition() {
        assertEquals("", c1.getCondition());
        c2.setCondition("Used");
        assertEquals("Used", c2.getCondition());
        c3.setCondition("New");
        assertEquals("New", c3.getCondition());
    }

    @Test
    void testCarGetYear() {
        assertEquals(0, c1.getYear());
        c2.setYear(1996);
        assertEquals(1996, c2.getYear());
        c3.setYear(2019);
        assertEquals(2019, c3.getYear());
    }

    @Test
    void testCarGetMileage() {
        assertEquals(0, c1.getMileage());
        c2.setMileage(1000);
        assertEquals(1000, c2.getMileage());
        c3.setMileage(2);
        assertEquals(2, c3.getMileage());
    }

    @Test
    void testCarGetPrice() {
        assertEquals(0, c1.getPrice());
        c2.setPrice(100000);
        assertEquals(100000, c2.getPrice());
        c3.setPrice(10000000);
        assertEquals(10000000, c3.getPrice());
    }

    @Test
    void testCarGetDescription() {
        assertEquals("", c1.getDescription());
        c2.setDescription("desc2");
        assertEquals("desc2", c2.getDescription());
        c3.setDescription("desc3");
        assertEquals("desc3", c3.getDescription());
    }
}