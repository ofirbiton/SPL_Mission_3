//Ofir Biton 208582494 & Naim Moshe 315852269
package assig3_1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String[] args){
        Semaphore semaphore = new Semaphore(1);
        AtomicBoolean thread3Ready = new AtomicBoolean(false);
        AtomicBoolean thread2Ready = new AtomicBoolean(false);
        AtomicBoolean thread1Ready = new AtomicBoolean(true);


        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    semaphore.acquire();
                    while (!thread1Ready.get() || thread3Ready.get() || thread2Ready.get()) {
                        semaphore.release();
                        semaphore.acquire();

                    }
                    
                    System.out.println("A");
                    thread1Ready.set(false);
                    thread2Ready.set(true);
                    semaphore.release(); // Release semaphore to let t2 execute B

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    semaphore.acquire();
                    while (!thread2Ready.get() || thread1Ready.get()) {
                        semaphore.release();
                        semaphore.acquire();

                    }

                    System.out.println("B");
                    thread3Ready.set(true);
                    semaphore.release(); // Release semaphore to let t3 execute C or t1 execute A
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(() -> {
            while (true) {
                try {
                    semaphore.acquire();
                    while (thread1Ready.get() || !thread3Ready.get() || !thread2Ready.get()) {
                        semaphore.release();
                        semaphore.acquire();

                    }

                    System.out.println("C");
                    thread1Ready.set(true);
                    thread2Ready.set(false);
                    thread3Ready.set(false);
                    semaphore.release(); // Release semaphore to let t1 execute A again

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
