package net.qipo.dao;

import net.qipo.bean.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherDao {

    public Teacher getTeacherById(Integer id);

    public List<Teacher> getTeacherByCondition(Teacher teacher);

    public List<Teacher> getTeacherByIdIn(@Param("ids") List<Integer> ids);

    public List<Teacher> getTeacherByConditionChoose(Teacher teacher);

    public Boolean updateTeacher(Teacher teacher);
}
