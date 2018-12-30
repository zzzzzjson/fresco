package soexample.umeng.com.eventbusdemo.eventbean;

/**
 * event事件 相当于EventBus用来传值的对象参数
 */
public class EventMessage {
    private String name;
    private String sex;
    private int age;
    private int eventType;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public EventMessage(String name, String sex, int age,int eventType) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "EventMessage{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
