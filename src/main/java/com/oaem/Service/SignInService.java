package com.oaem.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oaem.Pojo.Login;
import com.oaem.Pojo.SignIn;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface SignInService extends IService<SignIn> {
    List<Map<String, Object>> getAllSignInHistory(@Param("limit") int limit, @Param("offset") int offset, @Param("data") String data, @Param("status") Integer status, @Param("year") String year, @Param("month") String month, @Param("day") String day);
    int getTotalHistory(@Param("data") String data, @Param("status") Integer status,@Param("year") String year, @Param("month") String month,@Param("day") String day);
    int addSignIn(Integer employeeId, Timestamp signDate, Integer publish, Integer type);
    int deleteSignIn(Integer signId);


}
