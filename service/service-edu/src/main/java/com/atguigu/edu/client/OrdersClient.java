package com.atguigu.edu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xudongzhou
 * @date 2022-07-06
 * @desc
 */
@FeignClient(value = "service-order",fallback = OrdersClientImpl.class)
@Component
public interface OrdersClient {

    /**
     * 根据课程id和用户id查询订单表中订单状态
     * @param courseId 课程id
     * @param memberId 会员id
     * @return 是否购买
     */
    @GetMapping("/order/isBuyCourse/{courseId}/{memberId}")
    Boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);

}
