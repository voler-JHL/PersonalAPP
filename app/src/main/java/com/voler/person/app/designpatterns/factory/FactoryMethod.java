package com.voler.person.app.designpatterns.factory;

/**
 * FactoryMethod Created by voler on 2017/7/29.
 * 说明：工厂方法模式
 */

public class FactoryMethod {
    public static void main(String[] args) {

    }

    //客户类
    class Customer {
        public  void main() {
            FactoryBMW320 factoryBMW320 = new FactoryBMW320();
            BMW320 bmw320 = factoryBMW320.createBMW();

            FactoryBMW523 factoryBMW523 = new FactoryBMW523();
            BMW523 bmw523 = factoryBMW523.createBMW();
        }
    }


    //工厂类

    interface FactoryBMW {
        BMW createBMW();
    }

    public class FactoryBMW320 implements FactoryBMW {

        @Override
        public BMW320 createBMW() {

            return new BMW320();
        }

    }

    public class FactoryBMW523 implements FactoryBMW {
        @Override
        public BMW523 createBMW() {

            return new BMW523();
        }
    }

    //产品类

    abstract class BMW {
        public BMW() {

        }
    }

    public class BMW320 extends BMW {
        public BMW320() {
            System.out.println("制造-->BMW320");
        }
    }

    public class BMW523 extends BMW {
        public BMW523() {
            System.out.println("制造-->BMW523");
        }
    }
}
