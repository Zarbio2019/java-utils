package org.behavioral.strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainApp {

    private Map<AnimalStrategyType, AnimalStrategy> animalByType = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("MainApp");

        // Strategy pattern
        System.out.println("Strategy pattern");

        List<String> animalList = new MainApp().getList("ANIMAL");
        System.out.println("animalList: " + animalList);
    }

    public List<String> getList(String animalType) {
        this.animalByType.put(AnimalStrategyType.DOG, new DogImpl());
        this.animalByType.put(AnimalStrategyType.CAT, new CatImpl());

        AnimalStrategyType animalStrategyType = isAnimalNotNull(animalType) ? AnimalStrategyType.DOG : AnimalStrategyType.CAT;

        AnimalStrategy animalStrategy = this.animalByType.get(animalStrategyType);
        return animalStrategy.getList(animalType);
    }

    public boolean isAnimalNotNull(String animalType) {
        return Objects.nonNull(animalType) && !animalType.isEmpty();
    }
}