package com.mintlolly.basics.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create by on jiangbo 2020/5/19 17:18
 * <p>
 * Description:ThreadPoolExecutor
 */
public class ThreadPoolDemo {
    private static int produceTaskSleepTime = 5;
    private static int consumeTaskSleepTime = 5000;
    private static int produceTaskMaxNumber = 17; //定义最大添加10个线程到线程池中

    public static void main(String[] args) {

        /**
         *
         * java开发手册规定不允许使用Executors去创建线程池
         *
         * FixedThreadPool和SingleThreadPool:允许的请求队列长度为Integer.MAX_VALUE
         * 可能会堆积大量请求，从而导致OOM
         * CachedThreadPool 和 ScheduledThreadPool：允许的创建线程数量为 Integer.MAX_VALUE，
         * 可能会创建大量的线程，从而导致 OOM
         */

        //不建议使用
        Executors.newFixedThreadPool(4);

        //建议使用ThreadPoolExecutor构造一个线程池

        //corePoolSize表示线程池的常驻核心线程数。设置过小则会频繁创建和销毁线程
        //设置过大，则会浪费系统资源，所以开发者需要根据实际业务来调整此值
        int corePoolSize = 4;

        //maximumPoolSize表示线程池在任务最多时，最大可以创建的线程数。
        //必须>0,>=corePoolSize,此值在任务比较多，且不能存放任务队列时才会用到
        int maximumPoolSize = 8;

        //keepAliveTime表示线程的存活时间，当线程池空闲时并且超过了此时间，多余的线程就会销毁
        //直到数量等于corePoolSize，if maximumPoolSize = corePoolsize,那么线程空闲时也不会销毁
        long keepAliveTime = 1000;

        //unit 表示存活时间的单位，它是配合keepAliveTime参数共同使用的
        TimeUnit unit = TimeUnit.SECONDS;

        //workQueue 表示线程池的任务队列，当线程池所有线程都在处理任务时
        //如果来了新任务就会缓存到此任务队列中排队等待执行。
        BlockingQueue workQueue = new LinkedBlockingQueue<Runnable>(4);

        //threadFactory表示线程的创建工厂，此参数一般用的较少，一般不指定，会使用默认的线程工厂的方法来创建线程
        //Executors.defaultThreadFactory() 为默认的线程创建工厂
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        //RejectedExecutionHandler表示指定线程池的拒绝策略，当线程池的任务已经在缓存队列workQueue中存储满了后
        //并且不能创建新的线程来执行此任务，就会用到拒绝策略
        //它属于一种限流保护的机制。

        //当线程池中的任务队列已经被存满，再有任务添加时会先判断当前线程池中的线程数是否大于等于线程池的最大值，如果是，则会触发线程池的拒绝策略。
        //AbortPolicy，终止策略，线程池会抛出异常并终止执行，它是默认的拒绝策略；
        //CallerRunsPolicy，把任务交给当前线程来执行；
        //DiscardPolicy，忽略此任务（最新的任务）；
        //DiscardOldestPolicy，忽略最早的任务（最先加入队列的任务）。
        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,threadFactory,abortPolicy);

        ThreadPoolExecutor myThreadPool = new MyThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);

        //AbortPolicy，终止策略，线程池会抛出异常并终止执行，它是默认的拒绝策略； 4 8 1000 seconds 4 abortPolicy
        for (int i = 1; i <= produceTaskMaxNumber; i++) {
            try {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName());
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //ThreadPoolExecutor的执行方式有execute 和submit两种
        //区别execute()不能接收返回值
        myThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello execute");
            }
        });
        //submit方法可以接收线程池执行的返回值
        Future<String> future = myThreadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Hello submit");
                return "success";
            }
        });
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
    }


    //我们也可以自定义一个线程工厂，通过实现 ThreadFactory 接口来完成，
    // 这样就可以自定义线程的名称或线程执行的优先级了。
    static class DeaultThreadFactory implements ThreadFactory{
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DeaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        //创建线程
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group,r,namePrefix+threadNumber.getAndIncrement(),0);

            if(t.isDaemon()){
                t.setDaemon(false);
            }
            if(t.getPriority() != Thread.NORM_PRIORITY){
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

    /**
     * 线程池扩展
     */
    static class MyThreadPoolExecutor extends ThreadPoolExecutor{

        //保存线程执行时间
        private final ThreadLocal<Long> localTime = new ThreadLocal<>();

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            Long sTime = System.nanoTime();
            localTime.set(sTime);
            System.out.println(String.format("%s | before | time=%s",t.getName(), sTime));
            super.beforeExecute(t,r);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            Long eTime = System.nanoTime();
            Long totalTime = eTime - localTime.get();
            System.out.println(String.format("%s | after | time=%s | 耗时：%s 毫秒",Thread.currentThread().getName(),eTime,(totalTime/1000000.0)));
            super.afterExecute(r,t);
        }
    }
}
