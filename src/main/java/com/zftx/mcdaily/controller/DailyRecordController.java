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

    @RequestMapping(value = "/table")
    public String table(HttpSession session)
    {
        if (session.getAttribute("user") == null)
            return "redirect:/login";
        return "table";
    }


    /**
     * 查询日报
     * @param userId 用户id
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    @RequestMapping(value = "/getDaily",method = RequestMethod.GET)
    @ResponseBody
    public R getDailyRecord(Integer userId, Integer startDate, Integer endDate, HttpSession session)
    {
        //登录用户
        User user = (User) session.getAttribute("user");
        if(user != null && user.getId() != null && userId == null)
            userId = user.getId();
        if(startDate != null && endDate != null)
            if(startDate>endDate)
                return R.error("结束日期不能比开始日期早").put("fullName",user != null ? user.getFullName():"");
        if(startDate == null && endDate == null)
        {
            //日历
            Calendar calendar = Calendar.getInstance();
            //当前系统时间的  前七天
            calendar.add(Calendar.DATE,-7);
            startDate = Integer.parseInt(calendar.get(Calendar.YEAR)+""+(calendar.get(Calendar.MONTH)+1)+""+calendar.get(Calendar.DAY_OF_MONTH)+"");
            endDate = Integer.parseInt(Tool.getYear()+""+Tool.getMonth()+""+Tool.getToday()+"");
        }


        ArrayList<HashMap<String, Object>> list = dailyRecordService.getDailyRecord(userId, startDate.toString(), endDate.toString());
        if(list !=null &&list.size()>0)
            return R.ok("数据获取成功").put("data",list).put("fullName",user != null ? user.getFullName():"");
        else
            return R.error("获取数据失败").put("fullName",user != null ? user.getFullName():"");
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
    public R addDaily(HttpSession session,String typeName,Integer type,Integer surface,Integer line,
                      Integer point,String surfaceName,String lineName,String pointName,String eventName,
                      String process,String result,String method,String remarks,String selectDate){
        //获取用户信息
        User user = (User)session.getAttribute("user");
        //初始化查询条件
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");//格式化时间
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");//格式化日期

        //不能提前插入第二天或之后的日报
        if(selectDate!=null&& selectDate!="") {
            if (Integer.parseInt(selectDate) > Integer.parseInt(dateFormat1.format(new Date()))) {
                return R.error("不能提前创建日报");
            }
        }

        Type addType = new Type();
        Surface addSurface = new Surface();
        Line addLine = new Line();
        Point addPoint = new Point();
        DailyRecord dailyRecord = new DailyRecord();

        //添加日报的时候添加的type surface line point 关联当前登录的用户ID
        if(typeName != null && !"".equals(typeName)){
            //判断当前新添加的类型是否重复
            List<Type> addTypeList=typeService.getType(addType.setTypeName(typeName).setCreateUser(user.getId()));
            if(addTypeList==null||addTypeList.size()==0){
                typeService.insertType(addType.setCreateUser(user.getId()).setTypeName(typeName));
                //获取当前插入完成后类型的typeId
                addType = typeService.getType(addType.setId(addType.getId())).get(0);
                dailyRecord.setType(addType.getTypeId().toString());
            }else{
                addType = addTypeList.get(0);
                dailyRecord.setType(addType.getTypeId().toString());
            }
        }else{
            addType.setTypeId(type);
            dailyRecord.setType(type.toString());
        }

        //插入面记录表，关联当前用户和上下级
        if(surfaceName != null && !"".equals(surfaceName)){
            //判断当前新增面是否重复
            List<Surface> addSurfaceList=surfaceService.findAllSurFace(addSurface.setSurfaceName(surfaceName).setCreateUser(user.getId()));
            if(addSurfaceList==null||addSurfaceList.size()==0){
                surfaceService.addSurface(addSurface.setTypeId(addType.getTypeId())
                        .setCreateUser(user.getId())
                        .setSurfaceName(surfaceName));
                //获取surface 插入完成后surfaceId
                addSurface = surfaceService.findAllSurFace(addSurface.setId(addSurface.getId())).get(0);
                dailyRecord.setSurface(addSurface.getSurfaceId().toString());
            }else{
                addSurface = addSurfaceList.get(0);
                dailyRecord.setSurface(addSurface.getSurfaceId().toString());
            }
        }else{
            addSurface.setSurfaceId(surface);
            dailyRecord.setSurface(surface.toString());
        }

        //插入线记录表，关联当前登录用户，和上下级
        if(lineName != null && !"".equals(lineName)){
            //判断当前新增线是否重复
            List<Line> addLineList=lineService.findLineAll(addLine.setLineName(lineName).setCreateUser(user.getId()));
            if(addLineList==null||addLineList.size()==0){
                lineService.addLine(addLine.setTypeId(addType.getTypeId())
                        .setSurfaceId(addSurface.getSurfaceId())
                        .setCreateUser(user.getId())
                        .setLineName(lineName));
                //获取插入完成后返回的lineId
                addLine = lineService.findLineAll(addLine.setId(addLine.getId())).get(0);
                dailyRecord.setLine(addLine.getLineId().toString());
            }else{
                addLine = addLineList.get(0);
                dailyRecord.setLine(addLine.getLineId().toString());
            }
        }else {
            addLine.setLineId(line);
            dailyRecord.setLine(line.toString());
        }

        //插入点记录表，关联当前用户和上下级
        if(pointName != null && !"".equals(pointName)){
            //判断当前新增点是否重复
            List<Point> addPointList=pointService.findPointAll(addPoint.setPointName(pointName).setCreateUser(user.getId()));
            if(addPointList==null||addPointList.size()==0){
                pointService.addPoint(addPoint.setTypeId(addType.getTypeId())
                        .setSurfaceId(addSurface.getSurfaceId())
                        .setLineId(addLine.getLineId())
                        .setCreateUser(user.getId())
                        .setPointName(pointName));
                //获取插入完场后的pointId
                addPoint = pointService.findPointAll(addPoint.setId(addPoint.getId())).get(0);
                dailyRecord.setPoint(addPoint.getPointId().toString());
            }else{
                addPoint = addPointList.get(0);
                dailyRecord.setPoint(addPoint.getPointId().toString());
            }
        }else {
            addPoint.setPointId(point);
            dailyRecord.setPoint(point.toString());
        }

        //自己选择时间，或者使用当前默认时间
        if(selectDate!=null&&!"".equals(selectDate)){
            dailyRecord.setDate(selectDate);
        }else{
            dailyRecord.setDate(dateFormat1.format(new Date()));
        }
        //插入到日报统一记录表
        Integer dailyResult = dailyRecordService.addDailyRecord(dailyRecord
                .setUserId(user.getId())
                .setEvent(eventName)
                .setProcess(process)
                .setResult(result)
                .setMethod(method)
                .setRemark(remarks)
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
    public R addDaily(HttpSession session,Integer id, Integer typeId,Integer surfaceId,Integer lineId,Integer pointId,String eventName, String process, String result, String method, String remark,String time){
        //获取用户信息
        User user = (User)session.getAttribute("user");
        //初始化查询条件
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");//格式化日期

        //修改日报统一记录表
        DailyRecord dailyRecord = new DailyRecord().setUserId(user.getId()).setId(id)
                .setType(typeId.toString())
                .setSurface(surfaceId.toString()).setLine(lineId.toString())
                .setPoint(pointId.toString()).setEvent(eventName)
                .setProcess(process).setResult(result).setMethod(method)
                .setRemark(remark).setDate(dateFormat1.format(new Date()))
                .setTime(time);
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
