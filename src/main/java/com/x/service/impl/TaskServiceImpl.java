package com.x.service.impl;

import com.x.inter.TaskMapper;
import com.x.model.Task;
import com.x.service.TaskService;
import com.x.util.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskMapper taskMapper;

    public Task getTaskById(int id) {
        return this.taskMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Task> getAllTask() {
        return this.taskMapper.selectAll();
    }

    @Override
    public boolean addTask(Task task) {
        int num = this.taskMapper.insert(task);
        if(num == 1) return true;
        return false;
    }

    @Override
    public boolean delTask(int id) {
        int num = this.taskMapper.deleteByPrimaryKey(id);
        if(num == 1) return true;
        return false;
    }

    @Override
    public boolean updataTask(Task task) {
        int num = this.taskMapper.updateByPrimaryKeySelective(task);
        if(num == 1) return true;
        return false;
    }

    @Override
    public List<Task> getTaskByProId(int pro_id) {
        return this.taskMapper.selectByProId(pro_id);

    }

    @Override
    public List<Task> getPageByProId(int pageNow, int pro_id, int pageSize) {
        Page page = null;

        List<Task> bugs = new ArrayList<Task>();

        //获取查询数目
        int totalCount = this.taskMapper.getCountByProId(pro_id);

        page = new Page(pageNow,totalCount);

        page.setPageSize(pageSize);

        bugs = this.taskMapper.selectPageByProId(page.getStartPos(), page.getPageSize(), pro_id);


        return bugs;
    }

    @Override
    public List<Task> getPage(int pageNow, int pageSize) {
        Page page = null;

        List<Task> bugs = new ArrayList<Task>();

        //获取查询数目
        int totalCount = this.taskMapper.getCount();

        page = new Page(pageNow,totalCount);

        page.setPageSize(pageSize);

        bugs = this.taskMapper.selectPage(page.getStartPos(), page.getPageSize());


        return bugs;
    }

    @Override
    public int getCount() {
        return this.taskMapper.getCount();
    }

    @Override
    public int getCountByProId(int pro_id) {
        return this.taskMapper.getCountByProId(pro_id);
    }

    @Override
    public List<Task> getTaskByUserId(int id) {
        return this.taskMapper.selectByUserId(id);
    }
}
