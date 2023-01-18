package com.github.mxsm.dledger.benchmark;

import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

public class Test {

    private CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();

    public static void main(String[] args) throws Exception{

        Test test = new Test();

        for(int i = 0; i < 10; ++i){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("getNums");
                    for(;;){
                        test.getNums();
                    }
                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(1);

        for(int i = 0; i < 3; ++i){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("add");
                    for(;;){
                        test.add(Thread.currentThread().getName());
                    }
                }
            }).start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("clean");

                    for(;;){
                        test.clean();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

        TimeUnit.SECONDS.sleep(1000);
    }

    private void add(String a){
        set.add(a);
    }

    public  int getNums(){
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return set.size();
    }

    public void clean(){
        set.clear();
    }

}
