package com.atguigu.edu.client;

import com.atguigu.commonutils.result.ResultCode;
import com.atguigu.servicebase.exceptionhandler.exception.GuliException;
import org.springframework.stereotype.Component;

/**
 * @author xudongzhou
 * @date 2022-07-06
 * @desc
 */
@Component
public class OrdersClientImpl implements OrdersClient {

    /**
     * 根据课程id和用户id查询订单表中订单状态
     *
     * @param courseId 课程id
     * @param memberId 会员id
     * @return 是否购买
     */
    @Override
    public Boolean isBuyCourse(String courseId, String memberId) {
        throw GuliException.from(ResultCode.ERROR);
    }

}
