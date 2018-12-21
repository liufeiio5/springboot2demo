package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.TeaRepository;
import com.zftx.mcdaily.service.TeaRepositoryService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class TeaRepositoryController {

    @Autowired
    private TeaRepositoryService teaRepositoryService;

    @RequestMapping(value = "/teaRepository")
    public String teaRepository(HttpSession session) {
       /* if (session.getAttribute("sessionUser") == null) {
            return "redirect:/login";
        }*/
        return "teaRepository";
    }

    /**
     * 查询 茶库茶点
     * @param teaRepository
     * @return
     */
    @RequestMapping(value = "/getTeaRepository", method = RequestMethod.GET)
    @ResponseBody
    public R getLine(TeaRepository teaRepository) {
        ArrayList<Map<String, Object>> list = teaRepositoryService.getTeaRepository(teaRepository);
        if (list.size() > 0 && list != null) {
            return R.ok("数据获取成功").put("data", list);
        } else {
            return R.error("获取数据失败");
        }
    }

    /**
     * 添加 茶点 入库
     * @param catName
     * @param tName
     * @param mrequest
     * @param standard
     * @param price
     * @param note
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/addTeaRepository", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public R addTeaRepository(@RequestParam("catName")String catName,@RequestParam("tName")String tName, MultipartRequest mrequest,@RequestParam("standard") String standard,@RequestParam("price")float price,@RequestParam("note") String note)throws IOException{
        //上传地址
        System.out.println(catName+"xxxxxxxxxxxxxxxxxxxxxxxx"+tName+"xxxxxxxxxxxxxxxxxxxxxxxx"+standard+"xxxxxxxxxxxxxxxxxxxxxxxx"+price+"xxxxxxxxxxxxxxxxxxxxxxxx"+note+"xxxxxxxxxxxxxxxxxxxxxxxx");
        String dir = System.getProperty("user.dir") + "/src/main/resources/static/upload/tea_images/";
        ArrayList<String> urls = new ArrayList<String>();
        List<MultipartFile> files = mrequest.getFiles("file");

        String tImg="";
        for (MultipartFile file : files) {
            tImg="/upload/tea_images/"+tName.trim()+file.getOriginalFilename().substring(file.getOriginalFilename().length()-4, file.getOriginalFilename().length());
            File f = new File(System.getProperty("user.dir") + "/src/main/resources/static/"+tImg);
            System.out.println("qqqqqqqqqqqqqqqqqqqqqqq"+tImg);
            urls.add(dir + file.getOriginalFilename());
            //如果文件夹不存在则创建
            if (!f.getParentFile().exists())
                f.getParentFile().mkdirs();
            //创建文件
            try {
                file.transferTo(f);
            } catch (IOException e) {
                e.printStackTrace();
                return R.error("上传失败");
            }
        }
        TeaRepository teaRepository=new TeaRepository().setCatName(catName.trim()).setTName(tName.trim()).setTImg(tImg.trim()).setStandard(standard.trim()).setPrice(price).setNote(note.trim());
        if (teaRepository != null) {
            String str = teaRepositoryService.addTeaRepository(teaRepository);
            if ("success".equals(str)) {
                return R.ok("添加成功");
            } else if ("repeat".equals(str)) {
                return R.error("重复添加");
            } else {
                return R.error("添加失败");
            }
        } else {
            return R.error("参数有误!");
        }
    }

    /**
     * 修改 茶库 茶点
     * @param catName
     * @param tName
     * @param mrequest
     * @param standard
     * @param price
     * @param note
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/updateTeaRepository", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public R updateTeaRepository(@RequestParam("id")Integer id,@RequestParam("catName")String catName,@RequestParam("tName")String tName, MultipartRequest mrequest,@RequestParam("standard") String standard,@RequestParam("price")float price,@RequestParam("note") String note)throws IOException{
        //上传地址
        System.out.println(catName+"xxxxxxxxxxxxxxxxxxxxxxxx"+tName+"xxxxxxxxxxxxxxxxxxxxxxxx"+standard+"xxxxxxxxxxxxxxxxxxxxxxxx"+price+"xxxxxxxxxxxxxxxxxxxxxxxx"+note+"xxxxxxxxxxxxxxxxxxxxxxxx");
        String dir = System.getProperty("user.dir") + "/src/main/resources/static/upload/tea_images/";
        ArrayList<String> urls = new ArrayList<String>();
        List<MultipartFile> files = mrequest.getFiles("file");
        TeaRepository teaRepository = new TeaRepository().setId(id).setCatName(catName.trim()).setTName(tName.trim()).setStandard(standard.trim()).setPrice(price).setNote(note.trim());
        System.out.println("qqqqqqqqqqqqqqqqqqqqqqq" + files);
        if (!files.isEmpty()) {
            String tImg = "";
            for (MultipartFile file : files) {
                tImg = "/upload/tea_images/" + tName.trim() + file.getOriginalFilename().substring(file.getOriginalFilename().length() - 4, file.getOriginalFilename().length());
                File f = new File(System.getProperty("user.dir") + "/src/main/resources/static/" + tImg);
                urls.add(dir + file.getOriginalFilename());
                //如果文件夹不存在则创建
                if (!f.getParentFile().exists())
                    f.getParentFile().mkdirs();
                //创建文件
                try {
                    file.transferTo(f);
                } catch (IOException e) {
                    e.printStackTrace();
                    return R.error("上传失败");
                }
            }
            if (teaRepository.setTImg(tImg.trim()) != null) {
                String str = teaRepositoryService.updateTeaRepository(teaRepository);
                if ("success".equals(str)) {
                    return R.ok("修改成功");
                } else if ("repeat".equals(str)) {
                    return R.error("该茶点已存在");
                } else {
                    return R.error("修改失败");
                }
            } else {
                return R.error("参数有误!");
            }
        }else{
            return null;
        }
    }

    /**
     * 删除 茶点
     * @param teaRepository
     * @return
     */
    @RequestMapping(value = "/deleteTeaRepository")
    @ResponseBody
    public R deleteTeaRepository(TeaRepository teaRepository) {
        if (teaRepository != null) {
            String str = teaRepositoryService.delTeaRepository(teaRepository);
            if ("success".equals(str)) {
                return R.ok("删除成功");
            } else {
                return R.error("删除失败");
            }
        } else {
            return R.error("参数有误!");
        }
    }


    //添加文档(即上传文件)
    @RequestMapping(value = "/insertTeaRepository")
    public ModelAndView inset(@ModelAttribute("teaRepository") TeaRepository teaRepository, @ModelAttribute("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 如果文件不为空
        if (!file.isEmpty()) {
			/*// 获取当前项目根地址
			String contextPaht = request.getContextPath();
			// 获取根地址下面的路径
			String servletPath = request.getContextPath();
			String scherm = request.getScheme();*/
            // 存放文件路径
            String storePath = "D:\\Work\\eclipse\\项目\\PersonnelAdministration\\WebContent\\upload";
            // 获取上传时的文件名
            String fileName = file.getOriginalFilename();
            // 新建一个file
            teaRepository.setTImg(fileName);
            File filepath = new File(storePath, fileName);
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs(); // 如果目录不存在就新建目录
            }
            try {
                // 把文件写入目标文件地址
                file.transferTo(new File(storePath + File.separator + fileName));
            } catch (Exception e) {
                e.printStackTrace();
                return new ModelAndView("error");
            }
            //执行添加文档操作
            this.teaRepositoryService.addTeaRepository(teaRepository);
            return new ModelAndView("teaRepository");
        } else {
            return new ModelAndView("error");// 失败后跳转到失败页面
        }
    }
}
