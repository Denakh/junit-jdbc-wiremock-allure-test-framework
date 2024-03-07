package org.github.denakh.testsystem.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_object")
public class TestObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_object_id", nullable = false)
    private long id;

    @Column(name = "param1", nullable = false)
    private int param1;

    @Column(name = "param2")
    private String param2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getParam1() {
        return param1;
    }

    public void setParam1(int param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "id=" + id +
                ", param1=" + param1 +
                ", param2='" + param2 + '\'' +
                '}';
    }

}
