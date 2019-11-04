package net.qipo.dao;

import net.qipo.bean.Lock;

public interface LockDao {

    // 查锁子的时候把所有的钥匙也都查出来
    public Lock getLockById(Integer id);

    public Lock getLockByIdSimple(Integer id);

    public Lock getLockByIdByStep(Integer id);
}
