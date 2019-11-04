package net.qipo.dao;

import net.qipo.bean.Key;

import java.util.List;

public interface KeyDao {

    /**
     * 将钥匙和锁的信息一起查出
     * @param id
     * @return
     */
    public Key getKeyByID(Integer id);

    public Key getKeyByIDSimple(Integer id);

    public List<Key> getKeysByLockId(Integer id);
}
