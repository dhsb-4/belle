package com.t248.lmf.redis.controller;

import com.sun.deploy.net.URLEncoder;
import com.t248.lmf.redis.entity.*;
import com.t248.lmf.redis.service.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
public class AnalyzeController {
    @Resource
    private CstCustomerService cstCustomerService;
    @Resource
    private OrdersService ordersService;
    @Resource
    private OrdersLineService ordersLineService;
    @Resource
    private BasDicService basDicService;
    @Resource
    private CstLostService cstLostService;
    @RequestMapping(value = "/client/json")
    @ResponseBody
    public Map map(){
        Map<String,Object> map=new HashMap<String,Object>();
        List<String> list=new ArrayList<String>();
        List<Client> list1=new ArrayList<Client>();

        Client client1=new Client();
        client1.setName("胖哥有可能不是索黑");
        client1.setValue(1);

        Client client2=new Client();
        client2.setName("胖哥不是索黑");
        client2.setValue(1);

        Client client3=new Client();
        client3.setName("胖哥是索黑");
        client3.setValue(22);

        list1.add(client1);
        list1.add(client2);
        list1.add(client3);

        list.add("胖哥有可能不是索黑");
        list.add("胖哥不是索黑");
        list.add("胖哥是索黑");
        map.put("Name",list);
        map.put("value",list1);
        return map;
    }
    @RequestMapping(value = "/analyze/contribution")
    public String contribution(@RequestParam(required = false) String custName,
                               @RequestParam(required = false,defaultValue = "1") int pageIndex,
                               Model model){
        Sort sort = Sort.by(Sort.Direction.ASC,"custId");
        Pageable pageable = PageRequest.of(pageIndex-1,5,sort);
        Page<CstCustomer> salChancePager = cstCustomerService.findCustNameAll(custName,pageable);
        List<CstCustomer> list=new ArrayList<CstCustomer>();
        for (CstCustomer cst:salChancePager) {
            if(custName!=null){
                CstCustomer cstCustomer = cstCustomerService.findByCustNameLike(custName);
                if (ordersService.findByOdrCustomerNo(cstCustomer.getCustNo()).size()>0){
                    Orders orders = ordersService.findByOdrCustomerNo(cstCustomer.getCustNo()).get(0);
                    OrdersLine ordersLine = ordersLineService.findByOddOrderId(orders.getOdrId());
                    cst.setPrice(ordersLine.getOddPrice());
                    list.add(cst);
                }
            }else {
                CstCustomer cstCustomer =cstCustomerService.findByCustNameLike(cst.getCustName());
                if (ordersService.findByOdrCustomerNo(cstCustomer.getCustNo()).size()>0){
                    Orders orders = ordersService.findByOdrCustomerNo(cstCustomer.getCustNo()).get(0);
                    OrdersLine ordersLine = ordersLineService.findByOddOrderId(orders.getOdrId());
                    cst.setPrice(ordersLine.getOddPrice());
                    list.add(cst);
                }
            }
        }
        model.addAttribute("cstLostPager",list);
        return "/analyze/clienGrade";
    }
    @RequestMapping("/analyze/upExcel")
    @ResponseBody
    public String upExcel(MultipartFile file) throws Exception {
        System.out.println("进入文件上传的controller");
        if (file == null || file.isEmpty()) {
            return "文件不能为空";
        }
        // 获取Excel的输出流
        InputStream inputStream = file.getInputStream();
        // 获取文件名称
        String fileName = file.getOriginalFilename();
        // init工作簿
        Workbook workbook = null;
        // 获取文件后缀
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        // 根据不同后缀init不同的类，是xls还是xlsx
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            workbook = null;
            return "请上传正确的表格文件";
        }
        // 如果上传为费excel文件，返回
        if (null == workbook) {
            return "请上传文件";
        }
        // init
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        // 定义读取的容器
        List list = new ArrayList<>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheet = workbook.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++){ row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }
                List<Object> li = new ArrayList<>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {      cell = row.getCell(y);
                    li.add(cell);
                }
                list.add(li);
            }
        }
        // 关闭流
        workbook.close();
        inputStream.close();
        for (int i = 0; i < list.size(); i++) {
            List<Object> lo = (List<Object>) list.get(i);
            // TODO 随意发挥
            System.out.println(lo);
        }
        return "上传成功";
    }
    @RequestMapping(value = "/export/cst")
    public void goodsExcel(HttpServletResponse response){
        XSSFWorkbook wb =cstCustomerService.show();
        String fileName = "客户报表.xlsx";
        OutputStream outputStream =null;
        try {
            fileName = URLEncoder.encode(fileName,"UTF-8");
            //设置ContentType请求信息格式
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/analyze/constitute")
    public String conr(@RequestParam(required = false) String chcCustName,
                       @RequestParam(required = false,defaultValue = "1") int pageIndex,
                       @RequestParam(required = false) String chcTitle,
                       Model model){
        Sort sort = Sort.by(Sort.Direction.ASC,"dictId");
        Pageable pageable = PageRequest.of(pageIndex-1,5,sort);
        Page<BasDict> salChancePager0 = basDicService.findAll1("客户等级",pageable);
        List<BasDict> list=new ArrayList<BasDict>();
        for (BasDict bas:salChancePager0) {
            bas.setCount(cstCustomerService.findByCustLevelLabelLike(bas.getDictValue()));
            System.out.println("count"+bas.getCount());
            list.add(bas);
        }
        model.addAttribute("cstLostPager",list);
        return "/analyze/constiture";
    }
    @RequestMapping(value = "/constitute/json")
    @ResponseBody
    public Map map1(){
        Map<String,Object> map=new HashMap<String,Object>();

        List<Integer> list=new ArrayList<Integer>();
        List<String> list1=new ArrayList<String>();
        for (BasDict bas:basDicService.findAll()) {
            bas.setCount(cstCustomerService.findByCustLevelLabelLike(bas.getDictValue()));
            System.out.println("count"+bas.getCount());
            list1.add(bas.getDictItem());
            list.add(bas.getCount());
        }
        map.put("name",list1);
        map.put("value",list);
        return map;
    }
    @RequestMapping(value = "/analyze/serve")
    public String serve(@RequestParam(required = false,defaultValue = "1") int pageIndex,
                       Model model){
        Sort sort = Sort.by(Sort.Direction.ASC,"dictId");
        Pageable pageable = PageRequest.of(pageIndex-1,5,sort);
        Page<BasDict> salChancePager0 = basDicService.findAll1("服务类型",pageable);
        List<BasDict> list=new ArrayList<BasDict>();
        for (BasDict bas:salChancePager0) {
            bas.setCount(cstCustomerService.findByCustStatus(Long.toString(bas.getDictId())));
            list.add(bas);
        }
        model.addAttribute("cstLostPager",list);
        return "/analyze/serve";
    }
    @RequestMapping(value = "/serve/json")
    @ResponseBody
    public Map map2(){
        Map<String,Object> map=new HashMap<String,Object>();
        List<Integer> list=new ArrayList<Integer>();
        List<String> list1=new ArrayList<String>();
        for (BasDict bas:basDicService.findByDicttype()) {
            bas.setCount(cstCustomerService.findByCustStatus(Long.toString(bas.getDictId())));
            list1.add(bas.getDictItem());
            list.add(bas.getCount());
        }
        map.put("name",list1);
        map.put("value",list);
        return map;
    }
    @RequestMapping("/downExcel")
    public void downExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
            XSSFWorkbook wb =basDicService.show();
            String fileName = "客户服务表.xlsx";
            OutputStream outputStream =null;
            try {
                fileName = URLEncoder.encode(fileName,"UTF-8");
                //设置ContentType请求信息格式
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-disposition", "attachment;filename=" + fileName);
                outputStream = response.getOutputStream();
                wb.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    @RequestMapping("/export/clinet")
    public void exportclinet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        XSSFWorkbook wb =cstCustomerService.show1();
        String fileName = "客户贡献表.xlsx";
        OutputStream outputStream =null;
        try {
            fileName = URLEncoder.encode(fileName,"UTF-8");
            //设置ContentType请求信息格式
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/analyze/lapsed")
    public String juserlist(Model model,
                            @RequestParam(required = false,defaultValue = "1") int pageIndex,
                            @RequestParam(required = false) String lstCustNo,
                            @RequestParam(required = false) String lstCustManagerName){
        Sort sort = Sort.by(Sort.Direction.ASC,"lstId");
        Pageable pageable = PageRequest.of(pageIndex-1,5,sort);
        Page<CstLost> CstCustomerPager = cstLostService.findMarketiongAll(lstCustNo,lstCustManagerName,pageable);
        model.addAttribute("cstLostPager",CstCustomerPager);
        return "/analyze/lapsed";
    }
}
