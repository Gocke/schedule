package com.rookies.schedule.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rookies.schedule.pojo.Scheduler;
import com.rookies.schedule.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Gocke
 * 2019/6/5
 */
@RestController
public class ScheduleController {
    /**
     * 从数据库中取数
     */
    @Autowired
    private SchedulerService schedulerService;

    @RequestMapping("/getPage")
    public String getListByPage() {
        IPage<Scheduler> page = new Page<Scheduler>(0, 5);
        IPage<Scheduler> getPage = schedulerService.page(page);
        String pageStr = JSON.toJSONString(getPage);
        return pageStr;
    }

    @PostMapping("/addSchdule")
    public int addScheduler(Long  id,String text,String start_date,String end_date,Long events_pid,Long event_length,String rec_type) {
        Scheduler scheduler = new Scheduler();
        scheduler.setId(id);
        scheduler.setText(text);
        scheduler.setStartDate(start_date);
        scheduler.setEndDate(end_date);
        scheduler.setEventsPid(events_pid);
        scheduler.setEventLength(event_length);
        scheduler.setRecType(rec_type);
        int i =0;
        boolean saveOrUpdate = schedulerService.saveOrUpdate(scheduler);
        if(saveOrUpdate){
            i=1;
        }
        return i;
    }

    @PostMapping("/updateSchdule")
    public  int updateSchduler(String  id,String text,String start_date,String end_date,Long events_pid,Long event_length,String rec_type )
    {
        Scheduler scheduler = new Scheduler();
        scheduler.setId(Long.parseLong(id.split("#")[0]));
        scheduler.setText(text);
        scheduler.setStartDate(start_date);
        scheduler.setEndDate(end_date);
        scheduler.setEventsPid(events_pid);
        scheduler.setEventLength(event_length);
        scheduler.setRecType(rec_type);
        int i = 0;
        boolean saveOrUpdate = schedulerService.saveOrUpdate(scheduler);
        if (saveOrUpdate) {
            i = 1;
        }
        return i;
    }

    @PostMapping("/delById")
    public int delById(String id){
        int i=0;
        Long newId = Long.parseLong(id.split("#")[0]);
        boolean byId = schedulerService.removeById(newId);
        if(byId){
            i=1;
        }
        return i;
    }


    @GetMapping("/getSchdule")
    public String getSchduler() {
        List<Scheduler> list =  schedulerService.list();
        String listStr = JSON.toJSONString(list);
        return listStr;
    }

}
