package ru.whitegray.singleton;

public class SingletonExample {
    private static SingletonExample singletonExample;
    private static Integer val = 5;

    private SingletonExample() {

    }

    public static SingletonExample getInstance() {
        if(singletonExample == null) {
            singletonExample = new SingletonExample();
        }

        val++;
        return singletonExample;
    }

}
