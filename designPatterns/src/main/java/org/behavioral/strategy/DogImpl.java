package org.behavioral.strategy;

import java.util.Arrays;
import java.util.List;

public class DogImpl implements AnimalStrategy {

    @Override
    public List<String> getList(String animalType) {
        return Arrays.asList("Chihuahua","Doberman","Pitbull");
    }
}
