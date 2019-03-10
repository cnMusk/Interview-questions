package com.spark.eureka;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liudan on 2019/3/10.
 */
public class Jioushu {

    private static ReentrantLock lock =new ReentrantLock();;

    private  static Condition jishuCondition = lock.newCondition();;

    private  static Condition oushuCondition  = lock.newCondition();;

    private  static  int total = 10;




    public static  void main (String[] args){





        PrindJishu jishu = new PrindJishu();

        PrindOushu oushu = new PrindOushu();

        Thread t1 = new Thread(jishu);
        Thread t2 = new Thread(oushu);
        t2.start();
        t1.start();



    }

    static  class  PrindJishu implements Runnable{




        @Override
        public void run() {
            try {
                lock.lock();
                for (int i=0 ; i<total;i++) {
                    if( i % 2 == 0){
                        System.out.println(i);
                        jishuCondition.signal();
                        oushuCondition.await(1, TimeUnit.MILLISECONDS);

                    }
                }

            }catch(Exception e) {
            }finally {
                lock.unlock();
            }
        }
    }


    private static class  PrindOushu implements Runnable{



        @Override
        public void run() {
            try {
                lock.lock();
                for (int i=0 ; i<total;i++) {
                    if (i % 2 != 0) {
                        System.out.println(i);
                        oushuCondition.signal();
                        jishuCondition.await(1, TimeUnit.MILLISECONDS);


                    }
                }

            }catch(Exception e) {

            }finally {
                lock.unlock();
            }
        }
    }

}
