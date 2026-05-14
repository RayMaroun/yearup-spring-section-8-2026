package com.pluralsight;

import com.pluralsight.classes.EtchASketch;
import com.pluralsight.classes.Robot;
import com.pluralsight.classes.RobotVacuum;
import com.pluralsight.classes.Turtle;
import com.pluralsight.interfaces.ICleaner;
import com.pluralsight.interfaces.IMovable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();

        List<Object> things = new ArrayList<>();

        things.add(new Robot("Bolts"));
        things.add(new RobotVacuum("NotASweeper"));
        things.add(new EtchASketch());

        for (Object thing : things) {
            if (thing instanceof ICleaner) {
                /*ICleaner cleaner = (ICleaner) thing;
                cleaner.clean();*/
                ((ICleaner) thing).clean();
            }
        }

        System.out.println("=========================================");

        EtchASketch etchASketch = new EtchASketch();
        etchASketch.print();
        etchASketch.print("Potato");

        System.out.println("=========================================");

        IMovable robotVacuum = new RobotVacuum("Bolts");
        robotVacuum = new Turtle("Slow");


        List<String> names = new ArrayList<>();
        names = new LinkedList<>();
    }

}
