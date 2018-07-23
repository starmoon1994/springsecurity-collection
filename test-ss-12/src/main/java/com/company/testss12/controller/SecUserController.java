package com.company.testss12.controller;


import com.company.testss12.entity.SecUser;
import com.company.testss12.service.SecUserService;
import com.company.testss12.support.RetVO;
import com.company.testss12.support.page.PageSystemContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created by hyp-company on 2018/6/29.
 */

@RestController
@RequestMapping("/secuser")
public class SecUserController {

    private static Logger logger = LoggerFactory.getLogger(SecUserController.class);

    @Autowired
    private SecUserService secUserService;

    /**
     * 添加用户
     *
     * @param secUser
     * @return
     */
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public RetVO addUser(@RequestBody SecUser secUser) {
        RetVO retVO = new RetVO();

        int uid = secUserService.addUser(secUser);

        HashMap<String, Object> map = new HashMap<String, Object>(2);
        map.put("userId", secUser.getUid());
        retVO.setData(map);
        return retVO;
    }

    /**
     * 删除用户
     *
     * @param secUser
     * @return
     */
    @RequestMapping(value = "/remove.do", method = RequestMethod.POST)
    public RetVO removeUser(@RequestBody SecUser secUser) {
        RetVO retVO = new RetVO();

        secUserService.removeUser(secUser);

        HashMap<String, Object> map = new HashMap<String, Object>(2);
        map.put("userId", secUser.getUid());
        retVO.setData(map);

        return retVO;
    }

    /**
     * 修改用户
     *
     * @param secUser
     * @return
     */
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public RetVO updateUser(@RequestBody SecUser secUser) {
        RetVO retVO = new RetVO();

        secUserService.updateUser(secUser);
        return retVO;
    }


    /**
     * 用户详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/info.do", method = RequestMethod.GET)
    public RetVO getUser(@RequestParam(required = false, defaultValue = "0", value = "id") long id) {
        RetVO retVO = new RetVO();

        SecUser secUser = secUserService.getUser(id);

        return retVO;
    }

    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public RetVO getUserList(@RequestParam(required = false, defaultValue = "0", value = "state") int state) {
        RetVO retVO = new RetVO();

        PageInfo<SecUser> pageInfo = PageHelper.startPage(PageSystemContext.getPageNum(), PageSystemContext.getPageSize())
                .doSelectPageInfo(() -> secUserService.getList(state));

        retVO.setData(pageInfo);

        return retVO;
    }


}
