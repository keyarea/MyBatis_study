package net.qipo.bean;


import java.util.List;

/**
 * 锁表
 */
public class Lock {
    private Integer id;
    private String lockName;

    // 查询锁子的时候把所有的钥匙也查出来
    private List<Key> keys;
    // 1-1关联 1-n关联 n-n关联

    // 一个钥匙开一把锁

    public List<Key> getKeys() {
        return keys;
    }

    public void setKeys(List<Key> keys) {
        this.keys = keys;
    }

    @Override
    public String toString() {
        return "Lock{" +
                "id=" + id +
                ", lockName='" + lockName + '\'' +
                ", keys=" + keys +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }
}
