package com.rookies.schedule.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rookies.schedule.mapper.SchedulerMapper;
import com.rookies.schedule.pojo.Scheduler;
import org.springframework.stereotype.Service;

/**
 * Created by Gocke
 * 2019/6/5
 */
@Service
public class SchedulerServiceImp extends ServiceImpl<SchedulerMapper, Scheduler> implements SchedulerService {
}
