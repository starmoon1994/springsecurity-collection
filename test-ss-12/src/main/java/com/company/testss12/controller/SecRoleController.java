package com.company.testss12.controller;


import com.company.testss12.entity.SecRelationRoleMenu;
import com.company.testss12.entity.SecRole;
import com.company.testss12.service.SecRoleService;
import com.company.testss12.support.RetVO;
import com.company.testss12.support.page.PageSystemContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hyp-company on 2018/6/29.
 */
@RestController
@RequestMapping("/secrole")
public class SecRoleController {

    @Autowired
    private SecRoleService secRoleService;


    /**
     * 添加用户
     *
     * @param secRole
     * @return
     */
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public RetVO addOne(@RequestBody SecRole secRole) {
        RetVO retVO = new RetVO();

        secRoleService.addRole(secRole);

        retVO.setData(secRole);

        return retVO;
    }

    /**
     * 删除用户
     *
     * @param secRole
     * @return
     */
    @RequestMapping(value = "/remove.do", method = RequestMethod.POST)
    public RetVO removeRole(@RequestBody SecRole secRole) {
        RetVO retVO = new RetVO();

        secRoleService.removeRole(secRole);

        retVO.setData(secRole);

        return retVO;
    }

    /**
     * 修改用户
     *
     * @param secRole
     * @return
     */
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public RetVO updateRole(@RequestBody SecRole secRole) {
        RetVO retVO = new RetVO();

        secRoleService.updateRole(secRole);

        retVO.setData(secRole);

        return retVO;
    }


    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/info.do", method = RequestMethod.GET)
    public RetVO getRole(@RequestParam(required = false, defaultValue = "0", value = "id") long id) {
        RetVO retVO = new RetVO();

        SecRole secRole = secRoleService.getRole(id);

        return retVO;
    }

    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public RetVO getRoleList(@RequestParam(required = false, defaultValue = "0", value = "state") int state) {
        RetVO retVO = new RetVO();

        PageInfo<SecRole> pageInfo = PageHelper.startPage(PageSystemContext.getPageNum(), PageSystemContext.getPageSize())
                .doSelectPageInfo(() -> secRoleService.getList(state));

        retVO.setData(pageInfo);

        return retVO;
    }

    @RequestMapping(value = "/roleMenuRelationList.do", method = RequestMethod.GET)
    public RetVO getRoleMenuRelationList(
            @RequestParam(required = false, defaultValue = "0", value = "roleId") int roleId,
            @RequestParam(required = false, defaultValue = "0", value = "menuId") int menuId) {
        RetVO retVO = new RetVO();

        List<SecRelationRoleMenu> list = secRoleService.getRoleMenuRelationList(roleId, menuId);

        PageInfo pageInfo = new PageInfo(list);

        retVO.setData(pageInfo);

        return retVO;
    }


    /**
     * 处理 权限配置页面改变权限的请求
     */
    @RequestMapping(value = "/menuAuth.do", method = RequestMethod.POST)
    public RetVO handleMenuAuth(
            @RequestParam(required = false, defaultValue = "0", value = "roleId") int roleId,
            @RequestParam(required = false, defaultValue = "0", value = "menuId") int menuId,
            @RequestParam(required = false, defaultValue = "0", value = "isValid") int isValid) {

        RetVO retVO = new RetVO();

        int res = secRoleService.handleMenuAuth(roleId, menuId, isValid);

        return retVO;
    }


}
