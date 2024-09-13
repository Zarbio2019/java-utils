package org.utils;

import java.util.Arrays;
import java.util.List;

public class CatImpl implements AnimalStrategy {

    @Override
    public List<String> getList(String data) {
        return Arrays.asList("Siames","Kitty");
    }
}
