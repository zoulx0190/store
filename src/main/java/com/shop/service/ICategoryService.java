package com.shop.service;

import com.shop.common.ServerResponse;
import com.shop.dao.CategoryMapper;
import com.shop.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Description：
 * @ Author     ： zlx
 * @ Date       ： Created in  2018/11/14 13:12
 * @ Modified By：
 */
@Service
public interface ICategoryService {
    ServerResponse<String> addCategory(String categoryName, Integer parentId);

    ServerResponse<String> updateCategoryName(Integer categoryId,String categoryName);

    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
}
