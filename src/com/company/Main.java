package com.company;

import java.sql.Array;
import java.util.Scanner;

abstract class Bike{
    protected boolean workable=true;
    protected int countWheels;
    protected int probabilityCrash=20;
    public void buying(){
        System.out.println("You buy the bike");
    }
    public boolean getWorkable(){
        return this.workable;
    }
    abstract public void use();
    abstract public void repair();
    abstract public void crash();
}
class OneWheelBike extends Bike{
    protected float diametrFirstWheel;
    public OneWheelBike(float diametrFirstWheel){
        this.countWheels=1;
        this.diametrFirstWheel=diametrFirstWheel;
        System.out.println("Bike with one wheel created");
    }
    public OneWheelBike(float diametrFirstWheel, int probabilityCrash){
        this(diametrFirstWheel);
        this.probabilityCrash=probabilityCrash;
    }
    public float getDiametrFirstWheel() {
        return this.diametrFirstWheel;
    }
    @Override
    public void use() {
        System.out.println("You use the bike with one wheel");
        if((int)(Math.random()*101)<=this.probabilityCrash){
            crash();
        }

    }
    @Override
    public void repair() {
        this.workable=true;
        System.out.println("Yor bike with one wheel repaired");
    }

    @Override
    public void crash() {
        this.workable=false;
        System.out.println("Bike with one wheel crashed");
    }
}
class TwoWheelBike extends Bike{
    protected float diametrFirstWheel;
    protected float diametrSecondWheel;
    public TwoWheelBike(float diametrFirstWheel, float diametrSecondWheel){
        this.countWheels=2;
        this.diametrFirstWheel=diametrFirstWheel;
        this.diametrSecondWheel=diametrSecondWheel;
        System.out.println("Bike with two wheel created");
    }
    public float getDiametrFirstWheel() {
        return this.diametrFirstWheel;
    }
    public float getDiametrSecondWheel() {
        return this.diametrSecondWheel;
    }
    public TwoWheelBike(float diametrFirstWheel, float diametrSecondWheel, int probabilityCrash){
        this(diametrFirstWheel, diametrSecondWheel);
        this.probabilityCrash=probabilityCrash;
    }
    @Override
    public void use() {
        System.out.println("You use the bike with two wheel");
        if((int)(Math.random()*101)<=this.probabilityCrash){
            crash();
        }
    }
    @Override
    public void repair() {
        this.workable=true;
        System.out.println("Yor bike with two wheel repaired");
    }

    @Override
    public void crash() {
        this.workable=false;
        System.out.println("Bike with two wheel crashed");
    }
}
class ThreeWheelBike extends Bike{
    protected float diametrFirstWheel;
    protected float diametrSecondWheel;
    protected float diametrThirdWheel;
    public ThreeWheelBike(float diametrFirstWheel, float diametrSecondWheel, float diametrThirdWheel){
        this.countWheels=3;
        this.diametrFirstWheel=diametrFirstWheel;
        this.diametrSecondWheel=diametrSecondWheel;
        this.diametrThirdWheel=diametrThirdWheel;
        System.out.println("Bike with three wheel created");
    }
    public ThreeWheelBike(float diametrFirstWheel, float diametrSecondWheel, float diametrThirdWheel, int probabilityCrash){
        this(diametrFirstWheel,diametrSecondWheel,diametrThirdWheel);
        this.probabilityCrash=probabilityCrash;
    }
    public float getDiametrFirstWheel() {
        return this.diametrFirstWheel;
    }
    public float getDiametrSecondWheel() {
        return this.diametrSecondWheel;
    }
    public float getDiametrThirdWheel() {
        return this.diametrThirdWheel;
    }
    @Override
    public void use() {
        System.out.println("You use the bike with three wheel");
        if((int)(Math.random()*101)<=this.probabilityCrash){
            crash();
        }
    }
    @Override
    public void repair() {
        this.workable=true;
        System.out.println("Yor bike with three wheel repaired");
    }

    @Override
    public void crash() {
        this.workable=false;
        System.out.println("Bike with three wheel crashed");
    }
}
class Workshop{
    public static void repair(Bike bike){
        bike.repair();
    }
}
public class Main {
    public static float getSumDiametr(Bike bike){
        if(bike instanceof OneWheelBike){
            return ((OneWheelBike)(bike)).getDiametrFirstWheel();
        }
        if(bike instanceof TwoWheelBike){
            return ((TwoWheelBike)(bike)).getDiametrFirstWheel() + ((TwoWheelBike)(bike)).getDiametrSecondWheel();
        }
        return ((ThreeWheelBike)(bike)).getDiametrFirstWheel() + ((ThreeWheelBike)(bike)).getDiametrSecondWheel() + ((ThreeWheelBike)(bike)).getDiametrThirdWheel();
    }

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        Bike bikes[]=new Bike[n];
        for(int i=0;i<n;i++){
            double var=Math.random();
            if((int)(var*3)==0){
                bikes[i]=new OneWheelBike((float) (Math.random()*20)+10, (int)(Math.random()*10)+10);
            }
            if((int)(var*3)==1){
                bikes[i]=new TwoWheelBike((float) (Math.random()*20)+10,(float) (Math.random()*20)+10, (int)(Math.random()*10)+10);
            }
            if((int)(var*3)==2){
                bikes[i]=new ThreeWheelBike((float) (Math.random()*20)+10,(float) (Math.random()*20)+10,(float) (Math.random()*20)+10, (int)(Math.random()*10)+10);
            }
        }

        for(int i=0;i<5;i++){
            for(int j=0;j<n;j++){
                bikes[j].use();
            }
        }
        for(int i=0;i<n;i++){
            if(bikes[i].getWorkable()==false){
                Workshop.repair(bikes[i]);
            }
        }
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(getSumDiametr(bikes[i]) < getSumDiametr(bikes[j])){
                    Bike bike=bikes[i];
                    bikes[i]=bikes[j];
                    bikes[j]=bike;
                }
            }
        }
        System.out.println("Now diametrs is:");
        for(int i=0;i<n;i++){
            System.out.println(getSumDiametr(bikes[i]));
        }
    }
}
