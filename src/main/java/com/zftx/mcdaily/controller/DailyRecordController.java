package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.*;
import com.zftx.mcdaily.service.*;
import com.zftx.mcdaily.util.R;
import com.zftx.mcdaily.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.*;

@Controller
public class DailyRecordController {

    @Autowired
    private DailyRecordService dailyRecordService;

    @Autowired
    private PointService pointService;

    @Autowired
    private SurfaceService surfaceService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private LineService lineService;


    /**
     * 查询日报
     * @param userId 用户id
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    @RequestMapping(value = "/getDaily",method = RequestMethod.GET)
    @ResponseBody
    public R getDailyRecord(Integer userId, String startDate, String endDate, HttpSession session)
    {
        //登录用户
        User user = (User) session.getAttribute("user");
        System.out.println(user == null? "null" : user.toString());
        //日历
        Calendar calendar = Calendar.getInstance();
        //当前系统时间的  前七天
        calendar.add(Calendar.DATE,-7);
        if(user != null)
            userId = userId != null ? userId : user.getId();
        startDate = (startDate != null && endDate != null) ? startDate : calendar.get(Calendar.YEAR)+""+(calendar.get(Calendar.MONTH)+1)+""+calendar.get(Calendar.DAY_OF_MONTH)+"";
        endDate = (startDate != null && endDate != null) ? endDate : Tool.getYear()+""+Tool.getMonth()+""+Tool.getToday()+"";

        ArrayList<HashMap<String, Object>> list = dailyRecordService.getDailyRecord(userId, startDate, endDate);
        if(list !=null &&list.size()>0){
            return R.ok("数据获取成功").put("data",list).put("fullName",user.getFullName());
        }else{
            return R.error("获取数据失败").put("fullName",user.getFullName());
        }
    }

    /**
     * 添加日报记录
     * @param session
     * @param typeName
     * @param type
     * @param surface
     * @param line
     * @param point
     * @param surfaceName
     * @param lineName
     * @param pointName
     * @param eventName
     * @param process
     * @param result
     * @param method
     * @param remarks
     * @return
     */
    @RequestMapping(value = "/addDaily")
    @ResponseBody
    public R addDaily(HttpSession session,String typeName,Integer type,Integer surface,Integer line,Integer point,String surfaceName,String lineName,String pointName,String eventName,String process,String result,String method,String remarks){
        //获取用户信息
        User user = (User)session.getAttribute("user");
        //初始化查询条件
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");//格式化时间
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");//格式化日期

        Type addType = new Type();
        Surface addSurface = new Surface();
        Line addLine = new Line();
        Point addPoint = new Point();
        DailyRecord dailyRecord = new DailyRecord();

        //添加日报的时候添加的type surface line point 关联当前登录的用户ID
        if(typeName != null && !"".equals(typeName)){
            typeService.insertType(addType.setCreateUser(user.getId()).setTypeName(typeName));
            //获取当前插入完成后类型的typeId
            addType = typeService.getType(addType.setId(addType.getId())).get(0);
            dailyRecord.setType(addType.getTypeId().toString());
        }else{
            addType.setTypeId(type);
            dailyRecord.setType(type.toString());
        }

        //插入面记录表，关联当前用户和上下级
        if(surfaceName != null && !"".equals(surfaceName)){
            surfaceService.addSurface(addSurface.setTypeId(addType.getTypeId())
                    .setCreateUser(user.getId())
                    .setSurfaceName(surfaceName));
            //获取surface 插入完成后surfaceId
            addSurface = surfaceService.findAllSurFace(addSurface.setId(addSurface.getId())).get(0);
            dailyRecord.setSurface(addSurface.getSurfaceId().toString());

        }else{
            addSurface.setSurfaceId(surface);
            dailyRecord.setSurface(surface.toString());
        }

        //插入线记录表，关联当前登录用户，和上下级
        if(lineName != null && !"".equals(lineName)){
            lineService.addLine(addLine.setTypeId(addType.getTypeId())
                    .setSurfaceId(addSurface.getSurfaceId())
                    .setCreateUser(user.getId())
                    .setLineName(lineName));
            //获取插入完成后返回的lineId
            addLine = lineService.findLineAll(addLine.setId(addLine.getId())).get(0);
            dailyRecord.setLine(addLine.getLineId().toString());
        }else {
            addLine.setLineId(line);
            dailyRecord.setLine(line.toString());
        }

        //插入点记录表，关联当前用户和上下级
        if(pointName != null && !"".equals(pointName)){
            pointService.addPoint(addPoint.setTypeId(addType.getTypeId())
                    .setSurfaceId(addSurface.getSurfaceId())
                    .setLineId(addLine.getLineId())
                    .setCreateUser(user.getId())
                    .setPointName(pointName));
            //获取插入完场后的pointId
            addPoint = pointService.findPointAll(addPoint.setId(addPoint.getId())).get(0);
            dailyRecord.setPoint(addPoint.getPointId().toString());
        }else {
            addPoint.setPointId(point);
            dailyRecord.setPoint(point.toString());

        }

        //插入到日报统一记录表
        Integer dailyResult = dailyRecordService.addDailyRecord(dailyRecord
                .setUserId(user.getId())
                .setEvent(eventName)
                .setProcess(process)
                .setResult(result)
                .setMethod(method)
                .setRemark(remarks)
                .setDate(dateFormat1.format(new Date()))
                .setTime(dateFormat.format(new Date())));

        if(dailyResult>0){
            return R.ok("添加成功").put("dailyResult",dailyResult);
        }else{
            return R.error("添加失败");
        }
    }

    /**
     * 修改日报、修改日报记录
     * @param session
     * @param typeId
     * @param surfaceId
     * @param lineId
     * @param pointId
     * @param eventName
     * @param process
     * @param result
     * @param method
     * @param remark
     * @return
     */
    @RequestMapping(value = "/updateDaily")
    @ResponseBody
    public R addDaily(HttpSession session,Integer id, Integer typeId,Integer surfaceId,Integer lineId,Integer pointId,String eventName, String process, String result, String method, String remark){
        //获取用户信息
        User user = (User)session.getAttribute("user");
        //user = new User().setId(8);
        //初始化查询条件

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");//格式化时间
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");//格式化日期

        //修改日报统一记录表
        System.out.println("#########################"+typeId);
        DailyRecord dailyRecord = new DailyRecord().setUserId(user.getId()).setId(id)
                .setType(typeId.toString())
                .setSurface(surfaceId.toString()).setLine(lineId.toString())
                .setPoint(pointId.toString()).setEvent(eventName)
                .setProcess(process).setResult(result).setMethod(method)
                .setRemark(remark).setDate(dateFormat1.format(new Date()))
                .setTime(dateFormat.format(new Date()));
        Integer dailyRecordResult = dailyRecordService.updateDailyRecord(dailyRecord);

        if(dailyRecordResult>0){
            return R.ok("修改成功").put("dailyRecordResult",dailyRecordResult);
        }else{
            return R.error("修改失败");
        }
    }




    /**
     * 删除日报
     * @param dailyRecord
     * @return
     */
    @RequestMapping(value = "/deleteDailyRecord",method = RequestMethod.POST)
    @ResponseBody
    public R deleteDailyRecord(DailyRecord dailyRecord){
        if(dailyRecord.getId()!=null&&dailyRecord.getId()!=0) {
            String str = dailyRecordService.deleteDailyRecord(dailyRecord);
            if ("success".equals(str)) {
                return R.ok("删除成功");
            } else {
                return R.error("删除失败");
            }
        }else{
            return R.error("参数有误!");
        }
    }
}
