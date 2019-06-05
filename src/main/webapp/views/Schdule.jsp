<%@page contentType="text/html;charset=UTF-8"  language="java" %>
<!-- 万能头文件-->
<% String path=request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
    <head>
        <title>菜鸡学习Schduler</title>
        <!-- 引入日程管理系统的相关js和css-->
        <script type="text/javascript" src="<%=basePath%>static/jquery/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>static/bootstrap/js/bootstrap.js"></script>
        <script type="text/javascript" src="<%=basePath%>static/bootstrap/js/bootstrap-treeview.js"></script>
        <script type="text/javascript" src="<%=basePath%>static/dhtmlxScheduler/codebase/dhtmlxscheduler.js"></script>
        <script type="text/javascript" src="<%=basePath%>static/dhtmlxScheduler/codebase/locale/locale_cn.js"></script>
        <script type="text/javascript" src="<%=basePath%>static/dhtmlxScheduler/codebase/ext/dhtmlxscheduler_year_view.js"></script>
        <script type="text/javascript" src="<%=basePath%>static/dhtmlxScheduler/codebase/ext/dhtmlxscheduler_tooltip.js"></script>
        <script type="text/javascript" src="<%=basePath%>static/dhtmlxScheduler/codebase/ext/dhtmlxscheduler_pdf.js"></script>
        <script type="text/javascript" src="<%=basePath%>static/dhtmlxScheduler/codebase/ext/dhtmlxscheduler_offline.js"></script>
        <script type="text/javascript" src="<%=basePath%>static/dhtmlxScheduler/codebase/ext/dhtmlxscheduler_multiselect.js"></script>
        <script type="text/javascript" src="<%=basePath%>static/dhtmlxScheduler/codebase/ext/dhtmlxscheduler_offline.js"></script>
        <script type="text/javascript" src="<%=basePath%>static/dhtmlxScheduler/codebase/ext/dhtmlxscheduler_key_nav.js"></script>
        <script type="text/javascript" src="<%=basePath%>static/dhtmlxScheduler/codebase/ext/dhtmlxscheduler_recurring.js"></script>
        <script type="text/javascript" src="<%=basePath%>static/dhtmlxScheduler/codebase/locale/recurring/locale_recurring_cn.js"></script>
        <script type="text/javascript" src="<%=basePath%>static/dhtmlxScheduler/codebase/ext/dhtmlxscheduler_editors.js"></script>
        <script type="text/javascript" src="<%=basePath%>static/dhtmlxScheduler/codebase/ext/dhtmlxscheduler_minical.js"></script>

        <!--引入css -->
        <%--<link rel="stylesheet" type="text/css" href="<%=basePath%>static/bootstrap/css/bootstrap.css">
              <link rel="stylesheet" type="text/css" href="<%=basePath%>static/bootstrap/css/bootstrap-theme.css">
                <link rel="stylesheet" type="text/css" href="<%=basePath%>static/bootstrap/css/bootstrap-treeview.css">--%>
        <link rel="stylesheet" type="text/css" href="<%=basePath%>static/dhtmlxScheduler/codebase/dhtmlxscheduler_material.css">

    </head>
    <body onload="init()">
        <div class="webapp-title">
              <ul class="webapp site">
                  <li class="breadcrumb-item">菜鸡学习Schduler</li>
              </ul>
        </div>
        <div id="scheduler_here" class="dhx_cal_container" style="width: 100%;height: 100%;">
             <div class="dhx_cal_navline">
                 <div class="dhx_cal_prev_button">&nbsp;</div>
                 <div class="dhx_cal_next_button">&nbsp;</div>
                 <div class="dhx_cal_today_button"></div>
                 <div class="dhx_cal_date"></div>
                 <div class="dhx_cal_tab" name="day_tab" style="right: 200px;"></div>
                 <div class="dhx_cal_tab" name="week_tab" style="right: 140px;"></div>
                 <div class="dhx_cal_tab" name="year_tab" style="right: 282px;"></div>
                 <div class="dhx_cal_tab" name="month_tab" style="right:80px;"></div>
             </div>
            <div class="dhx_cal_header"></div>
            <div class="dhx_cal_data"></div>
        </div>
    </body>
<script type="text/javascript" charset="UTF-8">
    function init() {
        scheduler.config.server_utc=true;
         scheduler.config.multi_day=true;
         scheduler.config.xml_date="%Y-%m-%d %H:%i";
         scheduler.config.first_hour=8;
    }

    scheduler.init('scheduler_here',new Date(),"month");

    scheduler.load("../getSchdule","json");

    // 添加日程的方法
    scheduler.attachEvent("onEventAdded",function(id,ev){
         var time_start_date = scheduler.date.convert_to_utc(ev.start_date);
         var time_end_date = scheduler.date.convert_to_utc(ev.end_date);
         var common=scheduler.date.date_to_str("%m/%d/%Y %H:%i");

        var new_start_date = scheduler.date.add(time_start_date,8, 'hour');
        var new_end_date = scheduler.date.add(time_end_date,8, 'hour');

        var start_date =  common(new_start_date);
        var end_date = common(new_end_date);
      $.ajax({
            url:"../addSchdule",
            type:"post",
            dataType:"json",
            data:{id:id,text:ev.text,start_date:start_date,end_date:end_date,events_pid:ev.events_pid,event_length:ev.event_length,rec_type:ev.rec_type},
            success:function (data) {
                if(data !=1 ){
                    alert("添加失败！");
                    scheduler.load("../getSchdule","json");
                }
            }
        })
        return true;
    });


    // 修改日程
    scheduler.attachEvent("onEventChanged",function (id,ev) {
        //日期的处理
        var time_start_date = scheduler.date.convert_to_utc(ev.start_date);
        var time_end_date = scheduler.date.convert_to_utc(ev.end_date);
        var common=scheduler.date.date_to_str("%m/%d/%Y %H:%i");
        var new_start_date = scheduler.date.add(time_start_date,8, 'hour');
        var new_end_date = scheduler.date.add(time_end_date,8, 'hour');
        var start_date =  common(new_start_date);
        var end_date = common(new_end_date);
        $.ajax({
            url:"../updateSchdule",
            type:"post",
            dataType:"json",
            data:{id:id,text:ev.text,start_date:start_date,end_date:end_date,events_pid:ev.events_pid,event_length:ev.event_length,rec_type:ev.rec_type},
            success:function (data) {
                if(data != 1){
                    alert("更新失败！");
                    scheduler.load("../getSchdule","json");
                }
            }
        })
        return true;
    })
   //删除操作
    scheduler.attachEvent("onEventDeleted",function (id,ev) {
        $.ajax({
            url:"../delById",
            type:"post",
            dataType:"json",
            data:{id:id},
            success:function (data) {
                if(data !=1 ){
                    scheduler.load("../getSchdule","json");
                }
            }
        })
        return true;
    })
</script>
</html>
