package com.rookies.schedule.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFilter;

@TableName("scheduler")
public class Scheduler {
    @TableId("id")
    private Long id;

    private String text;
    @TableField("start_date")
    @JSONField(name="start_date")
    private String startDate;
    @TableField("end_date")
    @JSONField(name="end_date")
    private String endDate;
    @TableField("events_pid")
    @JSONField(name = "events_pid",serialzeFeatures= {SerializerFeature.WriteNullStringAsEmpty})
    private Long eventsPid;
    @TableField("event_length")
    @JSONField(name = "event_length",serialzeFeatures= {SerializerFeature.WriteNullStringAsEmpty})
    private Long eventLength;
    @TableField("rec_type")
    @JSONField(name = "rec_type")
    private String recType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public Long getEventsPid() {
        return eventsPid;
    }

    public void setEventsPid(Long eventsPid) {
        this.eventsPid = eventsPid;
    }

    public Long getEventLength() {
        return eventLength;
    }

    public void setEventLength(Long eventLength) {
        this.eventLength = eventLength;
    }

    public String getRecType() {
        return recType;
    }

    public void setRecType(String recType) {
        this.recType = recType == null ? null : recType.trim();
    }
}
