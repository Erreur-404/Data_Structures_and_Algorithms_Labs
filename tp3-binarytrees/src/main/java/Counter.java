public class Counter {

    private long timer;

    public void beginTime(){
        setTimer(System.nanoTime());
    }

    public Long endTimer(){
        return (System.nanoTime() - getTimer());
    }

    public long getTimer() {
        return timer;
    }

    private void setTimer(long timer) {
        this.timer = timer;
    }
}
