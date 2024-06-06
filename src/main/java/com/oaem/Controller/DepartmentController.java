package com.oaem.Controller;

import com.oaem.Pojo.SimReturn;
import com.oaem.Service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/add")
    @Transactional
    public SimReturn addDepartment(@RequestParam String name,
                                   @RequestParam Timestamp departmentCreateDate,
                                   @RequestParam String description,

                                   @RequestParam Integer principal) {

        try {

            int result = departmentService.addDepartment(name, departmentCreateDate, description,  principal>0?principal:null);
            if (result > 0) {
                return new SimReturn(200, "部门添加成功", null);
            } else {
                return new SimReturn(500, "部门添加失败", null);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "部门添加失败,服务器遇到了意料不到的情况，导致无法完成请求" , null);
        }
    }


    @GetMapping("/getAll/{limit}/{offset}/{data}/{status}")
    @Transactional
    public SimReturn getAllDepartments(@PathVariable("limit") Integer limit, @PathVariable("offset") Integer offset,@PathVariable("data") String data,@PathVariable("status") Integer status) {
        try {

            return new SimReturn(200, "获取所有部门成功", departmentService.getAllDepartments(limit, offset, data,status));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取所有部门失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }


    @GetMapping("/getTotal/{data}/{status}")
    @Transactional
    public SimReturn getTotal(@PathVariable("data") String data,@PathVariable("status") Integer status) {


        try {
            return new SimReturn(200, "获取部门总数成功", departmentService.getTotal(data,status));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取部门总数失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }

    @GetMapping("/get/{id}")
    @Transactional
    public SimReturn getDepartment(@PathVariable("id") Integer id) {

        try {
            return new SimReturn(200, "获取信息成功", departmentService.getDepartmentById(id));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取信息失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }

    @PostMapping("/update")
    @Transactional
    public SimReturn updateDepartment(@RequestParam Integer id,
                                     @RequestParam String name,
                                     @RequestParam String description,
                                     @RequestParam Integer principal) {
        try {
            int result = departmentService.updateDepartment( name, description, principal,id);
            if (result > 0) {
                return new SimReturn(200, "部门信息更新成功", null);
            } else {
                return new SimReturn(500, "部门信息更新失败", null);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "部门信息更新失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }

    @PostMapping("/delete/{id}")
    @Transactional
    public SimReturn deleteDepartment(@PathVariable("id") Integer id) {
        try {
            int result = departmentService.deleteDepartment(id);
            if (result > 0) {
                return new SimReturn(200, "部门删除成功", null);
            } else {
                return new SimReturn(500, "部门删除失败", null);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "部门删除失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }

    @GetMapping("/getDepartmentSelect")
    @Transactional
    public SimReturn getDepartmentSelect (){
        try {
            return new SimReturn(200, "获取部门下拉框成功", departmentService.QueryDepartment());
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取部门下拉框失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }
}
