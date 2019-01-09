package ch3.s1.collection;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 类的描述
 *
 * @author nancy.wang
 * @Time 2019/1/8
 */


class Student implements Comparable<Student> {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Student o) {

        return this.id.compareTo(o.getId());
    }
}

public class PriorityBlockingQueueTest {
    public static void main(String[] str) {
        PriorityBlockingQueue<Student> queue = new PriorityBlockingQueue<Student>();
        for (int i = 0; i < 12; i++) {
            Student user = new Student();
            Random random = new Random();
            Long n = random.nextLong()%(100-10+1);
            user.setId(n);
            user.setName("nancy"+n);
            queue.add(user);

        }
        for(int i=0; i<12; i++){
            Student u = queue.poll();
            System.out.println("优先级是："+u.getId()+","+u.getName());
        }

    }
}
