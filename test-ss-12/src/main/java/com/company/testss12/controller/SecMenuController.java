package com.company.testss12.controller;

import com.company.testss12.entity.SecMenu;
import com.company.testss12.entity.SecUser;
import com.company.testss12.entity.vo.MenuVo;
import com.company.testss12.service.SecMenuService;
import com.company.testss12.support.RetVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hyp-company on 2018/6/29.
 */
@RestController
@RequestMapping("/secmenu")

public class SecMenuController {

    @Autowired
    private SecMenuService secMenuService;


    /**
     * 添加
     *
     * @param secMenu
     * @return
     */
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public RetVO addOne(@RequestBody SecMenu secMenu) {
        RetVO retVO = new RetVO();

        secMenuService.addMenu(secMenu);

        retVO.setData(secMenu);

        return retVO;
    }

    /**
     * 删除
     *
     * @param secMenu
     * @return
     */
    @RequestMapping(value = "/remove.do", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public RetVO removeMenu(@RequestBody SecMenu secMenu) {
        RetVO retVO = new RetVO();

        secMenuService.removeMenu(secMenu);

        retVO.setData(secMenu);

        return retVO;
    }

    /**
     * 修改
     *
     * @param secMenu
     * @return
     */
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public RetVO updateMenu(@RequestBody SecMenu secMenu) {
        RetVO retVO = new RetVO();

        secMenuService.updateMenu(secMenu);

        retVO.setData(secMenu);

        return retVO;
    }


    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/info.do", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public RetVO getMenu(@RequestParam(required = false, defaultValue = "0", value = "id") long id) {
        RetVO retVO = new RetVO();

        SecMenu secMenu = secMenuService.getMenu(id);

        retVO.setData(secMenu);

        return retVO;
    }


    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public RetVO getMenuList(@RequestParam(required = false, defaultValue = "0", value = "state") int state) {
        RetVO retVO = new RetVO();
        PageHelper.startPage(1, Integer.MAX_VALUE);

        List<SecMenu> list = secMenuService.getList(state);

        List<MenuVo> menuVos = secMenuService.handleMenuList(list);

        PageInfo pageInfo = new PageInfo(menuVos);

        retVO.setData(pageInfo);

        return retVO;
    }


    /**
     * 登录用户获取菜单列表
     * 权限：登录即可
     * @return
     */
    @RequestMapping("/getUserMenuList")
    public RetVO getUserMenuList(){

        RetVO retVO = new RetVO();

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<MenuVo> userMenuList = secMenuService.getUserMenuList(userDetails);

        retVO.setData(userMenuList);

        return retVO;
    }

}
