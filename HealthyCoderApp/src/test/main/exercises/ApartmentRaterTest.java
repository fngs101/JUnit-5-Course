package main.exercises;

import main.BMICalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentRaterTest
{

    @ParameterizedTest
    @CsvSource(value = {"40.0, 240000.0, 1", "50.0, 450000.0, 2", "65.0, 610000.0, 2"})
    void should_ReturnCorrectRating_When_CorrectApartment(Double area, BigDecimal price, int rating)
    {
        //given
        Apartment apartment = new Apartment(area, price);
        int expected = rating;

        //when
        int actual = ApartmentRater.rateApartment(apartment);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnErrorValue_When_IncorrectApartment()
    {
        //given
        Apartment apartment = new Apartment(0.0, (new BigDecimal("500000.0")));
        int expected = -1;

        //when
        int actual = ApartmentRater.rateApartment(apartment);

        //then
        assertEquals(expected, actual);

    }

    @Test
    void should_Calculate_AverageRating_When_CorrectApartmentList()
    {
        //given
        List<Apartment> apartmentList = new ArrayList<>();
        apartmentList.add(new Apartment(60, (new BigDecimal("500000.0"))));
        apartmentList.add(new Apartment(45, (new BigDecimal("300000.0"))));
        double expected = 1.5;

        //when
        double actual = ApartmentRater.calculateAverageRating(apartmentList);

        //then
        assertEquals(expected, actual);

    }

    @Test
    void should_ThrowExceptionInCalculateAverageRating_When_Empty_ApartmentList()
    {
        //given
        List<Apartment> apartmentList = new ArrayList<>();

        //when
        Executable executable = () -> ApartmentRater.calculateAverageRating(apartmentList);

        //then
        assertThrows(RuntimeException.class, executable);
    }
}