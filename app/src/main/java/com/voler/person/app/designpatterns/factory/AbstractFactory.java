package com.voler.person.app.designpatterns.factory;

/**
 * AbstractFactory Created by voler on 2017/7/29.
 *
 * 适用场景：
 不管是简单工厂模式，工厂方法模式还是抽象工厂模式，他们具有类似的特性，所以他们的适用场景也是类似的。
 首先，作为一种创建类模式，在任何需要生成复杂对象的地方，都可以使用工厂方法模式。有一点需要注意的地方就是复杂对象适合使用工厂模式，
 而简单对象，特别是只需要通过new就可以完成创建的对象，无需使用工厂模式。如果使用工厂模式，就需要引入一个工厂类，会增加系统的复杂度。
 其次，工厂模式是一种典型的解耦模式，迪米特法则在工厂模式中表现的尤为明显。假如调用者自己组装产品需要增加依赖关系时，可以考虑使用工厂模式。
 将会大大降低对象之间的耦合度。
 再次，由于工厂模式是依靠抽象架构的，它把实例化产品的任务交由实现类完成，扩展性比较好。也就是说，当需要系统有比较好的扩展性时，
 可以考虑工厂模式，不同的产品用不同的实现工厂来组装。
 */

public class AbstractFactory {

    public class Customer {
        public void main() {
            //生产宝马320系列配件
            FactoryBMW320 factoryBMW320 = new FactoryBMW320();
            factoryBMW320.createEngine();
            factoryBMW320.createAircondition();

            //生产宝马523系列配件
            FactoryBMW523 factoryBMW523 = new FactoryBMW523();
            factoryBMW523.createEngine();
            factoryBMW523.createAircondition();
        }
    }


    //产品族
    //发动机以及型号
    public interface Engine {

    }

    public class EngineA implements Engine {
        public EngineA() {
            System.out.println("制造-->EngineA");
        }
    }

    public class EngineB implements Engine {
        public EngineB() {
            System.out.println("制造-->EngineB");
        }
    }

    //空调以及型号
    public interface Aircondition {

    }

    public class AirconditionA implements Aircondition {
        public AirconditionA() {
            System.out.println("制造-->AirconditionA");
        }
    }

    public class AirconditionB implements Aircondition {
        public AirconditionB() {
            System.out.println("制造-->AirconditionB");
        }
    }


    //工厂类
    //创建工厂的接口
    public interface BMWFactory {
        //制造发动机
        Engine createEngine();

        //制造空调
        Aircondition createAircondition();
    }


    //为宝马320系列生产配件
    public class FactoryBMW320 implements BMWFactory {

        @Override
        public Engine createEngine() {
            return new EngineA();
        }

        @Override
        public Aircondition createAircondition() {
            return new AirconditionA();
        }
    }

    //宝马523系列
    public class FactoryBMW523 implements BMWFactory {

        @Override
        public Engine createEngine() {
            return new EngineB();
        }

        @Override
        public Aircondition createAircondition() {
            return new AirconditionB();
        }


    }

}
