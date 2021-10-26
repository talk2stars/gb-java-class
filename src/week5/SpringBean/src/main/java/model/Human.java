package model;

public class Human extends Animal {

    private long id;

    private String name;

    @Override
    public void act() {
        System.out.println("人做");
    }

}
