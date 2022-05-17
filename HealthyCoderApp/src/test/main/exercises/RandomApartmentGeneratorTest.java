package main.exercises;

import org.junit.jupiter.api.RepeatedTest;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RandomApartmentGeneratorTest
{

    private static final double MAX_MULTIPLIER = 4.0;


    @RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
    void should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice()
    {
        //given
        double minArea = 30.0;
        double maxArea = minArea * MAX_MULTIPLIER;
        BigDecimal minPricePerSquareMeter = new BigDecimal(3000.0);
        BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(MAX_MULTIPLIER));

        //when
        RandomApartmentGenerator generator = new RandomApartmentGenerator();
        Apartment apartment = generator.generate();

        //then
        BigDecimal maxApartmentPrice = new BigDecimal(apartment.getArea()).multiply(maxPricePerSquareMeter);
        BigDecimal minApartmentPrice = new BigDecimal(apartment.getArea()).multiply(minPricePerSquareMeter);
        assertAll(
                () -> assertTrue(apartment.getArea() >= minArea),
                () -> assertTrue(apartment.getArea() <= maxArea),
                () -> assertTrue(apartment.getPrice().compareTo(minApartmentPrice) >= 0),
                () -> assertTrue(apartment.getPrice().compareTo(maxApartmentPrice) <= 0)
        );

    }


    @RepeatedTest(10)
    void should_GenerateCorrectApartment_When_CustomMinAreaMinPrice()
    {
        //given
        double minArea = 15.0;
        double maxArea = minArea * MAX_MULTIPLIER;

        BigDecimal minPricePerSquareMeter = new BigDecimal(5000.0);
        BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(MAX_MULTIPLIER));

        // when
        RandomApartmentGenerator generator = new RandomApartmentGenerator(minArea, minPricePerSquareMeter);
        Apartment apartment = generator.generate();

        // then
        BigDecimal maxApartmentPrice = new BigDecimal(apartment.getArea()).multiply(maxPricePerSquareMeter);
        BigDecimal minApartmentPrice = new BigDecimal(apartment.getArea()).multiply(minPricePerSquareMeter);
        assertAll(
                () -> assertTrue(apartment.getArea() >= minArea),
                () -> assertTrue(apartment.getArea() <= maxArea),
                () -> assertTrue(apartment.getPrice().compareTo(minApartmentPrice) >= 0),
                () -> assertTrue(apartment.getPrice().compareTo(maxApartmentPrice) <= 0)
        );
    }
}