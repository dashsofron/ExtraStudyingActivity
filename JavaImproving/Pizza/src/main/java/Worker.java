/**
 * class for every worker in company - bakers and delivery men
 * every worker works independently - have his own thread
 */
public abstract class Worker {
    Thread thread;

    /**
     * create and start thread
     */
    public void startWork() {
        thread = new Thread(this::work);
        thread.start();
    }

    //different for every worker's category
    public abstract void work();

    /**
     * gives information about order
     *
     * @param message - depends of worker category
     */
    public void showProgress(String message) {
        System.out.println(message);
    }

    /**
     * function that stop work at the end of working day
     */
    public void stopWork() {
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            assert false;
        }
    }
}
