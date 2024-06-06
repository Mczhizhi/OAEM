package com.oaem.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.oaem.Pojo.ReportAbsence;
import com.oaem.Pojo.SignInReplenishment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SignInReplenishmentService extends IService<SignInReplenishment> {
    List<Map<String, Object>> getAllSignInReplenishment( int limit,  int offset,  String data,  Integer status);
    int updateStatus(Integer status, Integer holidayId);
    int getTotal( String data, Integer status);
    List<Map<String, Object>> getAllRenewalHistory(@Param("limit") int limit, @Param("offset") int offset, @Param("data") String data, @Param("status") Integer status, @Param("year") String year, @Param("month") String month);
    int getTotalHistory(@Param("data") String data, @Param("status") Integer status,@Param("year") String year, @Param("month") String month);

}
