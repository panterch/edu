package ch.fhzh.jmock.gettingstarted;

public interface IPublisher {

    public abstract void add(ISubscriber subscriber);

    public abstract void publish(String message);

}