package com.rookies.schedule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rookies.schedule.pojo.Scheduler;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchedulerMapper extends BaseMapper<Scheduler> {
    int deleteByPrimaryKey(Long id);

    int insert(Scheduler record);

    int insertSelective(Scheduler record);

    Scheduler selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Scheduler record);

    int updateByPrimaryKey(Scheduler record);
}
