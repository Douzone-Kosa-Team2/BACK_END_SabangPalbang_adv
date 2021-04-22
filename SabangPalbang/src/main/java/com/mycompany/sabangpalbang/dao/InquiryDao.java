package com.mycompany.sabangpalbang.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.mycompany.sabangpalbang.dto.Inquiry;

@Mapper
public interface InquiryDao {
	
	public int insert(Inquiry inquiry);
	public List<Inquiry> selectAll();
	public Inquiry selectInquiryByNickname(String nickname);
	public int updateInquiryByid(int inquiry_id);
	public int updateInquiryAnswerById(int inquiry_id);
	public int deleteInquiryByInquiryId(int inquiry_id);
	public List<Inquiry> selectInquiry(HashMap<String, Object> map);
	public int count(int sid);
	public int mycount(String inquiry_writer);
	public List<Inquiry> selectNicknameInquiry(HashMap<String, Object> map);
	public int insertInquiryFirst(Inquiry inquiry);
	public Inquiry selectInquiryById(int inquiry_id);
	public int countNeedAnswer(int sid);
	
}