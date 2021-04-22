package com.mycompany.sabangpalbang.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.mycompany.sabangpalbang.dto.Inquiry;

@Mapper
public interface InquiryDao {
	
	public List<Inquiry> selectAll();
	public Inquiry selectInquiryByNickname(String nickname);
	public int updateInquiryAnswerById(@Param(value="inquiry_anscontent")String inquiry_anscontent, 
									@Param(value="inquiry_id")int inquiry_id);
	public int deleteInquiryByInquiryId(int inquiry_id);
	public List<Inquiry> selectInquiry(HashMap<String, Object> map);
	public int count(int sid);
	public List<Inquiry> selectNicknameInquiry(HashMap<String, Object> map);
	public Inquiry selectInquiryById(int inquiry_id);
	public int countNeedAnswer(int sid);
	
}